/*
    파일명: ProjectMemberInsertService.java
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
import kr.co.ygtime.DTO.TeamDTO;
import net.sf.json.JSONObject;

public class ProjectMemberInsertService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ProjectDAO projectdao = null;
		ActionForward forward = null;
		TeamDTO teamdto = null;
		int resultrow = 0;
		String userId = request.getParameter("userId");
		System.out.println("userId" + userId);
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		System.out.println("projectNum" + projectNum);
		teamdto = new TeamDTO();
		teamdto.setUserId(userId);
		teamdto.setProjectNum(projectNum);
		
		try {
			projectdao = new ProjectDAO();
			resultrow = projectdao.teamInsert(teamdto);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row");
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return forward;
	}

}
