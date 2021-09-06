package redis;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private  RedisTemplate<String, Serializable> redisTemplate;


    @Test
    void testLettuce() {
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set("key","string");
        redisTemplate.opsForValue().set("key2",123456);
        System.out.println(redisTemplate.opsForValue().get("key"));
        System.out.println(redisTemplate.opsForValue().get("key2"));


    }


    @Test
    void jedis(){
        Jedis jedis = new Jedis("192.168.121.131",6379);
        System.out.println(jedis.ping());
        System.out.println(jedis.get("key"));

    }

}
