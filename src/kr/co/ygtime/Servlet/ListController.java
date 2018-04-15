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
import kr.co.ygtime.service.list.ListDeleteService;
import kr.co.ygtime.service.list.ListSelectSerivce;
import kr.co.ygtime.service.list.ListUpdateService;
import kr.co.ygtime.service.list.ListinsertService;
import kr.co.ygtime.service.list.Listlistsevice;


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
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
        ActionForward forward =null;
	
        Action action = null;
        
        if(cmdURI.equals("/Listlist.list")) {
        	try{
				action = new Listlistsevice();
        		forward= action.execute(request, response);

			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        } else if(cmdURI.equals("/listinsert.list")) {
        	try{
				action = new ListinsertService();
        		forward= action.execute(request, response);

			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        } else if(cmdURI.equals("/listselect.list")) {
        	try{
				action = new ListSelectSerivce();
        		forward= action.execute(request, response);

			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        } else if(cmdURI.equals("/listupdate.list")) {
        	try{
				action = new ListUpdateService();
        		forward= action.execute(request, response);

			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        } else if(cmdURI.equals("/listdelete.list")) {
        	try{
				action = new ListDeleteService();
        		forward= action.execute(request, response);

			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
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