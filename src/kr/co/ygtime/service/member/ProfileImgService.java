/* 
    파일명: ProfileImgService.java
    설명: 
    작성일: 2018. 4. 16.
    작성자: 전나영
*/
package kr.co.ygtime.service.member;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONObject;

public class ProfileImgService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
				
		MemberDAO memberdao = null;
		try {
			memberdao = new MemberDAO();
			MemberDTO memberdto = new MemberDTO();
			String userId =  request.getParameter("userId");
			
			memberdto = memberdao.memberSelect(userId);
			JSONObject json = JSONObject.fromObject(memberdto);
			request.setAttribute("json", json);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonObject.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
				
		return forward;
	}

}
