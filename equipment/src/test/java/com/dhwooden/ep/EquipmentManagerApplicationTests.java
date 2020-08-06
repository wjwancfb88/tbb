package com.dhwooden.ep;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentManagerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void printtxt() {
		System. setProperty("java.awt.headless", "false");
		JFileChooser fileChooser = new JFileChooser(); //创建打印作业
		int state = fileChooser.showOpenDialog(null);
		if(state == fileChooser.APPROVE_OPTION){
			File file = new File("G:\\test.txt"); //获取选择的文件
			//nnnnn
			HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			//设置打印格式，因为未确定类型，所以选择autosense
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			//查找所有的可用的打印服务
			PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			//定位默认的打印服务
			PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
			//显示打印对话框
			PrintService service = ServiceUI.printDialog(null, 200, 200, printService,
					defaultService, flavor, pras);
			if(service != null){
				try {
					DocPrintJob job = service.createPrintJob(); //创建打印作业
					FileInputStream fis = new FileInputStream(file); //构造待打印的文件流
					DocAttributeSet das = new HashDocAttributeSet();
					Doc doc = new SimpleDoc(fis, flavor, das);
					job.print(doc, pras);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
