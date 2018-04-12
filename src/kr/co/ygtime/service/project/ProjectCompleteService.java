/* 
    파일명: ProjectpdateService.java
    설명: 
    작성일: 2018. 4. 12.
    작성자: 전나영
*/
package kr.co.ygtime.service.project;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.ProjectDTO;

public class ProjectCompleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		String userId = request.getParameter("userId");
		System.out.println("userid : " + userId);
		System.out.println("projectnum : " + projectNum);
		
		ActionForward forward = null;
		int row = 0;
		try {
			System.out.println("안녕");
		
			
			ProjectDAO dao = new ProjectDAO();
			 System.out.println("하세요");
			row = dao.projectComplete(projectNum, userId);
			
			request.setAttribute("row", row);
			System.out.println("반가워요");
			forward = new ActionForward();
			forward.setPath("/project_test/project_completeOk.jsp");
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
