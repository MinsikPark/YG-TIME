/* 
    파일명: ReplyAddService.java
    설명: 
    작성일: 2018. 4. 16.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.ReplyDTO;

public class ReplyAddService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO carddao = null;
		ActionForward forward = null;
		try {
			carddao = new CardDAO();
			
			ReplyDTO replydto = new ReplyDTO(); 
			
			String UserId = request.getParameter("UserId");
			int CardNum = Integer.parseInt(request.getParameter("CardNum"));
			String ReplyContents = request.getParameter("ReplyContents");
			
			int ReplyNum = carddao.maxReplyNum(CardNum);
			
			
			replydto.setUserId(UserId);
			replydto.setCardNum(CardNum);
			replydto.setReplyContents(ReplyContents);
			replydto.setReplyNum(ReplyNum+1);
			
			int row = carddao.replyInsert(replydto);
			
			request.setAttribute("resultrow", row);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		return forward;
	}
	
}
