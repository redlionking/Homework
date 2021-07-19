package tutu;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class CountDownLatch_member {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Count(countDownLatch)).start();
        countDownLatch.await();
        // 确保  拿到result 并输出
        System.out.println("CountDownLatch+线程 异步计算结果为："+Count.result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程

    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    static class Count implements Runnable{
        public static int  result;
        private CountDownLatch countDownLatch;

        public Count(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }


        @Override
        public void run() {
            result = sum();
            countDownLatch.countDown();
        }
    }
}
