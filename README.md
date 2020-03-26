# shopa

- 用来练习微服务项目搭建
- 整合一些分布式技术

## 项目结构

```
shopa 顶级父模块
-- shopa-basics 基础设施
-- -- shop-basic-eureka eureka注册中心
-- shopa-service-api api公共模块
-- -- shopa-service-api-weixin 微信api模块
-- -- shopa-service-api-member 会员api模块
-- shopa-service-entity 实体类模块
-- -- shopa-service-entity-weixin 微信实体类模块
-- shopa-service-impl 接口实现模块
-- -- shopa-service-impl-weixin 微信接口实现模块
-- -- shopa-service-impl-member 会员接口实现模块
```