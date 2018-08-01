#### 授权资源服务器 
> 与 oauth2-db-oauth-server-springboot1.5 工程联合使用

#### 配置 oauth-server 属性,见 TokenInfoServicesConfiguration 类
```
### 配置 oauth-server 的 check_token 地址
security.oauth2.resource.token-info-uri=http://localhost:9070/oauth/check_token
### 配置 客户端 id
security.oauth2.client.client-id=web
### 配置 客户端 秘钥
security.oauth2.client.client-secret=web_secret
```

#### 验证 token 合法性,见 RemoteTokenServices 类 的 loadAuthentication 方法


####  带 token 访问资源
1.访问 http://localhost:9170/order/1
```
{
    "error": "unauthorized",
    "error_description": "Full authentication is required to access this resource"
}
```

2.访问 http://localhost:9170/order/1?access_token=919722f7-a8d1-4d9b-9a30-1a56f371e64d
```
order id : 1
```