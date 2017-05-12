package com.java.document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class MDoc {

	private Configuration configuration = null;

	public MDoc() {
		configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");
	}
    /**
     * 创建word文档
     * @param dataMap 要填入模本的数据文件
     * @param fileName
     * @throws UnsupportedEncodingException
     */
	public void createDoc(Map<String,String> dataMap,String fileName) throws UnsupportedEncodingException {
		//dataMap 要填入模本的数据文件
		//设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
		//这里我们的模板是放在com.havenliu.document.template包下面
		configuration.setClassForTemplateLoading(this.getClass(), "/com/java/document/template");
		Template t=null;
		try {
			//test.ftl为要装载的模板
			t = configuration.getTemplate("temp-data.ftl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//输出文档路径及名称
		File outFile = new File(fileName);
		Writer out = null;
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(outFile);
			OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");
			out = new BufferedWriter(oWriter); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
			t.process(dataMap, out);
			out.close();
			fos.close();
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
