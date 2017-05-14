package com.java.document.utils;


import org.apache.commons.lang3.RandomUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 作废
 * @author wangjy
 *
 */
public class BulidWordThread extends Thread{
	private List<Map<String,String>> list;
	private String outPath;
	 public BulidWordThread(List<Map<String,String>> list,String outPath) {
	        this.list = list;
	        this.outPath = outPath;
	    } 

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try{
			System.out.println(list);
			SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
			System.out.println(getName()+"start***************"+myFmt2.format(new Date()));
			for(Map<String,String> dataMap:list){
				MDoc mdoc = new MDoc();
				mdoc.createDoc(dataMap, outPath+"\\"+System.currentTimeMillis()+RandomUtils.nextInt()+".doc");
			}
			System.out.println(getName()+"end.....终于结束了.."+myFmt2.format(new Date()));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
