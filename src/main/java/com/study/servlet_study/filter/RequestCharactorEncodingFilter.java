package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class RequestCharactorEncodingFilter extends HttpFilter implements Filter {

    public RequestCharactorEncodingFilter() {
    	
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String[] methods = new String[] { "POST", "PUT" };
		
		System.out.println(httpRequest.getMethod());
		// toUpperCase 무조건 대문자로 바꿔준다.
		// toLowerCase 무조건 소문자로 바꿔준다.
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) {
			httpRequest.setCharacterEncoding("utf-8");
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
