package com.myke.oauthtest;

import com.google.gson.Gson;
import com.myke.oauth2.Oauth2OauthServerApplication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * 1. 先启动 AuthCenterApplication 应用
 * 2. 后执行 test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Oauth2OauthServerApplication.class)
@Slf4j
public class SecurityOauth2ClientTest {

    @Autowired
    private Gson gson;

    //端口
    final static long PORT = 9070;
    //clientId
    final static String CLIENT_ID = "web";
    //clientSecret
    final static String CLIENT_SECRET = "web_secret";


    //获取accessToken 的URI
    final static String TOKEN_REQUEST_URI = "http://localhost:" + PORT + "/oauth/token?grant_type=client_credentials&username=" + "&scope=all";

    //资源 URL
    final static String ORDER_URI = "http://localhost:" + PORT + "/order/1";

    @Test
    public void getOrder() throws Exception {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("authorization", "Bearer " + getAccessToken());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> result = rest.exchange(ORDER_URI, HttpMethod.GET, entity, String.class, new Object[]{null});
        log.info("返回的结果={}", gson.toJson(result));
    }

    /**
     * 获取accessToken
     *
     * @return
     */
    private String getAccessToken() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.add("authorization", getBasicAuthHeader());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<OAuth2AccessToken> resp = rest.postForEntity(TOKEN_REQUEST_URI, entity, OAuth2AccessToken.class);
        if (!resp.getStatusCode().equals(HttpStatus.OK)) {
            throw new RuntimeException(resp.toString());
        }
        OAuth2AccessToken t = resp.getBody();
        log.info("accessToken={}", gson.toJson(t));
        log.info("the response, access_token: " + t.getValue() + "; token_type: " + t.getTokenType() + "; "
                + "refresh_token: " + t.getRefreshToken() + "; expiration: " + t.getExpiresIn()
                + ", expired when:" + t.getExpiration());
        return t.getValue();

    }

    /**
     * 构建header
     *
     * @return
     */
    private String getBasicAuthHeader() {
        String auth = CLIENT_ID + ":" + CLIENT_SECRET;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}