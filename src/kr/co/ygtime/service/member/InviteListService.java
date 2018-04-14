/* 
    파일명: InviteListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 김진원
*/

package kr.co.ygtime.service.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.InviteMsgDTO;
import kr.co.ygtime.DTO.ProjectDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
  클래스명 : InviteListService
  날      짜 : 2018. 4. 11.
  작성자명 : 최 재 욱
 */
public class InviteListService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		MemberDAO memberdao = null;
		ProjectDAO projectdao = null;
		List<InviteMsgDTO> list = null;
		String userId = null;
		ActionForward forward = null;
		Map<String, List<Object>> map = null;
		try {
			memberdao = new MemberDAO();
			projectdao = new ProjectDAO();
			map =  new HashMap<>();
			
			userId = (String)request.getParameter("userId");
			list = memberdao.inviteMsgSelect(userId);
			
			
			int j = 1;		
			for(InviteMsgDTO i : list) {
				
				ProjectDTO projectdto = projectdao.projectSelect(i.getProjectNum());
				List<Object> li = new ArrayList<>();
				li.add(i);
				li.add(projectdto);
				
				map.put(String.valueOf(j), li);
			
				j++;
			}
			
			JSONObject json = JSONObject.fromObject(map);
			request.setAttribute("json", json);
			
			forward = new ActionForward();
			forward.setRedirect(false);
       	    forward.setPath("/ajaxpath/jsonObject.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
