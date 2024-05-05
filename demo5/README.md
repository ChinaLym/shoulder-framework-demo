# 🤩 Demo5：绝密通讯

> 无侵入、无感知式高可扩展性的 HTTP 通信自动加解密方案，轻松满足不同国家的政策监管。

- 下载 `dem5` 后分别启动 [Demo5_Server.java](src/main/java/com/example/demo5/Demo5_Server.java)，[Demo5_Client.java](src/main/java/com/example/demo5/Demo5_Client.java)
- 打开 `[demo5.http](demo5.http)` 体验
- 前往 [controller](src/main/java/com/example/demo5/controller) debug 学习使用

> 为了演示方便，demo 运行时会启动内嵌 redis，数据库，停止后自动结束，第二次启动redis端口冲突报错不影响使用~。

# 🛠️ 功能说明

> 在需要加密保护的接口/字段上加 `@Sensitive` 注解，Shoulder 将在接口请求/响应时对其进行加解密

 [Shoulder - 通讯加密方案](https://github.com/ChinaLym/shoulder-framework/tree/master/shoulder-build/shoulder-base/shoulder-crypto-negotiation)

# 🌟扩展
按照 Shoulder SPI 注入想要的加密算法类即可切换加密算法，如中国要求 SM 算法、美国要求AES算法。

`Instagram` 绝密的通信是基于 AES + DH，Shoulder 使用**性能更好更安全的加密算法** AES(或其他) + ECDH。


