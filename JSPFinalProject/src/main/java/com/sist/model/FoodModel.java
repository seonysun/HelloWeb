package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;
import com.sist.vo.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class FoodModel {
	@RequestMapping("food/food_location.do")
	public String food_location(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../food/food_location.jsp");
		return "../main/main.jsp";
	}
}
