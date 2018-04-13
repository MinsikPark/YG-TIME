/*
    파일명: ProjectMemberListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 13.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.project;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONArray;

public class ProjectMemberListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ProjectMemberListService");
		ProjectDAO projectdao = null;
		ActionForward forward = null;
		List<MemberDTO> list = null;
		System.out.println("param :" + request.getParameter("projectNum"));
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		System.out.println("프넘 : " + projectNum);
		try {
			System.out.println("안녕");
			projectdao = new ProjectDAO();
			System.out.println("들어왓니??");
			list = projectdao.allProjectMemberSelect(projectNum);
			System.out.println("list" + list);
			JSONArray json = JSONArray.fromObject(list);
			request.setAttribute("json", json);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonArray.jsp");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
