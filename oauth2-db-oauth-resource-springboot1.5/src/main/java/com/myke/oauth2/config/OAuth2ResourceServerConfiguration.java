package com.myke.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 *
 * @author： zhangjianbin <br/>
 * ===============================
 * Created with IDEA.
 * Date： 2018/7/26
 * Time： 14:43
 * ================================
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    /**
     * 配置哪些资源需要认证后再能访问
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated();

        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();

    }


    /**
     * 参考 ResourceServerTokenServicesConfiguration 类
     * <p>
     * 也可以配置属性来实现 token 认证
     *
     * @return
     */
//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices() {
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        // 对 token校验的　授权服务器地址
//        tokenService.setCheckTokenEndpointUrl("http://localhost:9070/oauth/check_token");
//        tokenService.setClientId("web");
//        tokenService.setClientSecret("web_secret");
//        return tokenService;
//    }


}
