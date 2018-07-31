#### Spring Security OAuth2
OAuth 是一个开放标准，允许用户让第三方应用访问该用户在某一网站上存储的私密的资源
（如照片，视频，联系人列表），而不需要将用户名和密码提供给第三方应用。
OAuth允许用户提供一个令牌，而不是用户名和密码来访问他们存放在特定服务提供者的数据。

每一个令牌授权一个特定的网站在特定的时段内访问特定的资源。
这样，OAuth让用户可以授权第三方网站访问他们存储在另外服务提供者的某些特定信息

#### oauth2根据使用场景不同，分成了4种模式
- 授权码模式（authorization code）
- 简化模式（implicit）
- 密码模式（resource owner password credentials）
- 客户端模式（client credentials）


#### 参考
spring-security 4   
https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle/    


spring-security 5   
https://docs.spring.io/spring-security/site/docs/5.0.4.RELEASE/reference/htmlsingle/    

- spring security 5 对 PasswordEncoder 做了相关的重构

