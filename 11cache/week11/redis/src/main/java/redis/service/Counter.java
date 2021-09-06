package redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.util.RedisLockUtil;

@Service
public class Counter {
    private final static String LOCK = "redis_lock";


    private static RedisLockUtil redisLockUtil ;

    @Autowired
    public Counter(RedisLockUtil redisLockUtil){
        this.redisLockUtil = redisLockUtil;
    }

    private final static int EXPIRE = 3;

    private static int amount = 10;

    public static void lockTest(){
        System.out.println("lock test:: start sleep 10");

        if(!redisLockUtil.tryLock(LOCK, LOCK, EXPIRE)){
            System.out.println("获取锁失败");
            return ;
        }

        try {
            Thread.sleep(10000);
            amount -=1;
            System.out.println("库存减一 amount = " + amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisLockUtil.releaseLock(LOCK,LOCK);
        System.out.println("lock test::end");
    }
}
