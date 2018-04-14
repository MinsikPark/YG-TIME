/* 
    파일명: CardinsertService.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 전나영
*/
package kr.co.ygtime.service.card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;

public class CardinsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO carddao = null;
		ActionForward forward = null;
		try {
			carddao = new CardDAO();
			
			CardDTO carddto = new CardDTO(); 
			int listNum = Integer.parseInt(request.getParameter("listNum"));
			String cardName = request.getParameter("cardName");
			
			int cardSequential = carddao.maxCardSequential(listNum);
			
			
			carddto.setListNum(listNum);
			carddto.setCardName(cardName);
			carddto.setCardSequential(cardSequential+1);
			
			int row = carddao.cardInsert(carddto);
			
			request.setAttribute("resultrow", row);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		return forward;
	}

}
