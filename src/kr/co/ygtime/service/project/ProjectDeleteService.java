/*
    파일명: ProjectDeleteService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 12.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class ProjectDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		ProjectDAO dao = null;
		int resultrow = 0;
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		
		try {
			dao = new ProjectDAO();
			resultrow = dao.projectDelete(projectNum);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
