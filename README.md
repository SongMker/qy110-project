1.依赖关系
    common-->公共资源，不能有任何依赖
    config-->依赖于common
    eureka-management-->不能有依赖
    consumer-management-->依赖api(feign)
    provider-management-->依赖service 不能依赖config 业务逻辑不能写在controller，controller只做跳转
    model-->不依赖
    mapper-->依赖model
    service-->依赖mapper,config
    api-->依赖model
    provider-management-->依赖common
2.provider不关闭redis启动报错