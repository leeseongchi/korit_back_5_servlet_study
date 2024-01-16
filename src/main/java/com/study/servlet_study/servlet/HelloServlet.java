package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 주소창에서 요청하면 무조건 Get 요청이다.
// 웹에서는 무조건 Request(요청) 과 Response(응답)이다.
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getMethod());
		// 전체 주소를 가져온다.
		System.out.println(request.getRequestURL());
		// 앞쪽 주소는 빼고 뒷 주소만 가져온다.
		System.out.println(request.getRequestURI());
		// 요청이 정상적인지를 표시해준다.
		System.out.println(response.getStatus());
		// Content-Type 을 지정해준다.
		response.setContentType("text/plain");
		// 한글을 쓸 수 있다.
//		response.setCharacterEncoding("UTF-8");
		// 응답된 데이터를 표시해준다.
		System.out.println(response.getContentType());
		// 응답 데이터
		response.getWriter().println("헬로");
		
		System.out.println("요청이 들어옴!!!");
	}
}
