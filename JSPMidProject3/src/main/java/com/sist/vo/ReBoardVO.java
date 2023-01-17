package com.sist.vo;
import java.util.*;

public class ReBoardVO {
	/* 계층형 댓글,게시글
	group_id : 댓글 그룹 번호
	group_step : 그룹 내 출력 순서
	group_tab : 댓글/대댓글/대대댓글
	root : 댓글 소속
	depth : 포함 대댓글 갯수
	                     group_id   group_step    group_tab    root    depth 
        1AAAAAA             1           1             0          0       2           
          => 2BBBBBB        1           2             1          1       0
          => 3CCCCCC        1           3             1          1       1
             => 4DDDDDDDD   1           4             2          3       0
        EEEEEE              2           1             0
          =>KKKKKK          2           2             1
	*/
	private int no,hit,group_id,group_step,group_tab,root,depth;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getGroup_step() {
		return group_step;
	}
	public void setGroup_step(int group_step) {
		this.group_step = group_step;
	}
	public int getGroup_tab() {
		return group_tab;
	}
	public void setGroup_tab(int group_tab) {
		this.group_tab = group_tab;
	}
	public int getRoot() {
		return root;
	}
	public void setRoot(int root) {
		this.root = root;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
