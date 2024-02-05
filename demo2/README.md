# 更舒服的DB访问：致敬 Spring Boot、Mybatis Plus，在其肩膀更上一层楼

下载本项目

```
git clone https://gitee.com/ChinaLym/shoulder-framework-demo
```

直接运行（默认8080端口）

进入 `com.example.demo2.controller` 目录，打开对应的类，参照类上的注解进行测试与查看。（为了方便初学者快速浏览，在 IDE 中按住 `ctrl` 点击注释内 `url` 即可测试与访问）

建议根据以下的顺序了解 `Shoulder` 的使用

## 功能介绍

**demo2** 工程包含数据库快捷开发，该部分提供了Mybatis、JPA的简单增强

- Mybatis 方式：
    - 引入了 `Mybatis Plus`，具体使用方法可以查看 mybatis-plus 官网
    - TODO 使用方式
- JPA：
    - 提供了常用的类型转换，如 varchar(jsonStr) 与 list、set、枚举类型转换

- 全局锁（分布式锁-基于数据库）

- 批处理
    快速实现一些业务的批量处理 / 导入 / 导出

## 扩展：
H2 数据库链接参数：https://ossezhu.medium.com/hibernate-h2-%E6%95%B0%E6%8D%AE%E5%BA%93%E8%BF%9E%E6%8E%A5%E9%85%8D%E7%BD%AE-url-%E8%A7%A3%E8%AF%BB-d237a29c544c