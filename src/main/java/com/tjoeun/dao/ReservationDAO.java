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
import com.tjoeun.vo.ReservationVO;

public class ReservationDAO {

	private JdbcTemplate template;

	DataSource dataSource;
	
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
	
	
//	myReservaionView 단에서 작업할 댓글작업
	public int insertComment(CommentVO cmo) {
		System.out.println("CommentDAO의 Insert() 메소드");
		//System.out.println("DAO :" + cmo);
			Connection conn = null;
			PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "insert into usercomment (idx, userID, userComment) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cmo.getIdx());
			pstmt.setString(2, cmo.getUserID());
			pstmt.setString(3, cmo.getUserComment());
//			pstmt.executeUpdate();
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<CommentVO> commentSelectList(int idx) {
		System.out.println("CommentDAO의 commentSelectList() 메소드");
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from usercomment where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentVO cmo = new CommentVO();
				cmo.setIdx(rs.getInt("idx"));
				cmo.setUserID(rs.getString("userID"));
				cmo.setUserComment(rs.getString("userComment"));
				cmo.setWritedate(rs.getDate("writeDate"));
				list.add(cmo);	
				//System.out.println(list);
			}
		} catch (Exception e) {
			
		}
		return list;
	}

	public int selectReservationCount() {
		System.out.println("reservationDAO의 selectReservationCount() 메소드 실행");
		String sql = "select count(*) from reservation";
		return template.queryForInt(sql);
	}

	public ArrayList<ReservationVO> selectReservationList(HashMap<String, Integer> hmap) {
		System.out.println("ReservationDAO의 selectReservationList() 메소드 실행");
		String sql = "select * from (" + "    select rownum rnum, AA.* from ("
				+ "        select * from reservation order by idx desc" + "    ) AA where rownum <=" + hmap.get("endNo")+
				") where rnum >=" + hmap.get("startNo");
		
		return (ArrayList<ReservationVO>) template.query(sql, new BeanPropertyRowMapper(ReservationVO.class));
	}

//	Reservation기능 실행	
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
