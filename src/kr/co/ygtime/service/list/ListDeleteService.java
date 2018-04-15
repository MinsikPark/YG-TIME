/*
    파일명: ListDeleteService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 15.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.list;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ListDAO;

public class ListDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ListDAO listdao = null;
		ActionForward forward = null;
		int resultrow = 0;
		int listNum = Integer.parseInt(request.getParameter("listNum"));
		
		try {
			listdao = new ListDAO();
			resultrow = listdao.listDelete(listNum);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

}
