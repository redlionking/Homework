package tutu;

import java.util.concurrent.*;

public class CycleBarrier_whileANDsleep {
    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(new Barrier(cyclicBarrier)).start();
        // 确保  拿到result 并输出
        //Thread.sleep(1000);
        //while(Barrier.result==0){
        //    Thread.sleep(10);
        //}
        cyclicBarrier.await();
        System.out.println("CountDownLatch+线程 异步计算结果为："+ Barrier.result);
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

    static class Barrier implements Runnable{
        public static int  result;
        private CyclicBarrier cyclicBarrier;

        public Barrier(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }


        @Override
        public void run() {
            result = sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
