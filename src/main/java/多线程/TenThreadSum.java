package 多线程;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TenThreadSum {

    static class Task{
        int r = 0;
        int s = 0;
        Task(int r){
            this.r = r * 10;
        }
        public int sum(){
            for(int i = 0; i < 10; i ++){
                s += (r + i);
            }
            return s;
        }

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int res = 0;
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i = 0; i < 10; i ++){
            Task task = new Task(i);
            FutureTask<Integer> futureTask = new FutureTask<>(task::sum);
            new Thread(futureTask).start();
            res += futureTask.get();
            countDownLatch.countDown();
        }
        try{
            countDownLatch.await();
            System.out.println(res);
        }catch (InterruptedException e){
        }
    }
}
