# 学习进程间安全通讯

## 加密通信
> 无侵入、无感知式高可扩展性的 HTTP 通信自动加解密方案，轻松满足不同国家的政策监管。

在需要加密保护的接口/字段上加 `@Sensitive` 注解，Shoulder 将在接口请求/响应时对其进行加解密

### demo 体验
- 借助Spring profile机制，将demo5应用使用 test1、test2 profile分别启动后进行测试
> 如图
![demo5.png](demo5.png)

### 原理说明
 [Shoulder - 通讯加密方案](https://gitee.com/ChinaLym/shoulder-framework/tree/master/shoulder-build/shoulder-base/shoulder-crypto-negotiation)

### 扩展
按照 Shoulder SPI 注入想要的加密算法类即可切换加密算法，如中国要求 SM 算法、美国要求AES算法。

