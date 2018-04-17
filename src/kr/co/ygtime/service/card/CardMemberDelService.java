/* 
    파일명: CardMemberDelService.java
    설명: 
    작성일: 2018. 4. 17.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardMemberDTO;

public class CardMemberDelService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO carddao = null;
		ActionForward forward = null;
		try {
			carddao = new CardDAO();
			
			CardMemberDTO cardmemberdto = new CardMemberDTO(); 
			
			String userId = request.getParameter("userId");
			int cardNum = Integer.parseInt(request.getParameter("cardNum"));
			
			cardmemberdto.setUserId(userId);
			cardmemberdto.setCardNum(cardNum);
			
			int row = carddao.cardMemberDelete(cardmemberdto);
			
			request.setAttribute("resultrow", row);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		return forward;
	}
	
}
