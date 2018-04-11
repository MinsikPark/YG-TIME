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
import kr.co.ygtime.service.member.LoginService;

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
		System.out.println(cmdURI);
        ActionForward forward =null;
        Action action = null;
        
        if(cmdURI.equals("/방법1.time")) {
 
        }else if(cmdURI.equals("/member_test/login.member")) {
        	System.out.println("들어옴");
        	String logincheck = new LoginService().execute(request, response);
        	response.getWriter().print(logincheck);
        }else if(cmdURI.equals("/방법2.time")) {
        
        }else if(cmdURI.equals("/방법2.time")) {
        
        }else if(cmdURI.equals("/방법2.time")) {
        
        }else if(cmdURI.equals("/방법2.time")) {
      
        }else if(cmdURI.equals("/방법2.time")) {
    
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
