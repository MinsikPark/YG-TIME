/*
    파일명: ProjectMemberDelete.java
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
import kr.co.ygtime.DTO.TeamDTO;
import net.sf.json.JSONObject;

public class ProjectMemberDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ProjectDAO projectdao = null;
		ActionForward forward = null;
		int resultrow = 0;
		String userId = request.getParameter("userId");
		int projectNum = (int)request.getSession().getAttribute("projectNum");
		System.out.println(userId+"/"+projectNum);
		TeamDTO teamdto = new TeamDTO();
		teamdto.setProjectNum(projectNum);
		teamdto.setUserId(userId);
		
		try {
			projectdao =new ProjectDAO();
			resultrow = projectdao.outFromTeam(teamdto);
			JSONObject json = JSONObject.fromObject(resultrow);
			request.setAttribute("json", json);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
