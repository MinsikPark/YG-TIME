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
import kr.co.ygtime.service.project.ProjectAddService;
import kr.co.ygtime.service.project.ProjectCompleteService;

import kr.co.ygtime.service.project.ProjectDeleteService;
import kr.co.ygtime.service.project.ProjectListService;
import kr.co.ygtime.service.project.ProjectProgressService;

@WebServlet("*.project")
public class ProjectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProjectController() {
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
        
        if(cmdURI.equals("/addproject.project")) {
        	action = new ProjectAddService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	

        }else if(cmdURI.equals("/project_test/delete.project")) {
        	action = new ProjectDeleteService();
        	try {
        		forward = action.execute(request, response);
        	}
        	catch (Exception e) {
        		e.getMessage();
        	}
        } else if(cmdURI.equals("/projectlist.project")) {
        	action = new ProjectListService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	
        } else if(cmdURI.equals("/completeproject.project")){
        	action = new ProjectCompleteService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        } else if(cmdURI.equals("/progressproject.project")){
        	action = new ProjectProgressService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        //마지막 태우기
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
