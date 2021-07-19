package tutu;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Queue {
    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue();

        new Thread(() -> {
            try {
                queue.put(sum());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        int result = queue.take();

        System.out.println("CountDownLatch+线程 异步计算结果为："+ result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
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

}
