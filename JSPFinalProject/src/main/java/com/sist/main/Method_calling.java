package com.sist.main;

import java.lang.reflect.Method;

class A{
	public void aaa() {
		System.out.println("aaa() Call...");
	}
}
public class Method_calling {
	public static void main(String[] args) {
		//이름을 모르는 메소드 호출할 때 쓰는 방법 -> 협업 시 매우 중요
		try {
			//A a=new A();
			Class clsName=Class.forName("com.sist.main.A");
			Object obj=clsName.getDeclaredConstructor().newInstance();
			//a.aaa();
			Method[] methods=clsName.getDeclaredMethods();
			methods[0].invoke(obj, null);
		} catch(Exception ex) {}
	}
}
