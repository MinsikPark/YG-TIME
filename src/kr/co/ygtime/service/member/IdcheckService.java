/* 
    파일명: Idcheck.java
    설명: 
    작성일: 2018. 4. 11.
    작성자: 전나영
*/
package kr.co.ygtime.service.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;

public class IdcheckService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String userId = request.getParameter("email");
		
		MemberDAO memberdao = null;
		try {
			memberdao = new MemberDAO();
	
			String result = memberdao.isIdcheck(userId);
	
			if(result.equals("false")) { //아이디가 있을때
				result = "false";

			}
			if(result.equals("true")) { //아이디가 없을때
				result = "true";

			}if(result.equals("empty")) { //아이디가 빈값일때
				result = "empty";

			}
			request.setAttribute("result", result);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}

}
