package com.tjoeun.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.TeamProject.Constant;
import com.tjoeun.vo.ContentVO;

public class ContentDAO {

	private JdbcTemplate template;

	DataSource dataSource;
	
	public ContentDAO() {
		template = Constant.template;
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("연결실패!!!");
		}
	}
	public void insert(final ContentVO contentVO) {	
		System.out.println("contentDAO의 insert() 메소드 실행");
//		System.out.println(contentVO);
	
		String sql = "insert into content (idx, userID, subject, content) " +
				"values (mvcboard_idx_seq.nextval, ?, ?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contentVO.getUserID());
				ps.setString(2, contentVO.getSubject());
				ps.setString(3, contentVO.getContent());
			}
		});
	}
	
	
	public int selectContentCount() {
		System.out.println("contentDAO의 selectContentCount() 메소드 실행");
		String sql = "select count(*) from content";
		return template.queryForInt(sql);		
	}
	
// MyContent	
	public int selectMyContentCount(String userID) {
	  System.out.println("contentDAO의 selectMyContentCount() 메소드 실행"); String sql =
	  "select count(*) from content where userID='"+userID+"'"; return
	  template.queryForInt(sql); }
	 


	public ArrayList<ContentVO> selectContentList(HashMap<String, Integer> hmap){
		System.out.println("contentDAO의 selectContentList() 메소드 실행");
		String sql = "select * from (" + "    select rownum rnum, AA.* from ("
				+ "        select * from content order by idx desc" + "    ) AA where rownum <=" + hmap.get("endNo")+
				") where rnum >=" + hmap.get("startNo");
		
		return (ArrayList<ContentVO>) template.query(sql, new BeanPropertyRowMapper(ContentVO.class));
		
	}
		
// bestContent
	public ArrayList<ContentVO> selectBestContentList(HashMap<String, Integer> hmap){
		System.out.println("contentDAO의 selectContentList() 메소드 실행");
		String sql = "select * from (" + "    select rownum rnum, AA.* from ("
				+ "        select * from content order by hit desc" + "    ) AA where rownum <=" + hmap.get("endNo")+
				") where rnum >=" + hmap.get("startNo");
		
		return (ArrayList<ContentVO>) template.query(sql, new BeanPropertyRowMapper(ContentVO.class));
		
	}
	
	
	public void Increment(int idx) {
		System.out.println("ContentDAO의 increment() 메소드");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "Update content set hit = hit + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}	
	
	
	public ContentVO selectByIdx(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentVO contentVO = null; // 조회수를 증가시킨 글 1건을 얻어와 저장시켜 리턴할 객체를 선언한다.

		try {
			conn = dataSource.getConnection();
			String sql = "select * from content where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();

//		resultSet 객체에 저장된 글 1건을 리턴시키기 위해서 ContentVO 클래스 객체에 저장한다.
			if (rs.next()) {
				AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
				contentVO = ctx.getBean("ContentVO", ContentVO.class);
				contentVO.setIdx(rs.getInt("idx"));
				contentVO.setSubject(rs.getString("subject"));
				contentVO.setContent(rs.getString("content"));
				contentVO.setHit(rs.getInt("hit"));
				contentVO.setWriteDate(rs.getTimestamp("writeDate"));
				contentVO.setIp(rs.getString("ip"));
				contentVO.setUserID(rs.getString("userID"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return contentVO;
	}
	
	
	
//	search ajax 기능수정코드 
	public ArrayList<ContentVO> search(String subject) {
		System.out.println("ContentDAO 클래스의 search() 메소드");
		ArrayList<ContentVO> list = new ArrayList<ContentVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String sql = "select * from content where subject like ? order by idx desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%' + subject + '%');
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ContentVO vo = new ContentVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setSubject(rs.getString("subject"));
				vo.setUserID(rs.getString("userID"));
				vo.setHit(rs.getInt("hit"));
				vo.setWriteDate(rs.getTimestamp("writeDate"));
		
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public void delete(final int idx) {
		System.out.println("contentDAO의 delete() 메소드");
		String sql = "delete from content where idx = ?";
		template.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, idx);
				
			}
		});
		
	}
	
	public void update(final int idx,final String subject,final String content) {
		System.out.println("ContentDAO의 update(int idx, String subject, String content) 메소드");
		String sql = String.format("update content set subject = '%s' , content ='%s' where idx = %d",
				subject,content,idx);

		template.update(sql);
	}
	
	public void update(final ContentVO contentVO) {
		System.out.println("ContentDAO의 update(ContentVO contentVO) 메소드");
		
		String sql = "update content set subject = ?, content =  ? where idx = ?";
		template.update(sql,new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contentVO.getSubject());
				ps.setString(2, contentVO.getContent());
				ps.setInt(3, contentVO.getIdx());
				
			}
		});
		
	}
	public ArrayList<ContentVO> selectMyContentList(HashMap<String, Integer> hmap,String userID) {
//		System.out.println("contentDAO의 selectMyContentList() 메소드 실행");
//		System.out.println("selectMyContentList 메소드의 :"+userID);
		String sql = "select * from (" + "select rownum rnum, AA.* from ("
				+ "select * from content where userID ='" + userID + "'order by idx desc) AA where rownum <=" + hmap.get("endNo")+
				") where rnum >=" + hmap.get("startNo");
		
		return (ArrayList<ContentVO>) template.query(sql, new BeanPropertyRowMapper(ContentVO.class));

	}



}
