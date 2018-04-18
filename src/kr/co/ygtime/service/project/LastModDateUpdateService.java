package kr.co.ygtime.service.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class LastModDateUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		ProjectDAO dao = null;
		ActionForward forward = null;
		int resultrow = 0;
		try {
		
			dao = new ProjectDAO();
			
			int projectNum = Integer.parseInt(request.getParameter("projectNum"));
			String userId = (String) request.getSession().getAttribute("sessionId");
		
			resultrow = dao.lastModDateUpdate(projectNum, userId);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
