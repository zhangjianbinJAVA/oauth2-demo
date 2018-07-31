package com.myke.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 设计概要：
 * 1. IpAuthenticationProcessingFilter-->UsernamePasswordAuthenticationFilter
 * <p>
 * 2. IpAuthenticationToken-->UsernamePasswordAuthenticationToken
 * <p>
 * 3. ProviderManager-->ProviderManager
 * <p>
 * 4. IpAuthenticationProvider-->DaoAuthenticationProvider
 * <p>
 * 5. ConcurrentHashMap-->UserDetailsService
 *
 * @author： zhangjianbin <br/>
 * ===============================
 * Created with IDEA.
 * Date： 2018/7/30
 * Time： 14:27
 * ================================
 */
@SpringBootApplication
public class IpLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpLoginApplication.class, args);
    }
}
