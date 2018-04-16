/*
    파일명: ProjectEndCountService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 16.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;

public class ProjectEndCountService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		ProjectDAO projectdao = null;
		String userId = request.getParameter("userId");
		int resultrow = 0;
		System.out.println("들어왔니??재욱아123");
		
		try {
			projectdao = new ProjectDAO();
			resultrow = projectdao.countEndProject(userId);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
