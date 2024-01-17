package com.study.servlet_study.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.service.AccoutService;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccoutService accoutService;
       

    public AccountServlet() {
        super();
        accoutService = AccoutService.getInstance();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		Account account = accoutService.getAccount(username);
		response.setStatus(200);
		response.getWriter().println(account);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Account account = Account.builder()
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.build();
		
		int body = accoutService.addAcount(account);
		response.setStatus(201); // 200번대가 나오면 요청 성공한거다. 404번은 오류다.
		response.getWriter().println(body);
	}

}
