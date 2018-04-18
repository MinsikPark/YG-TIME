/*
    파일명: WithdrawalService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 18.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.member;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;

public class WithdrawalService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		MemberDAO memberdao = null;
		int resultrow = 0;
		String userId = request.getParameter("userId");
		
		try {
			memberdao = new MemberDAO();
			resultrow = memberdao.memberDelete(userId);
			
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return forward;
	}
	
}
