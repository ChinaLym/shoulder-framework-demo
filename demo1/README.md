# 🤩 Demo1：减少代码的开始

> 企业级封装：重温 Spring Boot WEB核心能力、体验 Shoulder 扩展思想，节省上万行代码！

- 下载后运行 `demo1` （默认8080端口）
- 进入 `com.example.demo1.controller` 目录，打开对应的类，参照类上的注解进行测试与查看。（在 IDE 中可按住 `ctrl` 并点击注释内 `url` 即可快速测试）

# 📂 目录指南

- log   打印日志、请求日志、http客户端日志、操作日志
- ex    处理异常与错误码
- response  统一响应格式
- convert   枚举参数自动转换
- validate  参数校验
- i18n      国际化 / 多语言翻译
- crypto    加解密：传输加解密、存储加解密
- current   线程相关，延迟任务、线程池增强器
- apidoc    接口文档

---

# ❓常见问题

### `xxx.propreties` 文件中写入中文后保存乱码？或显示为 unicode 格式？

解决方案：IDEA 可以为我们自动转化：
- 打开设置 `File -> Settings -> Editor -> File Encodings`
- 勾选 `Transparent native-to-ascii conversion`

---

# 🌟扩展

### 配套提效工具：快速创建一个包目录安排好的工程

单模块工程：可以通过 shoulder 提供的
maven [shoulder-archetype-simple](https://github.com/ChinaLym/shoulder-framework/tree/master/shoulder-archetype-simple)
快速创建

多模块工程：可以通过 shoulder-platform
提供的 [shoulder-platform-archetype](https://github.com/ChinaLym/shoulder-platform/tree/main/shoulder-platform-common/shoulder-platform-archetype)

代码生成器（根据数据库表，自动生成 controller、service、entity，带有基本的增删改查、前端界面的web工程）
[shoulder-generator](https://github.com/ChinaLym/shoulder-platform/tree/main/shoulder-generator)


# 👍 扩展：Spring & Shoulder 学习建议

## 推荐的学习目标

- 学会使用 `Spring Boot`/`Shoulder` 创建自己的工程
- 可以通过框架提供的配置项来更改框架提供的功能
- 理解实现原理
- 可以根据 `Spring` 或 `Shoulder` 框架预留的扩展点或接口来实现自己的功能
- 分享自己的想法和设计，为 `Shoulder`/`Spring Boot` 提交代码

注：可以根据自己的开发经验，选择性跳过一些基础的介绍~

## 推荐的学习顺序

Shoulder 是基于 Spring Boot 的，Shoulder 希望通过本项目帮助各位同学更好的学习 `Spring Boot`

### Spring Boot 基础知识介绍

为了更好地学习 Spring Boot 的使用，可以从以下开始。

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.3.2.RELEASE/reference/htmlsingle/#using-boot-devtools)

### Spring Boot 指南

Shoulder 框架是基于 Spring Boot 之上的，这里有 Spring 如何创建 Restful Web Service 的指南。

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)

* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)

* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
