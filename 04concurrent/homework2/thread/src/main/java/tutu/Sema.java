package tutu;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sema {

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Semaphore semaphore = new Semaphore(0);
        new Thread(new Worker(semaphore)).start();
        //while(Worker.result == 0){
        //    Thread.sleep(10);
        //}
        semaphore.acquire();
        System.out.println("CountDownLatch+线程 异步计算结果为："+ Worker.result);
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

    static class Worker implements Runnable{
        private Semaphore semaphore;
        private static int result;

        Worker(Semaphore semaphore){
            this.semaphore = semaphore;
        }
        @Override
        public void run() {
            try {

                result = sum();
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }

            semaphore.release();

        }
    }
}
