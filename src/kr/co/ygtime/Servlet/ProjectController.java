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
import kr.co.ygtime.service.project.LastModDateUpdateService;
import kr.co.ygtime.service.project.ProjectAddService;
import kr.co.ygtime.service.project.ProjectAdmin;
import kr.co.ygtime.service.project.ProjectCardCountService;
import kr.co.ygtime.service.project.ProjectCompleteService;

import kr.co.ygtime.service.project.ProjectDeleteService;
import kr.co.ygtime.service.project.ProjectEndCountService;
import kr.co.ygtime.service.project.ProjectListCountService;
import kr.co.ygtime.service.project.ProjectListService;
import kr.co.ygtime.service.project.ProjectMemberDeleteService;
import kr.co.ygtime.service.project.ProjectMemberListService;
import kr.co.ygtime.service.project.ProjectNameUpdateService;
import kr.co.ygtime.service.project.ProjectOwnerKickOutService;
import kr.co.ygtime.service.project.ProjectOwnerMendateService;
import kr.co.ygtime.service.project.ProjectOwnerService;
import kr.co.ygtime.service.project.ProjectProgressService;
import kr.co.ygtime.service.project.ProjectSelectService;
import kr.co.ygtime.service.project.ProjectStartCountService;

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

        }else if(cmdURI.equals("/projectnameupdate.project")) {

        	action = new ProjectNameUpdateService();
        	try {
        		forward = action.execute(request, response);
        	}
        	catch (Exception e) {
        		e.getMessage();
        	}

        }else if(cmdURI.equals("/delete.project")) {

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

        } else if(cmdURI.equals("/projectselect.project")) {
        	action = new ProjectSelectService();
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
        } else if(cmdURI.equals("/projectadmin.project")){
        	action = new ProjectAdmin();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Listlist.project")){
        	action = new ProjectAdmin();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/memberlist.project")){
        	action = new ProjectMemberListService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/owner.project")){
        	action = new ProjectOwnerService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/mendate.project")){
        	action = new ProjectOwnerMendateService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/tokickout.project")){
        	action = new ProjectOwnerKickOutService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/memberdeleteproject.project")){
        	action = new ProjectMemberDeleteService();
        	try {
				forward = action.execute(request, response);
				request.getSession().setAttribute("projectNum", null);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/startcount.project")){
        	action = new ProjectStartCountService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/endcount.project")){
        	action = new ProjectEndCountService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/listcount.project")){
        	action = new ProjectListCountService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/cardcount.project")){
        	action = new ProjectCardCountService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
        	}
        }else if(cmdURI.equals("/lastmoddateupdate.project")){
        	action = new LastModDateUpdateService();
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
