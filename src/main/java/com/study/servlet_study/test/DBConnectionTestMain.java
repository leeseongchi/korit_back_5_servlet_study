package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {
	
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();  // Java에서 DB를 Connection할 때 예외처리 필수!
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// Connection은 Java와 DB를 연결을 도와줌
			con = pool.getConnection();
			// 표현식 - ?는 찾고 싶은 쿼리 자료값을 찾아준다. 양 끝에 작은 따옴표를 달아준다.
			String name = "junil";
			String sql = "select * from author_tb where author_name = ?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name); // 1은 ?의 순서이고, name은 ?의 값 넣기다.
			
			rs = pstmt.executeQuery();
			
			List<Author> authorList = new ArrayList<>();
			
			while(rs.next()) {
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());
			}
			// 3개가 같은 식이다.
			authorList.forEach(author -> System.out.println(author));
			
//			for(Author author : authorList) {
//				System.out.println(author);
//			}
//			
//			for(int i = 0; i < authorList.size(); i++) {
//				Author author = authorList.get(i);
//				System.out.println(author);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

}
