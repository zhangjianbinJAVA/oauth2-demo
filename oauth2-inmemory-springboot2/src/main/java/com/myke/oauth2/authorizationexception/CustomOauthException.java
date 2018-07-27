package com.myke.oauth2.authorizationexception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.myke.oauth2.authorizationexception.serializer.CustomOauthExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 自定义登录失败异常信息
 * <p>
 * 添加自定义异常类，指定json序列化方式
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    public CustomOauthException(String msg) {
        super(msg);
    }
}