/* 
    파일명: JoinService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 전나영
*/

package kr.co.ygtime.service.member;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;

public class JoinService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		

		int result =0;
		try {
			
			String userId = request.getParameter("email");
			String userPwd = request.getParameter("password");
			String userNicname = request.getParameter("nickName");
		  
			MemberDTO member = new MemberDTO();
			member.setUserId(userId);
			member.setUserPwd(userPwd);
			member.setUserNicname(userNicname);

			
			MemberDAO dao = new MemberDAO();
			result = dao.memberInsert(member);
			
			if(result>0) {

				request.setAttribute("resultrow", "success");
			}
			else {

				request.setAttribute("resultrow", "fail");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		forward = new ActionForward();
		//회원가입 성공.
   		forward.setRedirect(false);
   		forward.setPath("/ajaxpath/result_row.jsp");
 	
		return forward;
	}

}