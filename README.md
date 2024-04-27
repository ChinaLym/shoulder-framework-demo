<h1 align="center"><img src="doc/img/logo.png" height="40" width="40" /><a href="https://github.com/ChinaLym/shoulder-framework-demo" target="_blank">Shoulder Framework Demo</a></h1>

# 📖介绍

多个使用了 [Shoulder-Framework](https://github.com/ChinaLym/shoulder-framework) 的演示项目，演示了 `Shoulder` 框架提供的常用能力。

# 🚀 快速开始
三步快速体验：下载、启动、体验

1. `git clone https://gitee.com/ChinaLym/shoulder-framework-demo` 或 `IDEA-new project-from version control` 下载本项目。
2. 打开对应的工程（如 `demo1`） ，将工程导入 `IDEA`/`ECLIPSE` 将demo运行在本地（默认8080端口）。
3. 根据其中的 `READE.ME` 或 `代码注释` 或 `.http 脚本`进行测试，跟着 demo 了解 shoulder 的能力

# 🧐 详细介绍
- [demo1](https://github.com/ChinaLym/shoulder-framework-demo/tree/main/demo1)（`常用能力`）
    - 介绍了 **[Shoulder](https://gitee.com/ChinaLym/shoulder-framework)** 框架基本使用，便于快速入门
    - 通过对比使用 `Shoulder` 与原生的 `Spring Boot` 来展示 Shoulder 提供的能力与快速开发。
    - 包含：日志、错误码、多语言翻译、返回值自动包装、异常自动拦截、数据存储加解密、前后端传输加解密
    - 系统监控
        - Spring Boot 自动监控
        - 轻松实现监控自己的线程池状态，同步到监控与报警中心 Prometheus，Grafana 展示

- [demo2](https://github.com/ChinaLym/shoulder-framework-demo/tree/main/demo2)（`数据库`）
    - 演示对数据库的增强和简化：仅需要实现接口，无需SQL开发即可获得基本的增删改查
    - 操作日志-DB存储
    - 激活并使用`字典`、`标签`、`批处理`、`异步校验&导入`、`导出`能力
    - 演示内嵌式DB，无需安装Mysql，直接本地使用数据库（H2）

- [demo3](https://github.com/ChinaLym/shoulder-framework-demo/tree/main/demo3)（`spring security`）
    - 演示安全认证相关
        - 用户名、密码登录
        - 手机短信登录
        - 验证码
        - (认证方式 token/session 一键切换)
    - 演示内嵌式redis，无需安装redis，直接本地使用

- [demo4](https://github.com/ChinaLym/shoulder-framework-demo/tree/main/demo4)（`spring security`）
    - 演示安全认证相关
        - 基于 JWT Token 的认证实现


- [demo5](https://github.com/ChinaLym/shoulder-framework-demo/tree/main/demo5)（`服务间安全通信`）
    - 应用间传输加解密【绝密！不可破解、不可伪造、不可抵赖！】
    - 完整的客户端与服务端实现，只需要在 `Spring` 的基础上知道一个 `@Sensitive` 注解即可实现整套功能

---

# 💗 贡献代码

欢迎各类型代码提交，不限于`优化代码格式`、`优化注释/JavaDoc`、`修复 BUG`、`新增功能`
，更多请参考 [如何贡献代码](CONTRIBUTING.MD)

# 📩 反馈 or 联系我

感谢小伙伴们的 **[🌟Star](https://gitee.com/ChinaLym/shoulder-framework/star)** 、 **🍴Fork** 、 **🏁PR**~ 

欢迎使用 `issue` 或 [cn_lym@foxmail.com](mailto:cn_lym@foxmail.com) 交流，如 留下你的建议、期待的新功能等~

### 👨‍💼 关于作者

多次参与 Alibaba 核心系统重构与设计，主导过多次 D11 级别大促保障，欢迎技术交流与简历投递～
- 该项目为作者在业余时间独立开发和维护的个人项目，非阿里巴巴官方产品。
