package tutu;

import java.util.concurrent.Exchanger;

public class Exchangers {
    private static int sum() {
        return fibo(36);
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        Exchanger exchanger = new Exchanger();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    exchanger.exchange(sum());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        int result = (int) exchanger.exchange(0);
        System.out.println("CountDownLatch+线程 异步计算结果为："+ result);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }
    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }
}
