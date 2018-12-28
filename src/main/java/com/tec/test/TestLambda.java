/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：TestLambda.java
 *  版本变更记录（可选）：修改日期2017年8月30日  上午11:09:09，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.DoubleFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;


/** 
 * @Description:
 * <p>创建日期：2017年8月30日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestLambda {
	public static void main(String[] args) {
		Runnable r1 = () -> {
			System.out.println("Hello Lambda!");
		};
		r1.run();
		List<String> names = new ArrayList<>();
		names.add("TaoBao");
		names.add("JD");
		String str = "hello ";
		List<String> lowerNames = names.stream().map(name -> {
			return str + name.toLowerCase();
		}).collect(Collectors.toList());
		List<String> lowerNames2 = names.stream().map(String::toUpperCase)
				.collect(Collectors.toCollection(ArrayList::new));
		System.out.println(names);// [TaoBao, JD]
		System.out.println(lowerNames);// [hello taobao, hello jd]
		System.out.println(lowerNames2);// [hello taobao, hello jd]
		List<Integer> listNum = Lists.newArrayList(100, 98, 74, 56, null);
		System.out.println(listNum.stream().filter(num -> {
			return num != null && num > 60;
		}).collect(Collectors.toList()));// [100, 98, 74]
		System.out.println(listNum.stream().filter(nu -> nu != null && nu > 60).collect(Collectors.toList()));// [100,

		System.out.println(listNum.stream().allMatch(num -> {
			return num != null;
		}));// false
		// List<People> list = new ArrayList<People>();
		List<People> list = Arrays.asList(new People("jason", 30), new People("tom", 17), new People("jack", 50));
		System.out.println(list.stream().filter((People people) -> {
			return people.getAge() > 18;
		}).collect(Collectors.toList()).get(0).getName());
		System.out.println(
				list.stream().filter((People o) -> o.getAge() < 18).collect(Collectors.toList()).get(0).getName());
		System.out.println("年龄最大的名字：" + list.stream().max(new Comparator<People>() {
			@Override
			public int compare(People o1, People o2) {
				return o1.getAge() - o2.getAge();
			}
		}).get().getName());

		list.stream().sorted((one, two) -> ((People) one).getAge() - ((People) two).getAge())
				.forEach(System.out::println);
		long sum = list.stream().filter(p1 -> p1.getAge() > 18).mapToInt(p1 -> p1.getAge()).count();
		System.out.println("大于18岁的人数量 ： " + sum);
		int sum2 = list.stream().filter(p2 -> p2.getAge() > 0).mapToInt(p2 -> p2.getAge()).sum();
		System.out.println("大于0岁所有人年龄的总和是：" + sum2);
		List<String> lName = list.stream().filter(p3 -> p3.getAge() > 18).map(p3 -> p3.getName())
				.collect(Collectors.toList());
		System.out.println("大于18岁的人名字： " + lName);

		// Map<String,String> map = new HashMap<>();
		List<Integer> l = Lists.newArrayList(10, 20, 30, 100, 45, 88);
		l.stream().sorted((x, y) -> x.compareTo(y)).forEach(System.out::println);
		int index = Arrays.binarySearch(new int[] { 1, 3, 4, 5, 6, 7, 8, 9 }, 1);
		System.out.println(index);
		System.out.println("MAX : " + l.stream().max(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}).get());

		String[] names1 = { "Eric", "John", "Alan", "Liz" };
		String[] names2 = { "Eric", "John", "Alan", "Liz" };
		String[] copyNames2 = Arrays.copyOf(names2, 2);
		System.out.println(Lists.newArrayList(copyNames2));
		System.out.println(Arrays.equals(names1, names2));

		int[][] stuGrades1 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };
		int[][] stuGrades2 = { { 80, 81, 82 }, { 84, 85, 86 }, { 87, 88, 89 } };
		System.out.println(Arrays.deepEquals(stuGrades1, stuGrades2));

		int[] array1 = new int[8];
		// 填充数组
		Arrays.fill(array1, 1);
		// [1, 1, 1, 1, 1, 1, 1, 1]
		System.out.println(Arrays.toString(array1));
		// 将数组的第2和第3个元素赋值为8
		Arrays.fill(array1, 2, 4, 8);
		// [1, 1, 8, 8, 1, 1, 1, 1]
		System.out.println(Arrays.toString(array1));
		// 对数组的第2个到第6个进行排序进行排序
		Arrays.sort(array1, 1, 5);
		System.out.println(Arrays.toString(array1));

		int[] array2 = array1.clone();
		System.out.println("克隆后数组元素是否相等:Arrays.equals(array1, array2): " + Arrays.equals(array1, array2));
		System.out.println("元素8在array1中的位置：Arrays.binarySearch(array1, 8): " + Arrays.binarySearch(array1, 8));

		// 调用工厂方法创建Optional实例
		Optional<String> name = Optional.of("Sanaulla");
		// 传入参数为null，抛出NullPointerException.
		System.out.println(name.orElse("There is no value present!"));
		System.out.println(name.orElseGet(() -> "Default Value"));
		Optional empty = Optional.ofNullable(null);
		// isPresent方法用来检查Optional实例中是否包含值
		if (empty.isPresent()) {
			// 在Optional实例内调用get()返回已存在的值
			System.out.println(empty.get());// 输出Sanaulla
		}
		System.out.println(empty.orElse("There is no value present!"));
		System.out.println(empty.orElseGet(() -> "Default Value"));

		// GoogleTable
		Table<Integer, Integer, String> table = HashBasedTable.create();
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 5; column++) {
				table.put(row, column, "value of cell (" + row + "," + column + ")");
			}
		}
		for (int i = 0; i < table.rowMap().size(); i++) {
			Map<Integer, String> rowData = table.row(i);
			for (int j = 0; j < rowData.size(); j++) {
				System.out.println("cell(" + i + "," + j + ") value is:" + rowData.get(j));
			}
		}

		String[] strArray = new String[] { "Ruijie", "HUAWEI", "H3C" };
		System.out.println(Arrays.stream(strArray));
		List<String> ls = Arrays.stream(strArray).collect(Collectors.toList());
		System.out.println(ls);
		ls.stream().forEach(System.out::println);
		System.out.println(ls.stream().collect(Collectors.joining("-")).toString());

		IntStream.of(new int[] { 1, 2, 3 }).forEach(System.out::println);
		IntStream.range(1, 6).forEach(System.out::println);
		IntStream.rangeClosed(1, 6).forEach(System.out::println);

		Stream<List<Integer>> inputS = Stream.of(Arrays.asList(1), Arrays.asList(1, 2), Arrays.asList(1, 2, 3));
		Stream<Integer> outputS = inputS.flatMap(listS -> listS.stream()).map(n -> n * 30);
		outputS.forEach(r -> System.out.println("flatMap " +r));

		// Predicate<People> youngAndGood =
		StringJoiner joiner = new StringJoiner(",", "(", ")");
		joiner.add("a").add("b").add("c");
		System.out.println(joiner.toString());
		StringJoiner joiner2 = new StringJoiner("|");
		joiner2.add("日期").add("金额").merge(joiner);
		System.out.println(joiner2.toString());

		List<Integer> listN = Arrays.asList(1, 3, 4, 5, 6, 67);
		// Stream.iterate(1, item -> item +
		// 1).limit(10).forEach(System.out::println);

		listN.stream().peek(e -> System.out.println("peek" + e + 100));
		// listN.stream().

		TestLambda testLambda = new TestLambda();
		System.out.println("integrate result : " + testLambda.integrate((double x) -> x, 2, 8));

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800), new Dish("beef", false, 700),
				new Dish("chicken", false, 400), new Dish("french fries", true, 530), new Dish("rice", true, 350),
				new Dish("season fruit", true, 120), new Dish("pizza", true, 550), new Dish("prawns", false, 300),
				new Dish("salmon", false, 450));
		// 选取卡路里小于400的菜名
		List<String> menuNameList = menu.stream().filter(k -> k.getCalories() < 400)
				.sorted((x, y) -> x.getCalories() - y.getCalories()).map(n -> n.getName()).collect(Collectors.toList());
		System.out.println(menuNameList);
		Optional<Integer> o = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce((a, b) -> a + b);
		System.out.println(o.get());
		
		System.out.println("***************************************************************************");
		System.out.println("***************************************************************************");
		/*
			(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
			(2) 交易员都在哪些不同的城市工作过？
			(3) 查找所有来自于剑桥的交易员，并按姓名排序。
			(4) 返回所有交易员的姓名字符串，按字母顺序排序。
			(5) 有没有交易员是在米兰工作的？
			(6) 打印生活在剑桥的交易员的所有交易额。
			(7) 所有交易中，最高的交易额是多少？
			(8) 找到交易额最小的交易。
		 */
		Trader raoul = new TestLambda().new Trader("Raoul", "Cambridge");
		Trader mario = new TestLambda().new Trader("Mario", "Milan");
		Trader alan = new TestLambda().new Trader("Alan", "Cambridge");
		Trader brian = new TestLambda().new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(
				new TestLambda().new Transaction(brian, 2011, 300),
				new TestLambda().new Transaction(raoul, 2012, 1000),
				new TestLambda().new Transaction(raoul, 2011, 400),
				new TestLambda().new Transaction(mario, 2012, 710),
				new TestLambda().new Transaction(mario, 2012, 700),
				new TestLambda().new Transaction(alan, 2012, 950));
		//(1)
		transactions.stream().filter(t -> t.getYear() == 2011).sorted((x, y) -> x.getValue() - y.getValue()).forEach(System.out::println);
		//(2)
		transactions.stream().map(t->t.getTrader().getCity()).distinct().forEach(System.out::println);
		//(3)
		transactions.stream().map(t->t.getTrader().getCity().equals("Cambridge")).distinct().sorted().forEach(System.out::println);
		//(4)
		System.out.println(transactions.stream().map(t->t.getTrader().getName()).distinct().sorted().reduce("",(n1,n2)->n1+","+n2));
		//(5)
		System.out.println(transactions.stream().allMatch(t->t.getTrader().getCity().equals("Milan"))); //why false?
		//(6)
