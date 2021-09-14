# 1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群。

## 主从

slaveof ip port

判断成功：get数据一致

## sentinel

sentinel monitor mymaster 172.25.254.101 6379 2

Sentinel 去监视一个名为 mymaster 的主服务器, 这个主服务器的IP 地址为 172.25.154.101,端口号为 6379 ,而将这个主服务器判断为失效至少需要 2 个 Sentinel 同意(只要同意 Sentinel 的数量不达标,自动故障迁移就不会执行)

redis-sentinel /etc/redis/sentinel.conf

## Cluster

redis-trib.rb create --replicas 1 127.0.0.1:7001 127.0.0.1:7002 127.0.0.1:7003 127.0.0.1:7004 127.0.0.1:7005 127.0.0.1:7006

# 6.（必做）搭建 ActiveMQ 服务，基于 JMS，写代码分别实现对于 queue 和 topic 的消息生产和消费，代码提交到 github。

