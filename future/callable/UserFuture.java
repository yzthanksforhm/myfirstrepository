package com.bjsxt.future.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class UserFuture implements Callable<String> {
	
	private String para;
	public UserFuture(String para) {
		this.para = para;
	}
	/**
	 * 真是业务逻辑
	 */
	@Override
	public String call() throws Exception {
		Thread.sleep(3000);
		String result = this.para+" complete!";
		return result;
	}

	public static void main(String[] args) throws Exception{
		String queryStr = "quert";
		//构造futureTask，并且传入需要真正进行业务逻辑处理的类，该类一定是实现了callable接口
		FutureTask<String> future = new FutureTask<>(new UserFuture(queryStr));
		//创建一个固定线程的线程池
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		//这里提交任务future,则开启线程执行realdata的call方法
			//submit与callable区别：第一个：sumbit可以传入实现callable接口的实例对象；第二个：submit有返回值
		Future<String> f = (Future<String>) executor.submit(future);
		System.out.println("请求完毕");
		
		/*System.out.println(f.get());//通过f可以判断是否执行完毕
		while(true){
			if(f.get()==null){
				System.out.println("当前任务执行完毕！");
				break;
			}
			
		}*/
		
		try {
			//此处可以做额外的业务操作，也就是主程序执行其他业务逻辑
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("数据："+future.get());//异步获取执行结果
		
		executor.shutdown();
		
	}
}
