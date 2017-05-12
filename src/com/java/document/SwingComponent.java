package com.java.document;
import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @author  
 *
 */
public class SwingComponent implements ActionListener {
	JFrame frame = new JFrame("");// 框架布局
	JTabbedPane tabPane = new JTabbedPane();// 选项卡布局
	Container con = new Container();//
	JLabel label1 = new JLabel("Excel路径");
	JLabel label2 = new JLabel("生成路径");
	JTextField text1 = new JTextField();// TextField 目录的路径
	JTextField text2 = new JTextField();// 文件的路径
	JButton button1 = new JButton("...");// 选择
	JButton button2 = new JButton("...");// 选择
	JFileChooser jfc = new JFileChooser();// 文件选择器
	JButton button3 = new JButton("转换");//
	
	SwingComponent() {
		//jfc.setCurrentDirectory(new File("d://"));// 文件选择器的初始目录定为d盘
		
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
		frame.setSize(400, 200);// 设定窗口大小
		frame.setContentPane(tabPane);// 设置布局
		label1.setBounds(10, 10, 100, 20);
		text1.setBounds(75, 10, 180, 20);
		button1.setBounds(260, 10, 50, 20);
		label2.setBounds(10, 35, 100, 20);
		text2.setBounds(75, 35, 180, 20);
		button2.setBounds(260, 35, 50, 20);
		button3.setBounds(150, 60, 60, 20);
		button3.setBackground(Color.ORANGE);
		button1.addActionListener(this); // 添加事件处理
		button2.addActionListener(this); // 添加事件处理
		button3.addActionListener(this); // 添加事件处理
		con.add(label1);
		con.add(text1);
		con.add(button1);
		con.add(label2);
		con.add(text2);
		con.add(button2);
		con.add(button3);
		frame.setVisible(true);// 窗口可见
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 使能关闭窗口，结束程序
		tabPane.add("", con);// 添加布局1
	}
	/**
	 * 事件监听的方法
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(button1)) {// 判断触发方法的按钮是哪个
			jfc.setFileSelectionMode(0);// 设定只能选择到文件夹
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;
			} else {
				File f = jfc.getSelectedFile();// f为选择到的目录
				text1.setText(f.getAbsolutePath());
			}
		}
		// 绑定到选择文件，先择文件事件
		if (e.getSource().equals(button2)) {
			jfc.setFileSelectionMode(1);// 设定只能选择到文件
			int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
			if (state == 1) {
				return;// 撤销则返回
			} else {
				File f = jfc.getSelectedFile();// f为选择到的文件
				text2.setText(f.getAbsolutePath());
			}
		}
		if (e.getSource().equals(button3)) {
			 String filePath=text1.getText();
			 String outPath=text2.getText();
			 if(StringUtils.isNotEmpty(filePath)&&StringUtils.isNotEmpty(outPath)){
				 System.out.println("excel路径=="+filePath); 
				 System.out.println("outPath路径=="+outPath);
				 button3.setEnabled(false);
				 int index = ChangeUtils.toChange(filePath,outPath);
				 button3.setEnabled(true);
				 JOptionPane.showMessageDialog(null, "成功处理"+index+"条数据", "提示", 1);
			 }else{
				 JOptionPane.showMessageDialog(null, "请选择文件", "提示", 2);
				 return;
			 }
			 
		}
	}
	public static void main(String[] args) {
		new SwingComponent();
	}
}