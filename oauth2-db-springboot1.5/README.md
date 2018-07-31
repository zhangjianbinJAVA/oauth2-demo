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

#### 访问资源
http://localhost:9070/order/1?access_token=53bd3e15-3b5b-4655-b2f5-cc53d3913418


#### password 模式
````
http://localhost:9070/oauth/token?username=admin&password=admin&grant_type=password&scope=all&client_id=web_1&client_secret=web_secret

响应结果：
{
    "access_token": "88eb6f16-a03f-4879-988d-a1a834f240d5",
    "token_type": "bearer",
    "expires_in": 359999,
    "scope": "all"
}
````

