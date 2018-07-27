package com.myke.oauth.test;

import com.google.gson.Gson;
import com.myke.oauth2.AuthCenterApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 1. 不用启动 AuthCenterApplication 应用
 * 2. 直接执行 test
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = AuthCenterApplication.class)
@Slf4j
public class SecurityOauth2PasswordTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;


    //clientId
    final static String CLIENT_ID = "client_2";
    //clientSecret
    final static String CLIENT_SECRET = "123456";

    //用户名
    final static String USERNAME = "user_1";
    //密码
    final static String PASSWORD = "123456";

    //获取accessToken 的URI
    final static String TOKEN_REQUEST_URI = "/oauth/token";

    // 资源 api
    final static String ORDER_URI = "/order/1";


    @Before
    public void setup() {
        //初始化MockMvc对象,添加Security过滤器链
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    public String obtainAccessToken() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("username", USERNAME);
        params.add("password", PASSWORD);

        // @formatter:off 和 @formatter:ON 此段代码不进行格式化

        // @formatter:off

        ResultActions result = mockMvc.perform(post(TOKEN_REQUEST_URI)
                .params(params)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());

        // @formatter:on

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        System.out.println(jsonParser.parseMap(resultString).get("access_token").toString());
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    /**
     * 获取 token
     *
     * @throws Exception
     */
    @Test
    public void getAccessToken() throws Exception {
        final String accessToken = obtainAccessToken();
        log.info("access_token={}", accessToken);
    }


    /**
     * 未授权 401
     *
     * @throws Exception
     */
    @Test
    public void UnauthorizedTest() throws Exception {
        ResultActions actions = mockMvc.perform(get(ORDER_URI));
        int status = actions.andReturn().getResponse().getStatus();
        Assert.assertTrue(status == HttpStatus.UNAUTHORIZED.value());
    }

    /**
     * 允许访问
     *
     * @throws Exception
     */
    @Test
    public void accessTokenOk() throws Exception {
        final String accessToken = obtainAccessToken();
        log.info("access_token={}", accessToken);
        mockMvc.perform(get(ORDER_URI)
                .header("Authorization", "bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}