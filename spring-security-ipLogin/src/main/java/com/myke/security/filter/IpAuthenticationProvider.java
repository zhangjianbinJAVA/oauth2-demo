package com.myke.security.filter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟 从数据库中查询 当前 ip 地址的权限
 */
public class IpAuthenticationProvider implements AuthenticationProvider {

    final static Map<String, SimpleGrantedAuthority> ipAuthorityMap = new ConcurrentHashMap<String, SimpleGrantedAuthority>();

    //维护一个ip白名单列表，每个ip对应一定的权限
    static {
        ipAuthorityMap.put("127.0.0.1", new SimpleGrantedAuthority("ADMIN"));
        ipAuthorityMap.put("10.236.69.103", new SimpleGrantedAuthority("ADMIN"));
        ipAuthorityMap.put("10.236.69.104", new SimpleGrantedAuthority("FRIEND"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IpAuthenticationToken ipAuthenticationToken = (IpAuthenticationToken) authentication;
        String ip = ipAuthenticationToken.getIp();
        SimpleGrantedAuthority simpleGrantedAuthority = ipAuthorityMap.get(ip);
        //不在白名单列表中
        if (simpleGrantedAuthority == null) {
            return null;
        } else {
            //封装权限信息，并且此时身份已经被认证
            return new IpAuthenticationToken(ip, Arrays.asList(simpleGrantedAuthority));
        }
    }

    //只支持 IpAuthenticationToken该身份
    @Override
    public boolean supports(Class<?> authentication) {
        return (IpAuthenticationToken.class
                .isAssignableFrom(authentication));
    }
}