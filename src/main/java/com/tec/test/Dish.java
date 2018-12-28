/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：Dish.java
 *  版本变更记录（可选）：修改日期2017年9月26日  下午6:10:23，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.test;

/**
 * @Description:
 *               <p>
 *               创建日期：2017年9月26日
 *               </p>
 * @version V1.0
 * @author SUNLEI
 * @see
 */
public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;

	public Dish(String name, boolean vegetarian, int calories) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	@Override
	public String toString() {
		return name;
	}
}
