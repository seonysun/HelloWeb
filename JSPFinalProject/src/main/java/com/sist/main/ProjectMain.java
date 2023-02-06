package com.sist.main;
import java.util.*;
import java.sql.*;

public class ProjectMain {
    private Connection conn;
    private PreparedStatement ps;
    private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
    public ProjectMain() {
	    try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
	    } catch(Exception ex) {}
    }
    public void getConnection() {
	    try {
		    conn=DriverManager.getConnection(URL,"hr","happy");
	    }catch(Exception ex){}
    }
    public void disConnection() {
	    try {
		    if(ps!=null) ps.close();
		    if(conn!=null) conn.close();
	    } catch(Exception ex) {}
    }
    //굿즈 수량 세팅
    public void goodsAccountInsert(int no, int account) {
	    try {
		    getConnection();
		    String sql="UPDATE goods_all SET "
				    +"account=? "
				    +"WHERE no=?";
		    ps=conn.prepareStatement(sql);
		    ps.setInt(1, account);
		    ps.setInt(2, no);
		    ps.executeUpdate();
	    } catch(Exception ex) {
		    ex.printStackTrace();
	    } finally {
		    disConnection();
	    }
    }
    //예약가능일 세팅
    public List<Integer> foodNumberData(){
    	List<Integer> list=new ArrayList<Integer>();
    	try {
    		getConnection();
    		String sql="SELECT fno FROM food_location";
    		ps=conn.prepareStatement(sql);
    		ResultSet rs=ps.executeQuery();
    		while(rs.next()) {
    			list.add(rs.getInt(1));
    		}
    		rs.close();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		disConnection();
    	}
    	return list;
    }
    public String reserve_day() {
    	String s="";
    	int[] com=new int[(int)(Math.random()*10)+7];
    						//갯수 7~17 사이 랜덤 발생
    	int su=0;
    	boolean bCheck=false;
    	for(int i=0;i<com.length;i++) {
    		bCheck=true;
    		while(bCheck) {
    			su=(int)(Math.random()*30)+1;
    				//1~31일 사이 랜덤 발생
    			bCheck=false;
    			for(int j=0;j<i;j++) {
    				if(com[j]==su) {
    					bCheck=true;
    					break;
    				}
    			}
    		}
    		com[i]=su;
    	}
    	for(int i:com) {
    		s+=i+",";
    	}
    	s=s.substring(0,s.lastIndexOf(","));
    	return s;
    }
    public void foodReserveDayInsert(int fno, String reserve_day) {
    	try {
    		getConnection();
    		String sql="UPDATE food_location "
    				+ "SET reserve_day=? "
    				+ "WHERE fno=?";
    		ps=conn.prepareStatement(sql);
    		ps.setString(1, reserve_day);
    		ps.setInt(2, fno);
    		ps.executeUpdate();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		disConnection();
    	}
    }
    //예약가능시간 세팅
    public String reserve_time() {
    	String s="";
    	int[] com=new int[(int)(Math.random()*5)+5];
    	int su=0;
    	boolean bCheck=false;
    	for(int i=0;i<com.length;i++) {
    		bCheck=true;
    		while(bCheck) {
    			su=(int)(Math.random()*16)+1;
    			bCheck=false;
    			for(int j=0;j<i;j++) {
    				if(com[j]==su) {
    					bCheck=true;
    					break;
    				}
    			}
    		}
    		com[i]=su;
    	}
    	Arrays.sort(com);
    	for(int i:com) {
    		s+=i+",";
    	}
    	s=s.substring(0,s.lastIndexOf(","));
    	return s;
    }
    public void foodReserveTimeInsert(int rdate, String rtime) {
    	try {
    		getConnection();
    		String sql="INSERT INTO project_reserve_date "
    				+ "VALUES((SELECT NVL(MAX(dno)+1,1) FROM project_reserve_date),?,?)";
    		ps=conn.prepareStatement(sql);
    		ps.setInt(1, rdate);
    		ps.setString(2, rtime);
    		ps.executeUpdate();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		disConnection();
    	}
    }
    public static void main(String[] args) {
	    ProjectMain gm=new ProjectMain();
	    
	    //굿즈
//	    for(int i=1;i<=6184;i++) {
//	    	int rand=(int)(Math.random()*40)+10;
//	    	gm.goodsAccountInsert(i, rand);
//	    	try {
//	    		Thread.sleep(0);
//	    	} catch(Exception ex) {}
//	    	System.out.println("i="+i);
//		}
//	    System.out.println("저장 완료");
	    
//	    //예약가능일
//	    List<Integer> list=gm.foodNumberData();
//	    int i=1;
//	    for(int fno:list) {
//	    	gm.foodReserveDayInsert(fno, gm.reserve_day());
//	    	System.out.println(i);
//	    	i++;
//	    }
//	    System.out.println("저장 완료");
	    
	    //예약가능시간
//	    for(int i=1;i<=31;i++) {
//	    	gm.foodReserveTimeInsert(i, gm.reserve_time());
//	    }
//	    System.out.println("저장 완료");
    }
}








