package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //저장기간 : 프로그램 종료시까지 유지
@Target(METHOD) //메소드 구분
/* 어노테이션 = 인덱스
 * 	ex. @requestMapping("a")
 * 		public void display(){}
 * 		-> a 입력시 display() 찾음
 * */
public @interface RequestMapping {
	String value();
}
