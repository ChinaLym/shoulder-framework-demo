# 🤓 Demo6 在 Spring Boot 怀里触摸 Shoulder

> Shoulder 是 Spring Boot 的可插拔扩展，完全符合 Spring Boot 官方定义的 Starter 要求以及 Spring Boot 思想。

你甚至不需要查看该项目源码，只需要在你的 Spring Boot 工程中复制以下内容到 `pom.xml` 中即可使用摘要日志能力，一个注解记录日志。

## 🚀 Spring Boot 3.x 中使用
```pom.xml
        <dependency>
            <groupId>cn.itlym</groupId>
            <artifactId>shoulder-starter-operation-log</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
```

## 🚀 Spring Boot 2.x 中使用
```pom.xml
        <dependency>
            <groupId>cn.itlym</groupId>
            <artifactId>shoulder-starter-operation-log</artifactId>
            <version>0.7.1</version><!-- shoulder-version -->
        </dependency>
```

## 🌟 关于操作日志更高级的玩法见 [demo1](../demo1/src/main/java/com/example/demo1)

- ⚡ 异步记录
- 🌐 多语言
- 📦️ 批处理记录
- ⚙️ 扩展字段
- 📁 多种存储
- 🎶 多种格式
- ...


----

# 💬 更多 

- 异步记录：日志太多也不怕，不用担心高并发下性能问题了
- 记录操作人：登录账号、ip、操作终端等完整记录，畅爽分析。
- 适配多语言：一条记录，多种语言展示。
- 跨线程记录：完美适配 Spring @Async。
- 记录多个被操作对象：甚至 n 个对象记录 m 条，自由掌控。
- 记录扩展字段：你的日志你定义！
- 可扩展的存储：记录在文件？数据库？或是上报给日志服务？统统支持
- 可扩展的记录格式：key=value？Json？Xml？统统支持，甚至自定义，如 Spring 一样使用丝滑。