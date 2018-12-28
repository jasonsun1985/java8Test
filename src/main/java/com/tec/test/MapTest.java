package com.tec.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTest {
	public static void main(String[] args) {
		Map<Integer,String> map = new HashMap<Integer,String>();
        for(int i=0; i<6; i++){
            map.put(i,"val_"+i);
        }
        map.put(10,null);
        //遍历
		map.forEach((key, value) -> System.out.println("key: " + key + " value: " + value));
		//getOrDefault(key,defaultValue):获取key值,如果key不存在则用defaultValue
		System.out.println(map.getOrDefault(1, "DEFAULT"));//val_1
		System.out.println(map.getOrDefault(11, "DEFAULT"));//DEFAULT
		///////////////////////////////////////////////////////////////////////////////////
		//putIfAbsent(K key, V value):根据key匹配Node,如果匹配不到则增加key-value,返回null,
		//如果匹配到Node,
		//	如果oldValue不等于null则不进行value覆盖，返回oldValue,
		//	如果oldValue等于null则进行value覆盖，返回null
		System.out.println(map.putIfAbsent(1, "1v"));//val_1
		System.out.println(map.get(1));//val_1
		System.out.println(map.putIfAbsent(10, "10v"));//null
		System.out.println(map.get(10));//10v
		System.out.println(map.putIfAbsent(11, "11v"));//null
		System.out.println(map.get(11));//11v
		///////////////////////////////////////////////////////////////////////////////////
		map.put(20,"remove");
		System.out.println(map.remove(9999, "remove"));//key value 同时满足才删除
		System.out.println(map.get(20));//remove
		System.out.println(map.remove(20, "remove"));//true
		System.out.println(map.get(20));//null
		///////////////////////////////////////////////////////////////////////////////////
		System.out.println(map.size());
		map.replaceAll((key,value)->{
			if(key==9999){
				return value+" OK";
			}
			return value;
		});
		Map<Integer,String> newMap = map.entrySet().stream().filter((e)->{
			return e.getKey()!=1;
		}).collect(Collectors.toMap((e) -> {return e.getKey();}, e -> e.getValue()));
		System.out.println(newMap.size());
		System.out.println(newMap.get(1));
		///////////////////////////////////////////////////////////////////////////////////
		System.out.println(map.get(1));
		map.compute(1, (k,v)->{return v.split("_")[0];});
		System.out.println(map.get(1));//val
		map.merge(1,"2",(k,v)->{return v.split("_")[0];});
		System.out.println(map.get(1));//val
		///////////////////////////////////////////////////////////////////////////////////
		Map<Integer,String> newMap1 = new HashMap<Integer,String>();
		newMap1.put(8888, "8888");
		Map<Integer,String> streamMap = Stream.concat(map.entrySet().stream(), newMap1.entrySet().stream()).collect(Collectors.toMap((m)->{return m.getKey();},(m)->{return m.getValue();}));
		streamMap.forEach((k,v)->System.out.println("key:" + k+" value:"+v));
		
		
		
	}
}
