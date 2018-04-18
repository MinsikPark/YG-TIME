package kr.co.ygtime.service.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.ProjectDTO;

public class ProjectNameUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String projectName = request.getParameter("projectName");
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		
		ProjectDAO dao = null;
		ProjectDTO dto = null;
		
		int resultrow = 0;
		
		try {
			dao = new ProjectDAO();
			dto = dao.projectSelect(projectNum);
			
			if(dto!=null) {
				dto.setProjectName(projectName);
				
				resultrow= dao.projectUpdate(dto);
			}
			
		}catch(Exception e) {
			
		}
		
		request.setAttribute("resultrow", resultrow);
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}

}
