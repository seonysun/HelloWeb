package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement ps;
	//회원가입
	public void memberInsert(MemberVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_member "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,'n',SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getSex());
			ps.setString(5, vo.getBirth());
			ps.setString(6, vo.getEmail());
			ps.setString(7, vo.getPost());
			ps.setString(8, vo.getAddr1());
			ps.setString(9, vo.getAddr2());
			ps.setString(10, vo.getPhone());
			ps.setString(11, vo.getContent());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//ID 중복체크
	public int memberIdCheck(String id) {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	//이메일 중복체크
	public int memberEmailCheck(String email) {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE email=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	//전화번호 중복체크
	public int memberPhoneCheck(String phone) {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE phone=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	//우편번호 검색
	public List<ZipcodeVO> memberPostFind(String dong){
		List<ZipcodeVO> list=new ArrayList<ZipcodeVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT zipcode,sido,gugun,dong,NVL(bunji,' ') "
					+ "FROM zipcode "
					+ "WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ZipcodeVO vo=new ZipcodeVO();
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setGugun(rs.getString(3));
				vo.setDong(rs.getString(4));
				vo.setBunji(rs.getString(5));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	public int memberPostCount(String dong) {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) "
					+ "FROM zipcode "
					+ "WHERE dong LIKE '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, dong);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
	//로그인
	public MemberVO memberLogin(String id, String pwd) {
		MemberVO vo=new MemberVO();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0) {
				vo.setMsg("NOID");
			} else {
				sql="SELECT id,pwd,name,admin "
						+ "FROM project_member "
						+ "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_id=rs.getString(1);
				String db_pwd=rs.getString(2);
				String db_name=rs.getString(3);
				String db_admin=rs.getString(4);
				rs.close();
				
				if(db_pwd.equals(pwd)) {
					vo.setMsg("OK");
					vo.setId(db_id);
					vo.setName(db_name);
					vo.setAdmin(db_admin);
				} else {
					vo.setMsg("NOPWD");
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	//회원수정
	public MemberVO memberJoinUpdateData(String id) {
		MemberVO vo=new MemberVO();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT id,name,sex,birth,email,post,addr1,addr2,phone,content "
					+ "FROM project_member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setId(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setSex(rs.getString(3));
			vo.setBirth(rs.getString(4));
			vo.setEmail(rs.getString(5));
			vo.setPost(rs.getString(6));
			vo.setAddr1(rs.getString(7));
			vo.setAddr2(rs.getString(8));
			vo.setPhone(rs.getString(9));
			vo.setContent(rs.getString(10));
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	public boolean memberJoinUpdate(MemberVO vo) {
		boolean bCheck=false;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT pwd FROM project_member "
					+ "WHERE id=?";
		    ps=conn.prepareStatement(sql);
		    ps.setString(1, vo.getId());
		    ResultSet rs=ps.executeQuery();
		    rs.next();
		    String db_pwd=rs.getString(1);
		    rs.close();
			
		    if(db_pwd.equals(vo.getPwd())) {
		    	bCheck=true;
		    	sql="UPDATE project_member "
		    			+ "SET name=?,sex=?,email=?,phone=?,content=?,birth=?,post=?,addr1=?,addr2=? "
		    			+ "WHERE id=?";
		    	ps=conn.prepareStatement(sql);
		    	ps.setString(1,vo.getName());
				ps.setString(2,vo.getSex());
				ps.setString(3,vo.getEmail());
				ps.setString(4,vo.getPhone());
				ps.setString(5,vo.getContent());
				ps.setString(6,vo.getBirth());
				ps.setString(7,vo.getPost());
				ps.setString(8,vo.getAddr1());
				ps.setString(9,vo.getAddr2());
				ps.setString(10,vo.getId());
		    	ps.executeUpdate();
		    }
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
	}
	//ID 찾기
	public String memberIdfind(String phone) {
		String result="";
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE phone=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0) {
				result="n";
			} else {
				sql="SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') "
							//id의 1번부터 1개만 출력하고 나머지 *로 출력
						+ "FROM project_member "
						+ "WHERE phone=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, phone);
				rs=ps.executeQuery();
				rs.next();
				result=rs.getString(1);
				rs.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return result;
	}
	public String memberIdfind2(String email) {
		String result="";
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_member "
					+ "WHERE email=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			
			if(count==0) {
				result="n";
			} else {
				sql="SELECT RPAD(SUBSTR(id,1,1),LENGTH(id),'*') "
						//id의 1번부터 1개만 출력하고 나머지 *로 출력
						+ "FROM project_member "
						+ "WHERE email=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, email);
				rs=ps.executeQuery();
				rs.next();
				result=rs.getString(1);
				rs.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return result;
	}
	//회원탈퇴
	public boolean memberJoinDelete(String id, String pwd) {
		boolean bCheck=false;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT pwd "
					+ "FROM project_member "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				sql="DELETE FROM project_member "
						+ "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				ps.executeUpdate();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
	}
}
