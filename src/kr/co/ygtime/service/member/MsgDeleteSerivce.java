/* 
    파일명: MsgDeleteSerivce.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 김진원
*/

package kr.co.ygtime.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;

public class MsgDeleteSerivce implements Action {

	//초대 메세지를 삭제한다.
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		/*
			초대승인 시 : 1.팀 insert -> 2.메세지 Delete -> 3.메인화면으로
					초대승인 -> 2번 시작
			
			초대거절 시 : 메세지 Delete
		*/
		
		ActionForward forward = null;
		
		try {
			//파라미터로 아이디와 초대받은 프로젝튼 넘버를 가져온다
			String userid = request.getParameter("userid");
			int projectnum = Integer.parseInt(request.getParameter("projectnum"));
			
			MemberDAO memberdao = new MemberDAO();
			
			//2. 초대메세지 삭제
			int row = memberdao.inviteMsgDelete(userid, projectnum);
			
			forward = new ActionForward();
			if(row > 0) { 
				//삭제성공 -> 메인으로 돌아가게 한다 (현재는 test)
				forward.setRedirect(false);
	       	    forward.setPath("/member_test/invite_choice_test.jsp");
			}else {
				//실패 (main 화면 가기)
				forward.setRedirect(false);
				forward.setPath("/member_test/invite_choice_test.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
}
