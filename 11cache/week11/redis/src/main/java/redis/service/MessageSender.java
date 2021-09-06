package redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class MessageSender {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * interval 2 s chat channel
     */
    @Scheduled(fixedDelay = 2000)
    public void sendMessage(){
        stringRedisTemplate.convertAndSend("chat", String.valueOf(Math.random()));
    }
}
