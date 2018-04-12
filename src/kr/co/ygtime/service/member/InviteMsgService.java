/* 
    파일명: InviteMsgService.java
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
import kr.co.ygtime.DTO.InviteMsgDTO;

public class InviteMsgService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		InviteMsgDTO invitemsgdto = new InviteMsgDTO();
		MemberDAO memberdao = null;
		ActionForward forward = null;
		
		int invite = 0;
		int msgNum = Integer.parseInt(request.getParameter("msgNum"));
		String userId = request.getParameter("userId");
		int projectNum = Integer.parseInt(request.getParameter("projectNum"));
		String inviteUserId = request.getParameter("inviteUserId");
		
		invitemsgdto.setUserId(userId);
		invitemsgdto.setProjectNum(projectNum);
		invitemsgdto.setInviteUserId(inviteUserId);
		
		try {
			memberdao =new MemberDAO();
			invite = memberdao.inviteMsgInsert(invitemsgdto);
			
			forward = new ActionForward();
			request.setAttribute("invite", invite);
			forward.setRedirect(false);
			forward.setPath("/member_test/invite_test.jsp");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return forward;
	}
	
	
}
