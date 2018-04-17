/* 
    파일명: ReplyDelService.java
    설명: 
    작성일: 2018. 4. 17.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;

public class ReplyDelService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String UserId = request.getParameter("UserId");
		int CardNum = Integer.parseInt(request.getParameter("CardNum"));
		int ReplyNum = Integer.parseInt(request.getParameter("ReplyNum"));
		
		CardDAO carddao = null;
		int resultrow = 0;
		try {
			carddao = new CardDAO();
			resultrow = carddao.replyDelete(UserId, ReplyNum, CardNum);
			
			request.setAttribute("resultrow", resultrow);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
