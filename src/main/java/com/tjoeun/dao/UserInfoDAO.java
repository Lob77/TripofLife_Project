package com.tjoeun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tjoeun.TeamProject.Constant;
import com.tjoeun.vo.UserInfoVO;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class UserInfoDAO {

	private JdbcTemplate template;

	
	DataSource dataSource;
	
	
	public UserInfoDAO() {
		template = Constant.template;
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
//			System.out.println("연결성공!!!");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("연결실패!!!");
		}
	}

	public int registerCheck(String userID) {
		System.out.println("UserInfoDAO 클래스의 registerCheck() 메소드");
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			String sql = "select * from userinfo where userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			/*
			 * String userIDDB = rs.getString("userID"); System.out.println(userIDDB);
			 * System.out.println(userID);
			 */			
			if (userID.trim().equals("")) {
				// 중복 검사할 아이디를 입력하지 않고 중복 체크 버튼을 클릭했을 경우
				return 0;
			} else if (rs.next()) {
				// 존재하는 회원일 경우
				return 1;
			} else {
				// 존재하지 않는 회원일 경우, 2
				return 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public void registerOK(final String userID, final String userPassword) {
		System.out.println("UserInfoDAO 클래스의 registerOK() 메소드");
		String sql = "insert into userinfo(userID,userPassword)"+
		"values (?,?)";
		template.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userID);
				ps.setString(2, userPassword);
			}
		}); 	
		}
	
/* 	userID 체크 메소드	
	public int registerCheck(String userID) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		System.out.println("userInfoDAO 클래스의 registerCheck() 메소드");
		try {
			String sql = "select * from userInfo where userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			if(userID.trim().contentEquals("")) {
				return 0;
			}else if(rs.next()) {
				return 1;
			}else {
				return 2;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
*/
/*	
	public void registerOK(final UserInfoVO userInfoVO) {
		System.out.println("MvcboardDAO의 insert() 메소드 실행");
		String sql = "insert into UserInfo (userID, userPassword) " +
				"values (?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1,userInfoVO.getUserID());
				ps.setString(2, userInfoVO.getUserPassword());
			}
		});
	}
*/	
/*
	public int loginCheck(String userID, String userPassword) {
		System.out.println("UserInfoDAO의 loginCheck() 메소드 실행");
		ResultSet rs = null;
		try {
		String sql ="select * from userInfo where userID = "+ userID;
		rs = (ResultSet) template.queryForObject(sql, new BeanPropertyRowMapper<UserInfoVO>(UserInfoVO.class));
	
		if(rs.next()) {
			if(rs.getString(2).equals(userPassword)) {
				return 1;
			} 
			else {
				return 0;
			}
		}
		return -1;
		} catch (SQLException e) {
		e.printStackTrace();
	}
	return -2;
	
}	
 */
		
	public int loginCheck(String userID, String userPassword) {
		System.out.println("UserInfoDAO 클래스의 loginCheck() 메소드");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = dataSource.getConnection();
			String sql = "select * from USERINFO where userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs= pstmt.executeQuery();
			
			
			if(rs.next()) {
				if(rs.getString("userPassword").equals(userPassword)) {
					return 1; // 로그인 성공
				} 
				else {
					return 0; // 패스워드 입력 오류
				}
			}
			return -1; // 존재하지 않는 아이디
			} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -2; // sql문 에러
	}

	public int DeleteConfirm(String userID, String userPassword,String userID1) {
		System.out.println("UserInfoDAO 클래스의 DeleteConfirm() 메소드 실행");
		System.out.println(userID);
		System.out.println(userPassword);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = dataSource.getConnection();
			String sql ="select * from userinfo where userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs= pstmt.executeQuery();
				if(rs.next()) {
					String userPasswordDB = rs.getString("userPassword");
					String userIdDB = rs.getString("userID"); //userID 값도 일치해야지 지워지도록 만들기
				if (userPasswordDB.equals(userPassword) &&userIdDB.equals(userID1)) {
						return 1;
					}else {
						return -1;
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}		
		return -2;
	}

	public int UpdateConfirm(String userID, String userPassword, String userID2) {
		System.out.println("UserInfoDAO 클래스의 UpdateConfirm() 메소드 실행");
//		System.out.println(userID);
//		System.out.println(userPassword);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = dataSource.getConnection();
			String sql ="select * from userinfo where userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID.trim());
			rs= pstmt.executeQuery();
				if(rs.next()) {
					String userPasswordDB = rs.getString("userPassword");
					String userIdDB = rs.getString("userID"); //userID 값도 일치해야지 지워지도록 만들기
				if (userPasswordDB.equals(userPassword) &&userIdDB.equals(userID2)) {
						return 1;
					}else {
						return -1;
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}		
		return -2;
	}

	public int userUpdate(ArrayList<String> list) {
		System.out.println("UserInfoDAO 클래스의 userUpdate() 메소드");

		
		String userID = list.get(0);
		String userPassword = list.get(1);
		String originalPassword = list.get(2);
		String userPassword1 = list.get(3);
		String userPassword2 = list.get(4);
		
		try {
		if(originalPassword == null || originalPassword.equals("")||
			userPassword1 == null || userPassword1.equals("")||
			userPassword2 ==null || userPassword2.equals("")) {
			return 1;
		}else if(!userPassword.equals(originalPassword)) {
			return 2;
		}else if(!userPassword1.equals(userPassword2)) {
			return 3;
		}else {
			String sql = String.format("update userinfo set userpassword = '%s'  where userid = '%s'",
					userPassword1,userID);
//			template.batchUpdate(sql);
			template.update(sql);
			return 4;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}	
		return 5;
	
	}
	
}



	
	


	
	








