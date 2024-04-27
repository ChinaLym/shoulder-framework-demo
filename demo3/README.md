# 🤩 Demo3：学习 Spring Security
> 少量的Spring Security扩展实现多方式认证，跟着 Shoulder 学习使用 Spring Security 的最佳姿势

- 下载后运行 `demo3` （默认8080端口）
  - >为了演示方便，demo 运行时会启动内嵌 redis，h2 数据库，停止后自动结束。
- 打开 [http://localhost:8080/](http://localhost:8080) 

---

# 🛠️ 功能说明

建议根据以下的顺序了解 `Shoulder` 的使用，Shoulder-Security 主要包含了 Spring-Security 的基本装配，若需要自定义可以参照框架的方式替换。

- 学习 Spring-Security 认证逻辑
- 学习如何扩展 Spring-Security 的认证
- 使用灵活的验证码框架 Shoulder-Security-Code
- 学习单点登录SSO、Oauth2协议、JWT、OpenID
- 学习使用 github、QQ 等第三方登录方式认证
- 学习搭建认证服务器、资源服务器

---
### session 模式认证

> 当后端服务器是面向浏览器的 web 程序时候（如供用户通过浏览器/手机浏览器访问）,用户登陆后会重定向至 登录-密码错误页面 / 首页页面。

`pom.xml` 引入 `shoulder-starter-auth-session` 即可激活：

```xml
<dependency>
    <groupId>cn.itlym</groupId>
    <artifactId>shoulder-starter-auth-session</artifactId>
</dependency>
```          

对浏览器的额外支持如下：
- 基本的认证页面（登录页面）
- Session 的无效、过期策略
- 认证成功、失败、退出登录的处理
- 默认的待认证请求处理
- 对验证码进行默认配置

`Shoulder`提供了 spring security session 默认配置，达到开箱即用的效果，无需再次配置一堆需要学习的 Spring Security DSL 或 Bean。

且`Shoulder`的默认实现的均支持使用者自行替换，如自定义各种页面（如登录、注册、退出等）、各类请求url、请求页面参数、会话过期时间等

### 使用验证码校验

引入

```xml
<dependency>
    <groupId>cn.itlym</groupId>
    <artifactId>shoulder-starter-security-code</artifactId>
</dependency>
```

配置需要检查验证码的请求，默认提供了两种：图片验证码、短信验证码

假如登录(/login)、修改密码请求(/changePwd)需要校验图片验证码，只需配置

`shoulder.security.validate-code.image.urls=/login,/changePwd`

假如手机号登录(`/login/phone`)、修改身份证号(`/changeIdCard`)需要校验短信验证码，只需配置

`shoulder.security.validate-code.sms.urls=/login/phone,/changeIdCard`

### 自动登录-记住我

spring 是怎么实现的 ？
https://springdoc.cn/spring-security/servlet/authentication/rememberme.html
- UsernamePasswordAuthenticationFilter
- RememberMeServices
- PersistentTokenBasedRememberMeServices
- PersistentTokenRepository
- JdbcTokenRepositoryImpl

当新的业务需要校验验证码时，只需要配一下即可。

- 更多配置:
    - 图片验证码相关配置，如希望修改图片尺寸、字符个数等
        - `org.shoulder.security.code.img.config.ImageCodeProperties` 
    - 短信验证码相关配置，如希望修改短信长度等
        - `org.shoulder.security.code.sms.config.SmsCodeProperties`

扩展说明：
> shoulder 默认提供的两种验证码框架都是基于 `shoulder-security-code` 进行二次开发的，实际业务中可能有更多验证码的设计方式，
使用者可以基于 `shoulder-security-code` 可以快速地定制一套自己的、可扩展性强的验证码框架~


---

### token 模式认证
> 用户登陆后接口会返回 token场景

引入 `shoulder-starter-auth-token` ，注入自定义的 `ClientDetailsService` 即可，shoulder 会自动识别，并根据此来进行认证、授权。

与 session 相似的，仍然可以使用 `http://localhost:8080/authentication/form` 来完成认证（登录）

不一样的是：session为生成sessionId，请求时会根据sessionId取用户信息；而token是生成token，请求时，请求头中必须修携带token字段

