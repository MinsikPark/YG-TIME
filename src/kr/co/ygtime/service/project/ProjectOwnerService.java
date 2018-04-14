/*
    파일명: ProjectOwnerService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 13.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class ProjectOwnerService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ProjectDAO projectDao = null;
		ActionForward forward = null;
		int resultrow = 0;
		int projectNum = (int)request.getSession().getAttribute("projectNum");
		String userId = request.getParameter("userId");
		try {
			projectDao = new ProjectDAO();
			resultrow = projectDao.ownerSelect(projectNum, userId);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		
		return forward;
	}

}
