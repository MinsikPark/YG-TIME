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
		
		String id = request.getParameter("email");
		
		MemberDAO dao = null;
		System.out.println(id);
		try {
			dao = new MemberDAO();
	
			String result = dao.isIdcheck(id);
				
			if(result.equals("true")) {
				result = "true";
			}else if(result.equals("false")) {
				result = "false";
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
