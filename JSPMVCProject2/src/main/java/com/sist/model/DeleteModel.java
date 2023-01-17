package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DeleteModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("msg", "데이터를 삭제합니다");
		return "view/delete.jsp";
	}

}
