package redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class PublishServiceImpl implements PublishService{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void publish(String topicName,String message) {
        stringRedisTemplate.convertAndSend(topicName, message);
    }
}
