package kr.co.ygtime.Servlet;

/**
파일명: EncodingFilter.java
설명: 서블릿 컨트롤러
작성일: 2018. 4. 10.
작성자: 김진원
*/

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;

@WebServlet("/*.time")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
		
        ActionForward forward = new ActionForward();
        Action action = null;
        
        if(cmdURI.equals("/방법1.time")) {
        	/* 방법1 : 아무것도 안타고 바로 경로 적기
			forward.setRedirect(false);
        	forward.setPath("/board/board_write.jsp");
<<<<<<< HEAD
        	test3
=======
        	jinwon
        	충돌 테스트를 하겠습니다
        	확인 작업을 시작합니다
        	~~
        	~~~
        	
        	
        	~~~~
>>>>>>> 9e9748cfeb50066dee9e3e88ae3dbf91bf2f3b0f
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
				ㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎㅇㅎ
				test3
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	/* 방법2 : 서비스 만들어서 태우고 오기
        	action = new 서비스자바파일();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	*/
        }else if(cmdURI.equals("/방법2.time")) {
        	System.out.println("충돌 테스트 ");
        }
        
        
        //태우기 마지막
        if(forward != null){
        	if(forward.isRedirect()) {
        		response.sendRedirect(forward.getPath());
        	}
        	else {
        		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        		dis.forward(request, response);
        	}
        }
	}
}
