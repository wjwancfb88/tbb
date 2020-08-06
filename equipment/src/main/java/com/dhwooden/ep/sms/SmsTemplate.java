package com.dhwooden.ep.sms;

import java.util.HashMap;
import java.util.Map;

public class SmsTemplate {
	
	public static final Map<String,String> TEMPLATES = new HashMap<>();
	//OEMAPP -------------------------------------------------------
	//完工
	public static final String SITUATION_ALL_TO_SUPPLIER = "1808834";
	public static final String SITUATION_ALL_TO_CUSTOMER = "1808828";
	public static final String SITUATION_ALL_TO_SALEPERSON = "1808820";
	//交货
	public static final String DELIVERY_ALL_TO_SALEPERSON = "1808844";
	public static final String DELIVERY_ALL_TO_CUSTOMER = "1808840";
	
	
	//OA板材供应商向浸渍纸供应商发起OA订货流程
	public static final String OA_SUPPLIER_BUY_JZZ = "1905606";
	
	//CUSTOMORDER -------------------------------------------------------
	
	public static final String CUSTOMORDER_SUPPLIERCONFIRM_TO_CUSTOMER = "1935796";//加工中心确认通知经销商
	public static final String CUSTOMORDER_SUPPLIERCONFIRM_TO_PERSON = "1935826";//加工中心确认通知业务员
	
	public static final String CUSTOMORDER_PRODUCTIONCOMPLETE_TO_CUSTOMER = "1935838";//加工中心完工通知经销商
	public static final String CUSTOMORDER_PRODUCTIONCOMPLETE_TO_PERSON = "1935846";//加工中心完工通知业务员
	
	public static final String CUSTOMORDER_SEND_TO_CUSTOMER = "1935886";//加工中心发货经销商
	public static final String CUSTOMORDER_SEND_TO_PERSON = "1935912";//加工中心发货通知业务员
	
	
	public static final String CUSTOMORDER_DELIVERY_TO_SUPPLIER = "1938578";//经销商签收通知供应商
	public static final String CUSTOMORDER_DELIVERY_TO_PERSON = "1938580";//经销商签收通知业务员
	public static final String ENTRY_TO_USER = "2409052";//家居产业园出入登记
	public static final String ATTENTION_TO_USER = "2443624";//家居产业园出入登记
	public static final String ATTENTION_TO_USER2 = "3381418";//武康本部出入登记

	public static final String QUICK_REPLY = "3545898";//快速回复信息模板
	public static final String QUICK_REPLY_MEET = "3545768";//快速回复信息模板
	public static final String QUICK_REPLY_AGREE = "3551974";//快速回复信息模板
	public static final String QUICK_REPLY_OUTSIDE = "3551988";//快速回复信息模板

	//尊敬的经销商,您的OEM订单#oemNumber#已全部完工,请及时关注该订单交货情况
	static {
		TEMPLATES.put(ATTENTION_TO_USER, "【兔宝宝家居产业园】温馨提示：欢迎您进入兔宝宝家居产业园！本园区内禁止吸烟，车辆限速5码。爱护园区环境，需要您的参与和支持。");//完工后，通知供应商
		TEMPLATES.put(ATTENTION_TO_USER2, "【兔宝宝】温馨提示：欢迎您进入德华兔宝宝！本公司内禁止吸烟，车辆限速5码。爱护公司环境，需要您的参与和支持。");//完工后，通知供应商
		TEMPLATES.put(ENTRY_TO_USER, "【兔宝宝】你有访客:#name#在#address#传达室等候，联系电话#phone#,请点击以下链接快速回复#url#");//完工后，通知供应商
		TEMPLATES.put(SITUATION_ALL_TO_SUPPLIER, "【兔宝宝】#customerName#OEM订单#oemNumber#已全部完工,请及时交货");//完工后，通知供应商
		TEMPLATES.put(SITUATION_ALL_TO_CUSTOMER, "【兔宝宝】尊敬的经销商,您的OEM订单#oemNumber#已全部完工,请及时关注该订单交货情况");//完工后，通知经销商
		TEMPLATES.put(SITUATION_ALL_TO_SALEPERSON, "【兔宝宝】#customerName#OEM订单#oemNumber#已全部完工,请及时关注#supplierName#交货情况");//交货后，通知销售员
		
		TEMPLATES.put(DELIVERY_ALL_TO_SALEPERSON, "【兔宝宝】#customerName#OEM订单#oemNumber#已由#supplierName#全部交货"); //交货后，通知销售员
		TEMPLATES.put(DELIVERY_ALL_TO_CUSTOMER, "【兔宝宝】尊敬的经销商,您的OEM订单#oemNumber#已由#supplierName#发货,请及时签收");//交货后，通知经销商
		
		TEMPLATES.put(OA_SUPPLIER_BUY_JZZ, "【兔宝宝】#buySupplierName#发了一个OA订货流程，流程号为#oaNumber#,请及时处理");
		
		//CUSTOMORDER -------------------------------------------------------
		
		TEMPLATES.put(CUSTOMORDER_SUPPLIERCONFIRM_TO_CUSTOMER, "【兔宝宝】尊敬的易装经销商，您的易装订单#orderNumber#由#supplierName#确认并开始生产，请及时关注");
		TEMPLATES.put(CUSTOMORDER_SUPPLIERCONFIRM_TO_PERSON, "【兔宝宝】#personName#，#customerName#的易装订单#orderNumber#由#supplierName#确认开始生产，请及时关注");
		
		TEMPLATES.put(CUSTOMORDER_PRODUCTIONCOMPLETE_TO_CUSTOMER, "【兔宝宝】尊敬的易装经销商，您的易装订单#orderNumber#,#supplierName#已生产完工，请及时关注");
		TEMPLATES.put(CUSTOMORDER_PRODUCTIONCOMPLETE_TO_PERSON, "【兔宝宝】#personName#，#customerName#的易装订单#orderNumber#已由#supplierName#生产完工，请及时关注");
		
		TEMPLATES.put(CUSTOMORDER_SEND_TO_CUSTOMER, "【兔宝宝】尊敬的易装经销商，您的易装订单#orderNumber#，#supplierName#已发货，请登录系统查看物流");
		TEMPLATES.put(CUSTOMORDER_SEND_TO_PERSON, "【兔宝宝】#personName#，#customerName#的易装订单#orderNumber#已由#supplierName#发货，请登录系统查看物流");
		
		TEMPLATES.put(CUSTOMORDER_DELIVERY_TO_SUPPLIER, "【兔宝宝】尊敬的易装加工中心，#customerName#的易装订单#orderNumber#已经签收，请及时关注");
		TEMPLATES.put(CUSTOMORDER_DELIVERY_TO_PERSON, "【兔宝宝】#personName#,#customerName#的易装订单#orderNumber#已经签收，请及时关注");

		TEMPLATES.put(QUICK_REPLY,"【兔宝宝】不好意思,我正在忙...稍后回复");
		TEMPLATES.put(QUICK_REPLY_MEET,"【兔宝宝】不好意思,我正在开会...稍后回复");
		TEMPLATES.put(QUICK_REPLY_AGREE,"【兔宝宝】我已知晓,同意放行!");
		TEMPLATES.put(QUICK_REPLY_OUTSIDE,"【兔宝宝】不好意思,我临时有事出去了,下次再会");
	}
	
	public static String buildText(String tplId,Map<String,String> data){
		String text = TEMPLATES.get(tplId);
		for (Map.Entry<String, String> entry : data.entrySet()) {
			text = text.replaceAll("#"+entry.getKey()+"#", entry.getValue());
		}
		return text;
	}
}
