package redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import redis.service.Counter;
import redis.util.RedisLockUtil;
import redis.util.SpringContextHolder;

@SpringBootApplication
public class RedisApplication {



    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(RedisApplication.class, args);
        pubsub();
        //counter();
    }

    public static void counter() throws InterruptedException{
        Thread thread1 = new Thread(Counter::lockTest);
        Thread thread2 = new Thread(Counter::lockTest);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Thread thread3 = new Thread(Counter::lockTest);
        thread3.start();
        thread3.join();
    }

    public static void pubsub(){

    }

}
