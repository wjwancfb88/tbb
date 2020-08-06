package com.dhwooden.ep.yzj;

public class YzjApps {


	//PC管理平台
	private static String APP_PC_MANAGE_ID = "500038030";
	private static String APP_PC_MANAGE_SECRET = "PC_MANAGE";

	
	//供应员管理
	private static String APP_MOBILE_GYY_ID = "53448803";
	private static String APP_MOBILE_GYY_SECRET = "MB_GYY";
	
	//销售员管理
	private static String APP_MOBILE_SALER_ID = "500000666";
	private static String APP_MOBILE_SALER_SECRET = "MB_SALER";
	
	//经销商门户
	private static String APP_MOBILE_CUSTOMER_ID = "500000510";
	private static String APP_MOBILE_CUSTOMER_SECRET = "MB_CUSTOMER";
	
	
	
	public static YzjApp MOBILE_GYY = new YzjApp(APP_MOBILE_GYY_ID, APP_MOBILE_GYY_SECRET);
	
	public static YzjApp MOBILE_SALER = new YzjApp(APP_MOBILE_SALER_ID, APP_MOBILE_SALER_SECRET);
	
	public static YzjApp MOBILE_CUSTOMER = new YzjApp(APP_MOBILE_CUSTOMER_ID, APP_MOBILE_CUSTOMER_SECRET);

	public static YzjApp PC_MANAGE = new YzjApp(APP_PC_MANAGE_ID, APP_PC_MANAGE_SECRET);
}
