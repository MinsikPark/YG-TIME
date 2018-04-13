package kr.co.ygtime.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.list.ListinsertService;
import kr.co.ygtime.service.member.LoginService;


@WebServlet("*.list")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ListController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
		String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
		System.out.println(cmdURI);
        ActionForward forward =null;
	
        Action action = null;
        
        
        if(cmdURI.equals("/listinsert.list")) {
        	try{
				action = new ListinsertService();
        		forward= action.execute(request, response);


			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        }
        
        
		
	}


}
