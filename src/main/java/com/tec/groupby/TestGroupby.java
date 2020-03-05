package com.tec.groupby;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

public class TestGroupby {
    public static void main(String[] args) {
//        group();
//        getSummarizingInt();
		test2();
    }

    private static void getSummarizingInt() {
        ArrayList<User> persons = Lists.newArrayList(new User(1, "a", 11), new User(2, "b", 12), new User(3, "c", 18), new User(4, "b", 19));
        IntSummaryStatistics collect = persons.stream().
                collect(Collectors.summarizingInt(a -> a.getAge()));
        System.out.println(collect.getAverage());
        System.out.println(collect.getCount());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
        System.out.println(collect.getSum());
    }

    public static void group() {
		List<User> list = Arrays.asList(
				new User(1, "a", 10),
				new User(4, "d", 19),
				new User(5, "e", 13),
				new User(2, "b", 14),
				new User(3, "a", 10),
				new User(6, "f", 16));
        Map<Integer, List<User>> collect = list.stream().collect(Collectors.groupingBy(User::getAge));
        Map<Integer, Map<Integer, List<User>>> c2 = list.stream()
                .collect(Collectors.groupingBy(User::getId, Collectors.groupingBy(User::getAge)));
        System.out.println(
                list.stream().collect(Collectors.groupingBy(User::getId, Collectors.groupingBy(User::getAge))));
        System.out.println("groupingByConcurrent:  " + list.stream().collect(Collectors.groupingByConcurrent(User::getId)));
        collect.forEach((k, v) -> {
            v.forEach(vv -> {
                System.out.println("k:" + k + " v: " + vv.toString());
            });
        });

        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(result);
    }

    public static void test2() {
        List<User> list = Arrays.asList(
                new User(1, "a", 10),
                new User(4, "d", 19),
                new User(5, "e", 13),
                new User(2, "b", 14),
                new User(3, "a", 10),
                new User(6, "f", 16));
        Map<String, User> m = new HashMap<>();
        list.stream().forEach(u ->{
             m.put(u.getName() + u.getAge(), u);
        });
        System.out.println(m);
        System.out.println(m.size());
//		Map<String, List<User>> listResult =
//                list.stream().collect(Collectors.groupingByConcurrent(User::getName));
//        f1.apply(list);
//        list.stream().collect(Collectors.groupingBy());
//        listResult.s
//		System.out.println(listResult);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.parse("20200305", DateTimeFormatter.ofPattern("yyyyMMdd")).toString());
    }
}
