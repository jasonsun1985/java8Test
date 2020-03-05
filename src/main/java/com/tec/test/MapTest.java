package com.tec.test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
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
		System.out.println("1:" + map.getOrDefault(1, "DEFAULT"));//val_1
		System.out.println("2:" + map.getOrDefault(11, "DEFAULT"));//DEFAULT
		///////////////////////////////////////////////////////////////////////////////////
		//putIfAbsent(K key, V value):根据key匹配Node,如果匹配不到则增加key-value,返回null,
		//如果匹配到Node,
		//	如果oldValue不等于null则不进行value覆盖，返回oldValue,
		//	如果oldValue等于null则进行value覆盖，返回null
		System.out.println("3:" + map.putIfAbsent(1, "1v"));//val_1
		System.out.println("4:" + map.get(1));//val_1
		System.out.println("5:" + map.putIfAbsent(10, "10v"));//null
		System.out.println("6:" + map.get(10));//10v
		System.out.println("7:" + map.putIfAbsent(11, "11v"));//null
		System.out.println("8:" + map.get(11));//11v
		///////////////////////////////////////////////////////////////////////////////////
		map.put(20,"remove");
		System.out.println("9:" + map.remove(9999, "remove"));//key value 同时满足才删除
		System.out.println("10:" + map.get(20));//remove
		System.out.println("11:" + map.remove(20, "remove"));//true
		System.out.println("12:" + map.get(20));//null
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
		}).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
		System.out.println("13:"+newMap.size());
		System.out.println("14:"+newMap.get(1));
		///////////////////////////////////////////////////////////////////////////////////
		System.out.println("15:"+map.get(1));
		map.compute(1, (k,v)->{return v.split("_")[0];});
		System.out.println("16:"+map.get(1));//val
		map.merge(1,"2",(k,v)->{return v.split("_")[0];});
		System.out.println("17:"+map.get(1));//val
		///////////////////////////////////////////////////////////////////////////////////
		Map<Integer,String> newMap1 = new HashMap<Integer,String>();
		newMap1.put(8888, "8888");
		Map<Integer,String> streamMap = Stream.concat(map.entrySet().stream(), newMap1.entrySet().stream()).collect(Collectors.toMap((m)->{return m.getKey();},(m)->{return m.getValue();}));
		streamMap.forEach((k,v)->System.out.println("key:" + k+" value:"+v));
		BiFunction<Integer, String, String> computeFunction = new BiFunction<Integer, String, String>() {
			@Override
			public String apply(Integer t, String u) {
				return t+"_"+u.split("_")[1];
			}
		};
		Function<Integer, String> ifAbsentFunction = new Function<Integer, String>() {
			@Override
			public String apply(Integer t) {
				return t+"+"+t;
			}
		};
		
		map.compute(2, computeFunction);
		System.out.println("18:" + map.get(2));
		
		//如果map里没有这个key，那么就按照后面的这个function添加对应的key和value 如果有这个key，那么就不添加
		map.computeIfAbsent(2, ifAbsentFunction);
		System.out.println("19:"+map.get(2));
		
		//如果map里有这个key，那么function输入的v就是现在的值，返回的是对应value，如果没有这个key，那么输入的v是null
		map.computeIfPresent(200, computeFunction);
		System.out.println("20:" + map.get(200));
		
		
	}

	/**
	 * @Description:
	 * 创建人：SUNLEI, 2019年6月27日 上午11:01:13
	 * 修改人：SUNLEI, 2019年6月27日 上午11:01:13
	 * @param k
	 * @param v
	 * @return 
	 * @return Object  
	 * @throws
	 */
	private static String computeBi(Integer k, String v) {
		return k+"_"+v.split("_")[1];
	}
}
