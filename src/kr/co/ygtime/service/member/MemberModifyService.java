/*
    파일명: MemberEditService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;


public class MemberModifyService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		MemberDAO memberdao = null;
		ActionForward forward = null;
		MemberDTO memberdto = null;
		int resultrow = 0;
		String userId =  (String)request.getSession().getAttribute("sessionId");
		String userPwd = request.getParameter("modpassword");
		String userNickname = request.getParameter("modnickName");
		try {
			memberdao = new MemberDAO();

			memberdto = memberdao.memberSelect(userId);
			
			if(memberdto!=null) {
				memberdto.setUserPwd(userPwd);
				memberdto.setUserNicname(userNickname);
				resultrow = memberdao.memberUpdate(memberdto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("resultrow", resultrow);
		forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		return forward;
	}

}
