/* 
    파일명: MemeberSelectService.java
    설명: 
    작성일: 2018. 4. 16.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONObject;

public class MemeberSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		MemberDAO memberdao = null;
		String userId = request.getParameter("userId");
		MemberDTO memberdto = null;
		
		try {
			memberdao = new MemberDAO();
			memberdto = memberdao.memberSelect(userId);
			JSONObject json = JSONObject.fromObject(memberdto);
			request.setAttribute("json", json);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonObject.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
}
