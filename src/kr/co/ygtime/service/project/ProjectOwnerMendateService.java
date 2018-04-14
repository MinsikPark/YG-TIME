/*
    파일명: ProjectOwnerMendateService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 14.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import net.sf.json.JSONObject;

public class ProjectOwnerMendateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ProjectDAO projectdao = null;
		ActionForward forward = null;
		int resultrow = 0;
		int projectNum = (int)request.getSession().getAttribute("projectNum");
		String userId = request.getParameter("userId");
		String modId = request.getParameter("userId");
		
		
		try {
			projectdao = new ProjectDAO();
			resultrow = projectdao.gradeUpdate(projectNum, userId, modId);
			JSONObject json = JSONObject.fromObject(resultrow);
			request.setAttribute("json", json);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonObject.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	

}
