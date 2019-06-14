package com.tec.test;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

public class Test8 {
	public static void main(String[] args) {
		testLambda();
		testisPresent();
		testDistinct();
		testFilter();
		testMap();
		testLimit();
		testSkip();
		testComprehensive();
		testFlatMap();
		testPredicate();
		testReduce();
	}
	private static void testReduce() {
		int result1 = Stream.of(1,2,3,4,5).reduce(2, (x,y)->x*y);
		System.out.println(result1);
		Optional<Integer> result2 = Stream.of(1,2,3,4,5).reduce((x,y)->x*y);
		System.out.println(result2.orElse(-1));
		
	}

	private static void testPredicate() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,67,87,123,21,32,99);
		List<Integer> newList = getNewList(list,Test8::cal);
		System.out.println(newList);
	}

	private static List<Integer> getNewList(List<Integer> list, Predicate<Integer> num) {
		return list.stream()
				.filter(num)
				.collect(toList());
	}

	private static boolean cal(Integer num) {
		return num % 3 == 0;
	}

	private static void testFlatMap() {
		List<String> words = Arrays.asList("hello world", "hello java", "hello hello");
		List<String> wordList = Arrays.asList("aa", "bb", "cc");
		/*words.stream()
				// 对words的每一项都做切割操作，把每一个字符串都转换为数组
				// 执行完后数据结构如下{["hello","world"],["hello","java"],["hello","hello"]}
				.map(item -> item.split(" "))
				// 对每个数组做单独的遍历操作
				.forEach(arr -> {
					for (String item : arr) {
						System.out.println(item);
					}
				});*/
		words.stream()
				// 把每一项转化为数组包含列表的Stream流
				// 然后这个函数把所有的List里面的字符串都取出来放在了一个集合中，这个集合做下一次执行的数据源
				// {"hello","world","hello","java","hello","hello"}
				.flatMap(item -> {
					String[] arr = item.split(" ");
					return Arrays.asList(arr).stream();
				})
				.collect(toList())
				// 根据每一项的HashCode和equals方法做去重操作
//				.distinct()
				// 打印每一项
				.forEach(item -> System.out.println(item));
		System.out.println("||||||||||||||||||||||||||||||||testFlatMap|||||||||||||||||||||||||||||");
	}

	private static void testComprehensive() {
		List<Integer> nums = Lists.newArrayList(1,1,null,2,3,4,null,5,6,7,8,9,10);
		System.out.println("sum is:"+nums.stream().filter(num -> num != null)
		                //1,1,2,3,4,5,6,7,8,9,10
		                //.peek(x -> System.out.println("peek0: "+x))
		                .distinct()
		                //1,2,3,4,5,6,7,8,9,10
		                .mapToInt(num -> num * 2)
		                //2,4,6,8,10,12
		                .skip(2)
		                //6,8,10,12,14,16,18,20
		                .limit(4)
		                .peek(System.out::println)
		                //6,8,10,12
		                .sum());
		                //36
		System.out.println("|||||||||||||||||||||||||||||||||testComprehensive||||||||||||||||||||||||||||");
	}
	/**
	 * 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
	 */
	private static void testSkip() {
		List<Integer> nums = Lists.newArrayList(18,2,3,4,5,6,7,50,100);
		nums.stream().skip(6).forEach(s->System.out.println(s));
		System.out.println("|||||||||||||||||||||||||||||||||testSkip||||||||||||||||||||||||||||");
	}

	private static void testLimit() {
		List<Integer> nums = Lists.newArrayList(18,2,3,4,5,6,7,50,100);
		nums.stream().limit(6).forEach(System.out::println);
		System.out.println("|||||||||||||||||||||||||||||||||testLimit||||||||||||||||||||||||||||");
	}

	private static void testMap() {
		List<Integer> nums = Lists.newArrayList(50,100);
//		nums.stream().peek(e -> System.out.println(e*100));
		nums.stream().map(n -> "成绩"+n.toString()).forEach(System.out::println);
		System.out.println("|||||||||||||||||||||||||||||||||||testMap||||||||||||||||||||||||||");
		
	}

	private static void testFilter() {
		List<Integer> nums = Lists.newArrayList(50,100);
		nums.stream().filter(s -> s > 90).map(r -> {
			if(r>90){
				return "优";
			} 
			return "其他";
		}).forEach(System.out::println);
		System.out.println("||||||||||||||||||||||||||||||||||||testFilter|||||||||||||||||||||||||");
	}

	private static void testDistinct() {
		List<String> names = Lists.newArrayList("A","B","A");
		names.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
		System.out.println("||||||||||||||||||||||||||||||||||||testDistinct|||||||||||||||||||||||||");
	}

	private static void testisPresent() {
		String str = "JASON";
//		String str = null;
		Optional<String> name = Optional.ofNullable(str);
		System.out.println(name.isPresent());
		name.ifPresent((value)->{
			System.out.println("name is : " + value);
	    });
		name.orElseGet(() -> "a");
		System.out.println("|||||||||||||||||||||||||||||||||||||testisPresent||||||||||||||||||||||||");
	}
	
	private static void testLambda() {
		List<String> names = Lists.newArrayList("A","B");
		names.stream().map((String n1) -> {return  n1.toLowerCase();}).collect(Collectors.toList());
		names.stream().map((n2) -> n2.toLowerCase()).collect(Collectors.toList());
		names.stream().map(String::toLowerCase).collect(Collectors.toList());
		System.out.println("|||||||||||||||||||||||||||||||||||||testLambda||||||||||||||||||||||||");
	}
	
}
