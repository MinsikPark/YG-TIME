/*
    파일명: MemberEditService.java
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
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONObject;

public class MemberModifyService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberdao = null;
		ActionForward forward = null;
		MemberDTO memberdto = null;
		int resultrow = 0;
		String userId =  request.getParameter("userId");
		String userPwd = request.getParameter("modpassword");
		String userNicname = request.getParameter("modnickName");
		String userProfile = request.getParameter("modfileUpLoad");
		memberdto = new MemberDTO();
		memberdto.setUserId(userId);
		memberdto.setUserPwd(userPwd);
		memberdto.setUserNicname(userNicname);
		memberdto.setUserProfile(userProfile);
		
		try {
			memberdao = new MemberDAO();
			resultrow = memberdao.memberUpdate(memberdto);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return forward;
	}

}
