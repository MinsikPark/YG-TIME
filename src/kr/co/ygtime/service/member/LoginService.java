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
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String logincheck = "false";
		try {
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.memberSelect(userId);
			System.out.println("ch 1");
			if(dto !=null) { // 해당하는 멤버가 있다면
				if(userPwd.equals(dto.getUserPwd())) { // 입력한 비밀번호가 user의 비밀번호와 같은지 체크
					System.out.println(userId);
					System.out.println(dto.getUserId());
					request.getSession().setAttribute("sessionId", userId);//비밀번호가 일치 한다면 session에 담아주세요
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
