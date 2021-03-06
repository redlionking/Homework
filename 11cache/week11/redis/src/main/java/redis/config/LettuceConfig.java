package redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.service.MessageReceiver;

import java.io.Serializable;

@Configuration
public class LettuceConfig  extends CachingConfigurerSupport {

    //@Bean
    //public RedisTemplate<String, Object> redisTemplate1(LettuceConnectionFactory redisConnectionFactory) {
    //    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    //    redisTemplate.setKeySerializer(new StringRedisSerializer());
    //    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    //    redisTemplate.setConnectionFactory(redisConnectionFactory);
    //    return redisTemplate;
    //}

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
        //??????????????????chat?????????
        container.addMessageListener(listenerAdapter1, new PatternTopic("chat"));
        container.addMessageListener(listenerAdapter2, new PatternTopic("chat"));
        return container;
    }


    /**
     * ?????????????????????????????????????????????????????????????????????????????????????????????????????????
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter1(MessageReceiver receiver) {
        //???messageListenerAdapter ?????????????????????????????????????????????????????????????????????receiveMessage???
        //??????defaultListenerMethod????????????handleMessage
        return new MessageListenerAdapter(receiver, "receiverMessage");
    }

    @Bean
    MessageListenerAdapter listenerAdapter2(MessageReceiver receiver) {
        //???messageListenerAdapter ?????????????????????????????????????????????????????????????????????receiveMessage???
        //??????defaultListenerMethod????????????handleMessage
        return new MessageListenerAdapter(receiver, "receiverMessage");
    }
    /**
     * ???????????????template
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }






}

