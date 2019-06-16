##### NOTE
> eureka作为服务注册与发现,service1为调用方,service2为被调用方, zuul-service为网关调用,这里做一个伪集群.

1. 启动`discovery-server` 启动参数为 `spring.profiles.active = discovery1/discovery2`
2. 启动`service2` 启动参数为 `server.port=8081/8082/8083`
3. 启动`service1` 启动参数为 `server.port=8071/8072/8073`
4. 启动`zuul-service`

- 分别对以上服务进行校验
1. `discovery-server` `http://127.0.0.1:8761/`
2. `service1` `http://localhost:8071/service1Api/testZuul` 端口替换为8072/8073
3. `service2` `http://localhost:8081/service2Api/testZuul` 端口替换为8082/8083
4. `zuul-service` 校验 `http://localhost:8079/api/service2/service2Api/testZuul` 或者
    `http://localhost:8079/api/service1/service1Api/testZuul` 进行多次刷新，可以看到负载均衡的效果.
    
    
> TODO: zuul网关的登陆验证, 日志, 调用链zipkin, 自定义路由等