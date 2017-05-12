package com.java.document;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class ChangeUtils {
	private  static Logger log = Logger.getLogger(ChangeUtils.class); 
	/**
	 * 读取Excel数据
	 * @param filePath 路径
	 * @return
	 */
	public static List<Map<String,String>> readExcel(String filePath){
		// TODO 自动生成方法存根  
        File file = new File(filePath);  
        if(!file.exists()){  
            System.out.println("文件不存在");  
            return null;  
        }  
        ExcelUtils rf = new ExcelUtils();  
        return rf.readExcel(file);   
	}
	/**
	 * 拆分集合
	 * @param <T>
	 * @param resList  要拆分的集合
	 * @param count	每个集合的元素个数
	 * @return  返回拆分后的各个集合
	 */
	public static  <T> List<List<T>> split(List<T> resList,int count){
		
		if(resList==null ||count<1)
			return  null ;
		List<List<T>> ret=new ArrayList<List<T>>();
		int size=resList.size();
		if(size<=count){ //数据量不足count指定的大小
			ret.add(resList);
		}else{
			int pre=size/count;
			int last=size%count;
			//前面pre个集合，每个大小都是count个元素
			for(int i=0;i<pre;i++){
				List<T> itemList=new ArrayList<T>();
				for(int j=0;j<count;j++){
					itemList.add(resList.get(i*count+j));
				}
				ret.add(itemList);
			}
			//last的进行处理
			if(last>0){
				List<T> itemList=new ArrayList<T>();
				for(int i=0;i<last;i++){
					itemList.add(resList.get(pre*count+i));
				}
				ret.add(itemList);
			}
		}
		return ret;
	}
	/**
	 * 执行转换
	 * @param filePath
	 * @return 
	 */
	public static  int toChange(String filePath,String outPath){
		int index = 0;
		SimpleDateFormat myFmt2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
		System.out.println("start=" +myFmt2.format(new Date()));
		try {
			List<Map<String,String>> list = readExcel(filePath);
            System.out.println(list);
			BulidWordTask task = new BulidWordTask(list,outPath);
	        ForkJoinPool pool=new ForkJoinPool(5);  
	        Future<Integer> result  = pool.submit(task);   
			pool.shutdown(); 
			try {
				index = result.get();
				System.out.println("end=" +myFmt2.format(new Date()) + "sum="+result.get());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
//		ExecutorService es  = Executors.newFixedThreadPool(30);
//		List<List<Map<String,String>>> ret=split(list,10);
//		for(int i=0;i<ret.size();i++ ){
//			List<Map<String,String>> tlist =ret.get(i);
//			es.execute(new BulidWordThread(tlist,outPath));
//		} 
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		return index;
	}
}
