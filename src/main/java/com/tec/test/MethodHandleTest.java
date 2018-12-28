/**
 *  软件版权：SUNLEI
 *  系统名称：java8
 *  文件名称：MethodHandleTest.java
 *  版本变更记录（可选）：修改日期2017年9月12日  上午11:27:45，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/** 
 * @Description:
 * <p>创建日期：2017年9月12日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class MethodHandleTest {

	class GrandFather{
		void thinking(){
			System.out.println("I 'm grandFather!");
		}
	}
	class Father extends GrandFather{
		void thinking(){
			System.out.println("I 'm father!");
		}
	}
	static class TestRef {
		static void test(){
			System.out.println("static method!");
		}
	}
	class Son extends Father{
		void thinking() {
			//实现祖父类的thinking(),打印 I 'm grandFather
			MethodType mt=MethodType.methodType(void.class);
			try {
				MethodHandle md=MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt,this.getClass());
				md.invoke(this);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		MethodHandleTest.Son son=new MethodHandleTest().new Son();
		son.thinking();// JDK 1.8 打印I 'm father!  JDK 1.7 打印 I 'm grandFather! 因为 JDK1.8环境下MethodHandles.lookup方法是调用者敏感的。
	}
}