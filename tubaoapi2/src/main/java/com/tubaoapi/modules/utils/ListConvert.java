package com.tubaoapi.modules.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * 
 *
 * @param <S> Source Object
 * @param <T> Target Object
 */
public abstract class ListConvert<S,T>{
	public List<T> convert(Collection<S> list){
		List<T> newList = new ArrayList<T>();
		for(S s:list){
			newList.add(convertObject(s));
		}
		return newList;
	}
	
	public abstract T convertObject(S s);
}
