/* 
    파일명: ProjectAdmin.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class ProjectAdmin implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int projectNum = Integer.parseInt(request.getParameter("projectNum"));
			String userId = request.getParameter("userId");
			
			ProjectDAO dao = new ProjectDAO();
			int owner = dao.ownerSelect(projectNum, userId);
			
			request.setAttribute("resultrow", owner);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
