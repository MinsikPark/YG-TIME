package kr.co.ygtime.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.utils.resolver.implementations.ResolverXPointer;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.service.member.JoinService;
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
 
        }else if(cmdURI.equals("/login.member")) {
        	try {
				action = new LoginService();
        		forward= action.execute(request, response);
        		//response.getWriter().print(logincheck);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        }else if(cmdURI.equals("/logout.member")){
        	
        	try {
        		request.getSession().invalidate();
            	forward = new ActionForward();
            	forward.setPath("main.jsp");
        	} 
        	catch (Exception e) {
        		e.printStackTrace();
        		
        	}
        }else if(cmdURI.equals("/Join.member")) {
     
         	action = new JoinService();
        	
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
