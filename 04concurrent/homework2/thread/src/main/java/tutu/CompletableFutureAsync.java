package tutu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureAsync {
    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        int results[] = new int[1];

        CompletableFuture cf = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return sum();
            }
        });



        System.out.println("CountDownLatch+线程 异步计算结果为："+ cf.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
