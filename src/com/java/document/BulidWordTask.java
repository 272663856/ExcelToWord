package com.java.document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

import org.apache.commons.lang3.RandomUtils;

public class BulidWordTask extends RecursiveTask<Integer>{
	// 每个"小任务"最多
    private static final int MAX = 20; 
	private List<Map<String,String>> list;
	private String outPath;
	 public BulidWordTask(List<Map<String,String>> list,String outPath) {
	        this.list = list;
	        this.outPath = outPath;
	    } 
	@Override
	protected Integer compute() {
		// TODO Auto-generated method stub 
		 Integer sum = 0;
				if (list.size() <= MAX) {
					System.out.println(Thread.currentThread().getName()+"承担了"+list.size()+"份工作");  
					  //执行转换
						try{
							SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
//							System.out.println("start***************"+myFmt2.format(new Date()));
							for(Map<String,String> dataMap:list){
								MDoc mdoc = new MDoc();
								mdoc.createDoc(dataMap, outPath+"\\"+dataMap.get("ETTHH")+".doc");
							}
//							System.out.println("end.....终于结束了.."+myFmt2.format(new Date()));
						}catch(Exception e){
							e.printStackTrace();
					}
						sum=list.size();
				} else {
					// 将大任务分解成两个小任务
					int middle = list.size()/ 2;
//					System.out.println("middle="+middle);
					BulidWordTask left = new BulidWordTask(list.subList(0, middle), outPath);
					BulidWordTask right = new BulidWordTask(list.subList(middle, list.size()), outPath);
					// 并行执行两个小任务
					left.fork();
					right.fork();
					Integer leftResult = left.join(); 
					Integer rightResult = right.join(); 
	                sum = leftResult + rightResult; 
				}
				return sum;
	}
}
