/* 
    파일명: LoginService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 김진원
*/

package kr.co.ygtime.service.member;

import javax.servlet.http.HttpServlet;
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


public class LoginService {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("LoginService 함수 실행");
		String loginEmail = request.getParameter("loginEmail");
		String loginPwd = request.getParameter("loginPwd");
		String logincheck = "false";
		System.out.println("loginEmail : "+loginEmail);
		try {
			MemberDAO dao = new MemberDAO();
			System.out.println("ch-1");
			MemberDTO dto = dao.memberSelect(loginEmail);
			System.out.println("ch 1");
			System.out.println("userid : "+dto.getUserId());
			if(dto !=null) { // 해당하는 멤버가 있다면
				if(loginPwd.equals(dto.getUserPwd())) { // 입력한 비밀번호가 user의 비밀번호와 같은지 체크
					System.out.println(loginEmail);
					System.out.println(dto.getUserId());
					request.getSession().setAttribute("sessionId", loginEmail);//비밀번호가 일치 한다면 session에 담아주세요
					System.out.println("ch 3");
					logincheck = "true";
				}
			} 
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return logincheck;
	}

}
