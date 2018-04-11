/* 
    파일명: InviteListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 김진원
*/

package kr.co.ygtime.service.member;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.InviteMsgDTO;
import kr.co.ygtime.DTO.MemberDTO;

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
		List<InviteMsgDTO> list = null;
		String userId = null;
		ActionForward forward = null;
		try {
			memberdao = new MemberDAO();
			userId = (String)request.getParameter("userId");
			list = memberdao.inviteMsgSelect(userId);
			System.out.println("userid : " + userId);
			
			forward = new ActionForward();
			request.setAttribute("list", list);
			
			forward.setRedirect(false);
			forward.setPath("/member_test/invite_list_test.jsp");
			System.out.println("메롱1");
			System.out.println("list : "+ list);
			
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
