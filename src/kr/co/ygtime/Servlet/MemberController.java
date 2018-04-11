package kr.co.ygtime.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.service.member.JoinService;

/**
 * 
  클래스명 : MemberController
  날      짜 : 2018. 4. 11.
  작성자명 : 전 나 영
 */

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
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
		
        ActionForward forward = null;
        Action action = null;
        
        if(cmdURI.equals("")) {
        	/* 방법1 : 아무것도 안타고 바로 경로 적기
			forward.setRedirect(false);
        	forward.setPath("/board/board_write.jsp");

        
        
        
        

        	*/
        }else if(cmdURI.equals("/Join.member")) {
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
         	action = new JoinService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        	
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
