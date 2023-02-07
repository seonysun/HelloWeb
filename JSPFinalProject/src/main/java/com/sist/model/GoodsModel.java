package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class GoodsModel {
	@RequestMapping("goods/goods_all.do")
	public String goodsAll(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		GoodsDAO dao=new GoodsDAO();
		ArrayList<GoodsVO> list=dao.goodsAllListData(curpage);
		int totalpage=dao.goodsAllTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("main_jsp", "../goods/goods_all.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/goods_best.do")
	public String goodsBest(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		GoodsDAO dao=new GoodsDAO();
		ArrayList<GoodsVO> list=dao.goodsBestListData(curpage);
		int totalpage=dao.goodsBestTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("main_jsp", "../goods/goods_best.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/goods_sp.do")
	public String goodsSpecial(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		GoodsDAO dao=new GoodsDAO();
		ArrayList<GoodsVO> list=dao.goodsSpListData(curpage);
		int totalpage=dao.goodsSpTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("main_jsp", "../goods/goods_sp.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/goods_new.do")
	public String goodsNew(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		GoodsDAO dao=new GoodsDAO();
		ArrayList<GoodsVO> list=dao.goodsNewListData(curpage);
		int totalpage=dao.goodsNewTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage) endPage=totalpage;
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("main_jsp", "../goods/goods_new.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/goods_detail.do")
	public String goods_detail(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		GoodsDAO dao=new GoodsDAO();
		GoodsVO vo=dao.goodsDetailData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../goods/goods_detail.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	//장바구니 session에 저장
	/*
	@RequestMapping("goods/cart_insert.do")
	public String goods_cart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String no=request.getParameter("no");
		String account=request.getParameter("account");
		String total=request.getParameter("total");
		List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
		int cno=1;
		if(list==null) { //초기 list 없으므로 세팅
			list=new ArrayList<CartVO>();
			cno=1;
		} else { //수량 하나씩 추가
			int max=1;
			for(CartVO c:list) {
				if(max<c.getBno())
					max=c.getBno();
			}
			cno=max+1;
		}
		CartVO vo=new CartVO();
		vo.setBno(cno);
		vo.setGno(Integer.parseInt(no));
		vo.setId(id);
		vo.setAccount(Integer.parseInt(account));
		vo.setBuy_ok("n");
		vo.setTotal_price(Integer.parseInt(total));
		vo.setRegdate(new Date());
		list.add(vo);
		session.setAttribute("cart", list);
		return "redirect:cart_list.do";
	}
	
	@RequestMapping("goods/cart_list.do")
	public String goods_cart_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
		GoodsDAO dao=new GoodsDAO();
		if(list!=null) {
			for(CartVO vo:list) {
				GoodsVO g=dao.goodsDetailData(vo.getGno());
				vo.getGvo().setGoods_name(g.getGoods_name());
				vo.getGvo().setGoods_poster(g.getGoods_poster());
				vo.getGvo().setGoods_price(g.getGoods_price());
			}
			request.setAttribute("list", list);
			request.setAttribute("count", list.size());
		} else {
			request.setAttribute("count", 0);
		}
		request.setAttribute("mypage_jsp", "../goods/cart_list.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/cart_cancel.do")
	public String goods_cart_cancel(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String bno=request.getParameter("bno");
		List<CartVO> list=(List<CartVO>)session.getAttribute("cart");
		for(CartVO vo:list) {
			if(vo.getBno()==Integer.parseInt(bno)) {
				list.remove(vo);
				break;
			}
		}
		session.setAttribute("cart", list);
		return "redirect:cart_list.do";
	}
	 */
	
	//장바구니 DB에 저장
	@RequestMapping("goods/cart_insert.do")
	public String goods_cart(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String account=request.getParameter("account");
		String total=request.getParameter("total");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		CartVO vo=new CartVO();
		vo.setGno(Integer.parseInt(no));
		vo.setId(id);
		vo.setAccount(Integer.parseInt(account));
		vo.setTotal_price(Integer.parseInt(total));
		CartDAO.goodsCartInsert(vo);
		return "redirect:cart_list.do";
	}
	
	@RequestMapping("goods/cart_list.do")
	public String goods_cart_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		List<CartVO> list=CartDAO.goodsCartListData(id);
		System.out.println(list.size());
		if(list.size()>0) {
			request.setAttribute("count", list.size());
		    request.setAttribute("list", list);
		} else
			request.setAttribute("count", 0);
		request.setAttribute("mypage_jsp", "../goods/cart_list.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("goods/cart_cancel.do")
	public String goods_cart_cancel(HttpServletRequest request, HttpServletResponse response) {
		String bno=request.getParameter("bno");
		CartDAO.goodsCartDelete(Integer.parseInt(bno));
		return "redirect:cart_list.do";
	}
}
