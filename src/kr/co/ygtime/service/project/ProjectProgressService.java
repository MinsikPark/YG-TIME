/* 
    파일명: ProjectProgressService.java
    설명: 
    작성일: 2018. 4. 12.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class ProjectProgressService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		String userId = request.getParameter("userId");
		
		ActionForward forward = null;
		int row = 0;
		try {
			ProjectDAO dao = new ProjectDAO();
			row = dao.projectProgress(projectNum, userId);
			
			request.setAttribute("resultrow", row);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}
}
