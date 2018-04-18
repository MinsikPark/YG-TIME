/* 
    파일명: ProjectAddService.java
    설명: 
    작성일: 2018. 4. 11.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.ProjectDTO;
import kr.co.ygtime.DTO.TeamDTO;

public class ProjectAddService implements Action{

	//프로젝트 생성
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 프로젝트 insert -> 2.프로젝트 팀 insert -> 3.프로젝트 list -> main
		
		ActionForward forward = null;
		
		try {
			//파라미터로 프로젝트 명을 가져온다
			String newprojectname = request.getParameter("newprojectname");
			//ProjectDTO 객체에 넣어준다.
			ProjectDTO projectdto = new ProjectDTO();
			projectdto.setProjectName(newprojectname);
		
			ProjectDAO projectdao = new ProjectDAO();
			//1. 프로젝트 insert
			int projectnum = projectdao.projectInsert(projectdto); 
			
			forward = new ActionForward();
			if(projectnum > 0) {
				//프로젝트 insert 성공
				//2. 팀 insert 관리자로
				String userid = (String) request.getSession().getAttribute("sessionId"); //확실한지 로그인 만들어진 후 테스트

				TeamDTO teamdto = new TeamDTO();
				teamdto.setUserId(userid);
				teamdto.setGrade(0);
				teamdto.setProjectNum(projectnum);
				
				int insertrow = projectdao.teamInsert(teamdto); //DB 팀에 넣어준다.
					
				
				request.setAttribute("resultrow", insertrow);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
