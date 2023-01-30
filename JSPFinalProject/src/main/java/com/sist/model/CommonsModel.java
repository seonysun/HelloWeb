package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;

public class CommonsModel {
	//모든 .do에서 실행 가능한 static 메소드 등록
	public static void footerData(HttpServletRequest request) {
		NoticeDAO ndao=new NoticeDAO();
		List<NoticeVO> nlist=ndao.noticeTop5();
		request.setAttribute("nList", nlist);
	}
}
