/*
    파일명: MemberLeaveService.java
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

public class MemberLeaveService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		ProjectDAO projectdao = null;
		TeamDTO teamdto = null;
		int resultrow = 0;
		String userId = request.getParameter("userId");
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		teamdto = new TeamDTO();
		teamdto.setProjectNum(projectNum);
		teamdto.setUserId(userId);
		System.out.println("userId : " + userId);
		System.out.println("projectNum : " + projectNum);
		
		try {
			projectdao = new ProjectDAO();
			resultrow = projectdao.outFromTeam(teamdto);
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