//		System.out.println("6: " +transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).mapToInt(t->t.getValue()).sum());
		transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).forEach(System.out::println);
		//(7)
		System.out.println(transactions.stream().map(t->t.getValue()).reduce(Integer::max).get());
		//(8)
		System.out.println(transactions.stream().map(t->t.getValue()).min(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		}).get());
		System.out.println(transactions.stream().sorted(new Comparator<Transaction>() {
			@Override
			public int compare(Transaction o1, Transaction o2) {
				return o1.getValue()-o2.getValue();
			}
		}).collect(Collectors.toList()).get(0));
		
		List<Dish> listMap = Arrays.asList(new Dish("pork", false, 800), new Dish("beef", false, 700),
				new Dish("chicken", false, 400), new Dish("french fries", true, 530), new Dish("rice", true, 350),
				new Dish("season fruit", true, 120), new Dish("pizza", true, 550), new Dish("prawns", false, 300),
				new Dish("salmon", false, 450));
//		Map<String, Integer> dishPriceMap = listMap.stream().collect(Collectors.toMap(Dish::getName,Dish::getCalories));
//		Map<String, Integer> dishPriceMap = listMap.stream().collect(Collectors.toMap((Dish d)->{return d.getName();},(d)->{return d.getCalories();}));
		Map<String, Integer> dishPriceMap = listMap.stream().collect(Collectors.toMap((d)->d.getName(),(d)->d.getCalories()));
		dishPriceMap.forEach((k,v)->System.out.println("key: " + k+ " Value : " + v));
	}
	
	public double integrate(DoubleFunction<Double> f , double a, double b) {
		return (f.apply(a)+f.apply(b))*(b-a)/2.0;
	}

	public class Trader {
		private final String name;
		private final String city;

		public Trader(String n, String c) {
			this.name = n;
			this.city = c;
		}

		public String getName() {
			return this.name;
		}

		public String getCity() {
			return this.city;
		}

		public String toString() {
			return "Trader:" + this.name + " in " + this.city;
		}
	}

	public class Transaction {
		private final Trader trader;
		private final int year;
		private final int value;

		public Transaction(Trader trader, int year, int value) {
			this.trader = trader;
			this.year = year;
			this.value = value;
		}

		public Trader getTrader() {
			return this.trader;
		}

		public int getYear() {
			return this.year;
		}

		public int getValue() {
			return this.value;
		}

		public String toString() {
			return "{" + this.trader + ", " + "year: " + this.year + ", " + "value:" + this.value + "}";
		}
	}

}
