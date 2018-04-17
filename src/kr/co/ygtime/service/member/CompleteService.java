/*
    파일명: CompleteService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 17.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.member;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;
import net.sf.json.JSONArray;

public class CompleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		MemberDAO memberdao = null;
		ActionForward forward = null;
		
		try {
			memberdao = new MemberDAO();
			List<String> use = memberdao.allMemberSelect();
			JSONArray json = JSONArray.fromObject(use);
			request.setAttribute("json", json);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonArray.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

}
