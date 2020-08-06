/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.dhwooden.ep.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author calvin
 */
public class Identities {

	private static SecureRandom random = new SecureRandom();
	private static char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	public static int randomInt(int n){
		return Math.abs(random.nextInt(n));
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}
	
	public static String randomNumberString(int length){
		StringBuilder sb = new StringBuilder();
		int l = numbers.length;
		for(int i=0;i<length;i++){
			sb.append(numbers[randomInt(l)]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(Identities.randomInt(10));
	}
}
