# shopa

- 用来练习微服务项目搭建
- 整合一些分布式技术

## 项目结构

```
shopa 顶级父模块
-- shopa-basics 基础设施
-- -- shop-basic-eureka eureka注册中心
-- -- shopa-basic-zuul 网关
-- -- shopa-basic-sso 单点登录服务
-- shopa-common 公用模块
-- -- shopa-common-core
-- shopa-service-api api公共模块
-- -- shopa-service-api-weixin 微信api模块
-- -- shopa-service-api-member 会员api模块
-- -- shopa-service-api-goods 商品api模块
-- shopa-service-entity 实体类模块
-- -- shopa-service-entity-weixin 微信实体类模块
-- -- shopa-service-entity-member 会员服务实体类模块
-- -- shopa-service-entity-goods 商品服务实体类
-- shopa-service-impl 接口实现模块
-- -- shopa-service-impl-weixin 微信接口实现模块
-- -- shopa-service-impl-member 会员接口实现模块
-- -- shopa-service-impl-goods 商品接口实现服务
-- shopa-web
-- -- shopa-web-portal 门户
-- -- shopa-web-pay 支付web站
```

### 注册中心

- eureka

### 配置中心

使用apollo

<https://github.com/ctripcorp/apollo>

### 统一数据转换

- inputDTO
- outputDTO
- DO

### 服务

#### 微信服务

- 生成注册验证码，消息发送
- 验证验证码