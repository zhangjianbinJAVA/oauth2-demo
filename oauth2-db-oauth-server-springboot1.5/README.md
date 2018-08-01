#### 授权服务器
> 与 oauth2-db-oauth-resource-springboot1.5 工程联合使用

####  client_credentials 模式
````
http://localhost:9070/oauth/token?grant_type=client_credentials&scope=all&client_id=web&client_secret=web_secret


响应结果：
{
    "access_token": "53bd3e15-3b5b-4655-b2f5-cc53d3913418",
    "token_type": "bearer",
    "expires_in": 359999,
    "scope": "all"
}
````

#### password 模式
````
http://localhost:9070/oauth/token?username=admin&password=admin&grant_type=password&scope=all&client_id=web_1&client_secret=web_secret

响应结果：
{
    "access_token": "ef444c22-353a-420c-9c96-8acf1363f874",
    "token_type": "bearer",
    "expires_in": 359999,
    "scope": "all"
}
````

#### 授权服务器端点
 - /oauth/authorize：授权端点。
 - /oauth/token：令牌端点。
 - /oauth/confirm_access：用户确认授权提交端点。
 - /oauth/error：授权服务错误信息端点。
 - /oauth/check_token：用于资源服务访问的令牌解析端点。
 - /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话。


#### 查询 token 客户端信息
http://localhost:9070/oauth/check_token?token=919722f7-a8d1-4d9b-9a30-1a56f371e64d