package com.tubaoapi.modules.utils;

import java.util.Random;
import java.util.UUID;


public class RandomNumberUtils {
	private static Random r	=new Random();
	
	
	public static int randomInt(){
		return r.nextInt(10000);
	}
	
	public static int randomInt(int n){
		return r.nextInt(n);
	}
	
}
