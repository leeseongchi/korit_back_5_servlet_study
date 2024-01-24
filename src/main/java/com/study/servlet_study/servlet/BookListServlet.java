package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Book;
import com.study.servlet_study.service.BookService;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;

    public BookListServlet() {
        super();
        bookService = BookService.getInstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// params
		// bookName -> like 조회
		// authorName -> like 조회
		// publisherName -> like 조회
		
		String strBookName = request.getParameter("bookName");
		
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String publisherName = request.getParameter("publisherName");
		
		Map<String, String> params = new HashMap<>();
		
		if(strBookName != null) {
			try {
				strBookName = String.valueOf(strBookName);				
			} catch(NumberFormatException e) {
				response.setContentType("text/plain");
				response.setStatus(400);
				response.getWriter().println("잘못된 형식의 데이터입니다.");
				return; // return을 걸어줘야 밑에 코드가 실행되지 않는다.
			}
		}
		
		if(bookName != null) {
			params.put("bookName", bookName);
		}
		if(authorName != null) {
			params.put("authorName", authorName);
		}
		if(publisherName != null) {
			params.put("publisherName", publisherName);
		}
		
		params.size();
		List<Book> book = bookService.getBook(params);
		
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println(book);
		
	}

}
