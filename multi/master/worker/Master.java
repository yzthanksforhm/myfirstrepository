package com.bjsxt.multi.master.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Master {

	//应该有一个任务集合
	private ConcurrentLinkedDeque<Task> workQueue = new ConcurrentLinkedDeque<>();
	//使用普通的hashmap去装work
	private HashMap<String, Thread> workers = new HashMap<>();
	//使用一个concurrenthashtable装worker执行任务结果集
	private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();
	
	
	//构造方法
	public Master(Worker worker,int workerCount){
		//每一个对象都需要有master对象的引用：workerQueue用于任务领取，requestMap用于任务提交
		worker.setWorkQueue(this.workQueue);
		worker.setResultMap(this.resultMap);
		//初始化worker
		for(int i=0;i<workerCount;i++){
			//key表示每一个worker的名字;value表示线程执行对象
			this.workers.put("子节点: "+i, new Thread(worker));
		}
	}
	//提交方法
	public void submit(Task task){
		this.workQueue.add(task);
	}
	//执行方法：启动应用程序让所有worker工作
	public void execute(){
		for(Map.Entry<String,Thread> me : workers.entrySet()){
			me.getValue().start();
		}
	}
	public boolean isComplete() {
		if(workQueue.isEmpty()){
			return true;
		}
		return false;
	}
	public boolean isComplete1(){
		for(Map.Entry<String, Thread> me : workers.entrySet()){
			if(me.getValue().getState()!=Thread.State.TERMINATED){
				return false;
			}
		}
		return true;
	}
	public int getResult() {
		//返回结果集
		int ret = 0;
		for(Map.Entry<String, Object> me : resultMap.entrySet()){
			ret += (int) me.getValue();
		}
			
		return ret;
	}
}
