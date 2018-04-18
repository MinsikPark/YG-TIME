/* 
    파일명: ProjectSelectService.java
    설명: 프로젝트 선택 서비스
    작성일: 2018. 4. 18.
    작성자: 강 성 훈
*/

package kr.co.ygtime.service.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.ProjectDTO;
import net.sf.json.JSONObject;

public class ProjectSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		ProjectDAO dao = null;
		
		try {
			dao = new ProjectDAO();
			ProjectDTO projectdto = dao.projectSelect(projectNum);
			
			JSONObject json = JSONObject.fromObject(projectdto);
			request.setAttribute("json", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;
	}
}
