package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookSerachMain {

	public static void main(String[] args) {
		
		// 검색할 도서명을 입력하세요. >>> 글
		
		// 도서명 / 저자명 / 출판사
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Scanner scanner = new Scanner(System.in);
		String name = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			name = "%" + scanner.nextLine() + "%";
			String sql = "select\r\n"
					+ "	bt.book_name,\r\n"
					+ "    at.author_name,\r\n"
					+ "    pt.publisher_name\r\n"
					+ "from\r\n"
					+ "	book_tb bt\r\n"
					+ "	left outer join author_tb at on(at.author_id = bt.author_id)\r\n"
					+ "    left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "where\r\n"
					+ "	book_name like ?\r\n"
					+ "group by\r\n"
					+ "	book_name,\r\n"
					+ "    author_name,\r\n"
					+ "    publisher_name";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			List<Book> bookList = new ArrayList<>();
			
			while(rs.next()) {
				bookList.add(Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(Author.builder()
								.authorId(rs.getInt(3))
								.authorName(rs.getString(4))
								.build())
						.publisher(Publisher.builder()
								.publisherId(rs.getInt(5))
								.publisherName(rs.getString(6))
								.build())
						.build());
			}
			
			bookList.forEach(book -> System.out.println(book));
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
	}

}
