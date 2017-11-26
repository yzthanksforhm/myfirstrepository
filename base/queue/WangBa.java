package com.bjsxt.base.queue;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {

	private DelayQueue<WangMin> queue = new DelayQueue<WangMin>();

	public volatile boolean yinye = true;

	public void shangji(String name, String id, int money) {
		
		WangMin man = new WangMin(name, id, 1000 * money+ System.currentTimeMillis());
		
		System.out.println("网名" + man.getName() + " 身份证" + man.getId() + "交钱"
				+ money + "块,开始上机...");
		this.queue.add(man);
	}

	public void xiaji(WangMin man) {
		System.out.println("网名" + man.getName() + " 身份证" + man.getId()
				+ "时间到下机...");
	}

	@Override
	public void run() {
		while (yinye) {
			try {
				WangMin man = queue.take();
				xiaji(man);
				
				if(queue.isEmpty()){
					yinye = false;
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		try {
			System.out.println("网吧开始营业");
			WangBa siyu = new WangBa();
			siyu.shangji("路人甲", "123", 1);
			siyu.shangji("路人乙", "234", 10);
			siyu.shangji("路人丙", "345", 5);
			Thread shangwang = new Thread(siyu);
			shangwang.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}