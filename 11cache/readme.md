# Redis-Cache	

## **8.（必做）**基于 Redis 封装分布式数据操作：

​	用了lettuce做的

​	简单的分布式锁：两个操作（加锁， 解锁）

​		加锁 ：setnx 原子性 单线程 保证了拿到锁唯一；

​		解锁 ：加锁时加了过期时间-确保不会阻塞 ，

​			         lua保证原子性 单线程 保证了拿到锁唯一，

​					 lua先判断是否是自己的那把锁，是就把锁删掉

## 9.（必做）基于 Redis 的 PubSub 实现订单异步处理

​	用了lettuce做的

###### 	基于topic来接受消息

​	publish： 直接发送就好   stringRedisTemplate.convertAndSend(topicName, message);	

​	subscribe：配置 监听适配器 监听容器。

```
@Bean
public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
    RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(connectionFactory);
    return redisTemplate;
}

@Bean
RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                        MessageListenerAdapter listenerAdapter1,MessageListenerAdapter listenerAdapter2) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    //订阅了一个叫chat的通道
    container.addMessageListener(listenerAdapter1, new PatternTopic("chat"));
    container.addMessageListener(listenerAdapter2, new PatternTopic("chat"));
    return container;
}


/**
 * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
 * @param receiver
 * @return
 */
@Bean
MessageListenerAdapter listenerAdapter1(MessageReceiver receiver) {
    //给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
    //不填defaultListenerMethod默认调用handleMessage
    return new MessageListenerAdapter(receiver, "receiverMessage");
}

@Bean
MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
    //给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
    //不填defaultListenerMethod默认调用handleMessage
    return new MessageListenerAdapter(receiver, "receiverMessage");
}
/**
 * 读取内容的template
 */
@Bean
StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
    return new StringRedisTemplate(connectionFactory);
}
```

