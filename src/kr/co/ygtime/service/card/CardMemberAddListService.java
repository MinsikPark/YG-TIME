/* 
    파일명: CardMemberAddListService.java
    설명: 
    작성일: 2018. 4. 17.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.TeamDTO;
import net.sf.json.JSONArray;

public class CardMemberAddListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int projectNum = (int)request.getSession().getAttribute("projectNum");
		
		List<TeamDTO> list = null;
		try {
		
			ProjectDAO dao = new ProjectDAO();
			list = dao.allTeamSelect(projectNum);
			JSONArray json = JSONArray.fromObject(list);
			request.setAttribute("json", json);
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonArray.jsp");
		return forward;
	}
	
}
