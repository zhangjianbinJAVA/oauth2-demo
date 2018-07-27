package com.myke.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    /**
     * jdbc 存储 token
     *
     * @return
     */
    @Bean
    TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 密码明文验证
     *
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置 UserDetailsService,并设置数据源
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    /**
     * 顶级身份管理者AuthenticationManager
     * <p>
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**", "/druid/*").permitAll()
                .antMatchers("/**").permitAll();
        // @formatter:on
    }
}