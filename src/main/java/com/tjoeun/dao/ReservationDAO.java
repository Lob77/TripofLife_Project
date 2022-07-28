package com.tjoeun.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.tjoeun.TeamProject.Constant;
import com.tjoeun.vo.CommentVO;
import com.tjoeun.vo.ContentVO;
import com.tjoeun.vo.Param;
import com.tjoeun.vo.ReservationParam;
import com.tjoeun.vo.ReservationVO;

public class ReservationDAO {

	private JdbcTemplate template;

	DataSource dataSource;

	
// 	template 객체를 이용해 db 연결	
	public ReservationDAO() {
		template = Constant.template;
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("연결실패!!!");
		}
	}
	
//	Id 값에 따른 찜 게시물 갯수 카운트 함수
	public int selectMyReservationCount(String userID) {
		System.out.println("reservationDAO의 selectMyReservationCount() 메소드 실행");
		String sql = "select count(*) from reservation where resID='"+ userID+"'";
		return template.queryForInt(sql);
	}
	
//	Id 값에 따른 찜 리스트 목록 불러오는 함수
	public ArrayList<ReservationVO> selectMyReservationList(HashMap<String, Integer> hmap,String userID) {
		System.out.println("ReservationDAO의 selectMyReservationList() 메소드 실행");
		String sql = "select * from (" + "select rownum rnum, AA.* from ("
				+ "select * from reservation where resID ='" + userID + "'order by residx desc) AA where rownum <=" + hmap.get("endNo")+
				") where rnum >=" + hmap.get("startNo");
		
		return (ArrayList<ReservationVO>) template.query(sql, new BeanPropertyRowMapper(ReservationVO.class));
	}

//	찜 기능 실행	
	public int reservation(final ReservationVO ro) {
		System.out.println("reservationDAO의 reservation() 메소드 실행");
		
		String ResID = ro.getResID();
//		System.out.println(ResID);
		String contentOwner = ro.getContentOwner();
//		System.out.println(contentOwner);
		
		try {
		if(ResID == null || ResID == "") {
			return 1;	
		}else if(ResID.equals(contentOwner)) {
			return 2;
		}else{
			String sql="insert into reservation(resIdx, idx, ContentOwner, resID, subject) \r\n" 
					+ " values(reservation_resIdx_seq.nextval,?,?,?,?)";
			
			template.update(sql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, ro.getIdx());
					ps.setString(2, ro.getContentOwner());
					ps.setString(3, ro.getResID());
					ps.setString(4, ro.getSubject());
				}
			});
			return 3;
		}
		}catch(Exception e) {
			e.printStackTrace();
			return 4;
		}
	}



}
