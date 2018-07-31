package com.myke.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 构造器中传入了 /ipVerify 作为IP登录的端点
     */
    public IpAuthenticationProcessingFilter() {
        super(new AntPathRequestMatcher("/ipVerify"));
    }

    /**
     * 接收并解析用户凭证
     * <p>
     * attemptAuthentication()方法中加载请求的IP地址，之后交给内部的 AuthenticationManager 去认证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        //获取host信息
        String host = request.getRemoteHost();
        //交给内部的AuthenticationManager去认证，实现解耦
        return getAuthenticationManager().authenticate(new IpAuthenticationToken(host));
    }
}