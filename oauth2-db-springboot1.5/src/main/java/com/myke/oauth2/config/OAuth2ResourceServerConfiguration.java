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
//        http
//                //配置order访问控制，必须认证过后才可以访问
//                .authorizeRequests().mvcMatchers("/order/**")
//                .authenticated();

        http.authorizeRequests()
                .antMatchers("/order/**").authenticated();

    }

}