package tutu;

import java.util.concurrent.*;

public class Future_ThreadPool {
    /**
     * FutureTask + ExecutorService
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，

        // 异步执行 下面方法
        ExecutorService executor = Executors.newSingleThreadExecutor();

        FutureTask<Integer> task  = new FutureTask<Integer>(
                ()->{
                    return sum();
                }
        );
        executor.submit(task);

        int result = task.get();

        // 确保  拿到result 并输出
        System.out.println("FutureTask+线程池 异步计算结果为："+result);
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
}
