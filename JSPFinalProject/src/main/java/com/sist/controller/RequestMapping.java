package com.sist.controller;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) //저장기간 : 프로그램 종료시까지 유지
@Target(METHOD) //메소드 구분자
public @interface RequestMapping {
	public String value(); //구분자 찾아오는 메소드
}