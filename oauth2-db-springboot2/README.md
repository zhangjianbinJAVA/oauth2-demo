####  client_credentials 模式
````
http://localhost:9070/oauth/token?grant_type=client_credentials&scope=all&client_id=web&client_secret=web_secret


响应结果：
{
    "access_token": "69e01cb4-6735-4a89-bc5b-c6ef455b650c",
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
    "access_token": "88eb6f16-a03f-4879-988d-a1a834f240d5",
    "token_type": "bearer",
    "expires_in": 359999,
    "scope": "all"
}
````