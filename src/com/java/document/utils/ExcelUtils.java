package com.java.document.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
public class ExcelUtils {  
    /** 
     * 读取Excel数据 
     * @param file 
     */  
    public List<Map<String,String>> readExcel(File file){  
    	List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        try {  
            InputStream inputStream = new FileInputStream(file);  
            String fileName = file.getName();  
            Workbook wb = null;  
            if(fileName.endsWith("xls")){  
                wb = new HSSFWorkbook(inputStream);//解析xls格式  
            }else if(fileName.endsWith("xlsx")){  
                wb = new XSSFWorkbook(inputStream);//解析xlsx格式  
            }  
            Sheet sheet = wb.getSheetAt(0);//第一个工作表  
              
            int firstRowIndex = sheet.getFirstRowNum()+2;  
            int lastRowIndex = sheet.getLastRowNum(); 
            Map<String,Integer> keyMap =new HashMap<String,Integer>();
            String[] keys={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","AA","AB",  "AC",  "AD" , "AE" ,"AF" ,"AG" ,"AH" ,"AI", "AJ" , "AK", "AL" ,"AM", "AN" ,"AO", "AP", "AQ" ,"AR",  "AS",  "AT", "AU", "AV"}; 
            for(int i=0;i<keys.length;i++){
            	keyMap.put(keys[i].toUpperCase(),i);
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd");
            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){  
                Row row = sheet.getRow(rIndex); 
                Map<String,String> dataMap = new HashMap<String,String>();
                for(String str:keyMap.keySet()){
                		Cell cell = row.getCell(keyMap.get(str));
                		if(cell !=null){
                			cell.setCellType(Cell.CELL_TYPE_STRING);
                			dataMap.put(str+"TTHH", cell.getStringCellValue());
                		}else{
                			dataMap.put(str+"TTHH", "");
                		}
                }
                	
        		list.add(dataMap);
//                if(row != null){  
//                    int firstCellIndex = row.getFirstCellNum();  
//                    int lastCellIndex = row.getLastCellNum();  
//                    for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){  
//                        Cell cell = row.getCell(cIndex);  
//                        String value = "";  
//                        if(cell != null){  
//                            value = cell.toString();  
//                            System.out.print(value+"\t");  
//                        }  
//                    }  
//                    System.out.println();  
//                }  
            }  
        } catch (FileNotFoundException e) {  
            // TODO 自动生成 catch 块  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO 自动生成 catch 块  
            e.printStackTrace();  
        }  
        return list;
    }  
}  