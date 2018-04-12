/*
    파일명: MemberInfoService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.member;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MemberInfoService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		MemberDAO memberdao = null;
		String userId = request.getParameter("userId");
		MemberDTO memberdto = null;
		System.out.println("test1");
		try {
			memberdao = new MemberDAO();
			//System.out.println(userId + "멤버 액션");
			memberdto = memberdao.memberSelect(userId);
			//System.out.println(memberdto);
			JSONObject json = JSONObject.fromObject(memberdto);
			//System.out.println(json);
			request.setAttribute("ajaxdata", json);
		//	System.out.println("json : " + json);
			forward = new ActionForward();
			forward.setPath("/member_test/AjaxOk.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return forward;
	}

}
