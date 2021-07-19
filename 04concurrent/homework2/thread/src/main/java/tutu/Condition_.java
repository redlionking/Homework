package tutu;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_ {
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }


    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        ReentrantLock rlock = new ReentrantLock();
        Condition condition = rlock.newCondition();

        int[] result = new int[1];

        new Thread(()->{

            try{
              //  rlock.lock();
                result[0]=(sum());
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
              //  rlock.unlock();
            }

        }).start();

        condition.await();

        System.out.println("CountDownLatch+线程 异步计算结果为："+ result[0]);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");



    }
}
