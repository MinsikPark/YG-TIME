/* 
    파일명: ProjectListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 12.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.ProjectDTO;
import net.sf.json.JSONArray;

public class ProjectListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		System.out.println("ProjectListService : " + userId);
		List<ProjectDTO> list = null;
		
		try {
			
			ProjectDAO dao = new ProjectDAO();
			list = dao.allProjectSelect(userId);
			
			JSONArray json = JSONArray.fromObject(list);
			System.out.println(json);
			request.setAttribute("json", json);
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonArray.jsp");
		
		return forward;
	}

}
