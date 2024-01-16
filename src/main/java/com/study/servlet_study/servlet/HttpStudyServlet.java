package com.study.servlet_study.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.utils.ParamsConverter;

@WebServlet("/http")
public class HttpStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HttpStudyServlet() {
        super();
    }
    
    // HTTP 메소드
    // POST요청   - C Create(추가)
    // GET요청 	  - R Read(조회)
    // PUT요청 	  - U Update(수정)
    // DELETE요청 - D Delete(삭제)
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.setCharacterEncoding("utf-8"); // 한글 깨짐현상을 방지해준다.
    	
    	Map<String, String> paramsMap = new HashMap<>();
    	
    	request.getParameterMap().forEach((k, v) -> {
//    		String[] valueArray = v;
    		StringBuilder builder = new StringBuilder(); // 문자열을 넣을 수 있는 빈 공간
    		
    		Arrays.asList(v).forEach(value -> builder.append(value)); // 배열을 arryslist로 바꿔준다.
    		
    		paramsMap.put(k,  builder.toString());
    		
//    		System.out.println(k + ": " + builder.toString()); // toString을 통해서 문자열로 바꿔준다.
//    		for(String value : v) {
//    			System.out.print(value);
//    		}
//    		System.out.println();
    	});
    	
    	System.out.println(paramsMap);
//    	System.out.println(request.getParameter("name"));
    	// 다른 방법
    	Map<String, String> paramsMap2 = new HashMap<>();
    	Iterator<String> ir = request.getParameterNames().asIterator(); // Iterator에 list,arrayList,set 다 있음
    	while(ir.hasNext()) {
    		String key = ir.next();
    		paramsMap2.put(key, request.getParameter(key));
    	}
    	
    	// 하나하나 출력을 했을 때
//    	String nameParams = request.getParameter("name");
//    	String phoneParams = request.getParameter("phone");
//    	String emailParams = request.getParameter("email");
//    	String addressParams = request.getParameter("address");
//    	
//    	System.out.println(nameParams);
//    	System.out.println(phoneParams);
//    	System.out.println(emailParams);
//    	System.out.println(addressParams);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 요청은 params를 쓴다.
		// get 요청은 ? 이후에 주소창에 입력된다.
		Map<String, String> paramsMap = ParamsConverter.convertParamsMapToMap(request.getParameterMap());
    	
    	System.out.println(paramsMap);		
	}

		
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
