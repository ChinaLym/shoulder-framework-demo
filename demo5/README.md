# 高级：进程通讯安全
> 无侵入、无感知式高可扩展性的 HTTP 通信自动加解密方案，轻松满足不同国家的政策监管。

下载本项目

```
git clone https://gitee.com/ChinaLym/shoulder-framework-demo
```

- 导入 `IDEA` 后，分别启动 [Demo5_Server.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo5%2FDemo5_Server.java)，[Demo5_Client.java](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo5%2FDemo5_Client.java)
> 如图
![demo5.png](demo5.png)

学习 [controller](src%2Fmain%2Fjava%2Fcom%2Fexample%2Fdemo5%2Fcontroller) 的

IDEA 打开 `[demo5.http](demo5.http)` 文件快速触发调用并体验

> 为了演示方便，demo 运行时会启动内嵌 redis，数据库，停止后自动结束，第二次启动redis端口冲突报错不影响使用~。

## 使用 & 原理说明

> 在需要加密保护的接口/字段上加 `@Sensitive` 注解，Shoulder 将在接口请求/响应时对其进行加解密

 [Shoulder - 通讯加密方案](https://gitee.com/ChinaLym/shoulder-framework/tree/master/shoulder-build/shoulder-base/shoulder-crypto-negotiation)

### 扩展
按照 Shoulder SPI 注入想要的加密算法类即可切换加密算法，如中国要求 SM 算法、美国要求AES算法。

