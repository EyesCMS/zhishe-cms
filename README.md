# zhishe-cms

[![Build Status](https://travis-ci.com/EyesCMS/zhishe-cms.svg?branch=dev)](https://travis-ci.com/EyesCMS/zhishe-cms)

backend code for zhishe cms

## 项目结构
```
zhishe-cms
├── cms-common -- 工具类及通用代码
├── cms-mbg -- MyBatisGenerator 生成的数据库操作代码
├── cms-security -- SpringSecurity 封装公用模块
├── cms-core -- 前台系统接口
└── cms-demo -- 框架搭建时的测试代码
```

## 如何运行
将项目使用 IDEA 打开，导入 pom 依赖，进入 `cms-demo` 目录，运行 `CmsDemoApplication`
