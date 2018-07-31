package com.myke.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * 授权服务器
 * /oauth/authorize：授权端点。
 * /oauth/token：令牌端点。
 * /oauth/confirm_access：用户确认授权提交端点。
 * /oauth/error：授权服务错误信息端点。
 * /oauth/check_token：用于资源服务访问的令牌解析端点。
 * /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;


    /**
     * 配置OAuth2的客户端相关信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //从数据库中获取clientDetails信息
        clients.jdbc(dataSource);
    }


    /**
     * 配置身份认证器，token 存储方式等
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // token 存储到 jdbc
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                // 允许 GET、POST 请求获取 token，即访问端点：oauth/token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);


    }

    /**
     * 配置AuthorizationServer安全认证的相关信息，创建 ClientCredentialsTokenEndpointFilter 核心过滤器
     * <p>
     *
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();

        //公开/oauth/token的接口
        oauthServer
                .tokenKeyAccess("permitAll()")
                //allow check token;
                .checkTokenAccess("permitAll()");
    }

}

