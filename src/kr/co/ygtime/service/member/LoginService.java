/* 
    파일명: LoginService.java
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
import kr.co.ygtime.DTO.MemberDTO;

/**
 * 
  클래스명 : LoginService
  날      짜 : 2018. 4. 11. 
  작성자명 : 박 민 식
 */


public class LoginService implements Action{

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String loginEmail = request.getParameter("loginEmail");
		String loginPwd = request.getParameter("loginPwd");
		String msg = "fail";
		try {
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.memberSelect(loginEmail);

			if(dto !=null) { // 해당하는 멤버가 있다면
				if(loginPwd.equals(dto.getUserPwd())) { // 입력한 비밀번호가 user의 비밀번호와 같은지 체크
					msg = "success";
					request.getSession().setAttribute("sessionId", loginEmail);//비밀번호가 일치 한다면 session에 담아주세요
				}
			} 
		} catch (Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		request.setAttribute("msg", msg);
		request.setAttribute("path", "main.jsp");
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/loginOk.jsp");
		
		return forward;
	}

}
