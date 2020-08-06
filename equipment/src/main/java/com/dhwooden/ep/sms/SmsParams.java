package com.dhwooden.ep.sms;

import com.dhwooden.ep.modal.SmsTask;
import com.dhwooden.ep.util.Identities;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmsParams {
	
	private static final String supplierName = "supplierName";//供应商名称
	private static final String customerName = "customerName";//经销商名称
	private static final String salePersonName = "salePersonName";//销售员名称
	private static final String operatorName = "operatorName";//供应员名称
	private static final String oemNumber = "oemNumber";//OEM订单号
	

	
	
	private static void addTask(List<SmsTask> tasks,String cell,String tplId,Map<String,String> data){
		cell = StringUtils.trimToEmpty(cell);
		if(cell.length()==0 || cell.startsWith("0")){
			return;
		}
		SmsTask task = new SmsTask(
				Identities.uuid2(),
				cell, 
				SmsTemplate.buildText(tplId, data));
		tasks.add(task);
	}
}
