package com.bjsxt.base.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class WangMin implements Delayed{
	 private String name;  
	    //身份证  
	    private String id;  
	    //截止时间  
	    private long endTime;  
	    //定义时间工具类
	    private TimeUnit timeUnit = TimeUnit.SECONDS;
	      
	    public WangMin(String name,String id,long endTime){  
	        this.name=name;  
	        this.id=id;  
	        this.endTime = endTime;  
	    }  
	      
	    public String getName(){  
	        return this.name;  
	    }  
	      
	    public String getId(){  
	        return this.id;  
	    }

	    /** 
	     * 用来判断是否到了截止时间 
	     */  
	    @Override  
	    public long getDelay(TimeUnit unit) { 
	        //return unit.convert(endTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	    	return endTime - System.currentTimeMillis();
	    }  
	  
	    /** 
	     * 相互批较排序用 
	     */  
	    @Override  
	    public int compareTo(Delayed delayed) {  
	    	WangMin w = (WangMin)delayed;  
	        return this.getDelay(this.timeUnit) - w.getDelay(this.timeUnit) > 0 ? 1:0;  
	    }   
	      
	    
	    
	}  