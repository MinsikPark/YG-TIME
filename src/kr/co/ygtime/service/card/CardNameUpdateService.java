/* 
    파일명: CardNameUpdateService.java
    설명: 
    작성일: 2018. 4. 16.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;

public class CardNameUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String cardName = request.getParameter("cardName");
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		
		CardDAO dao = null;
		CardDTO dto = null;
		
		int resultrow = 0;
		
		try {
			dao = new CardDAO();
			dto = dao.cardSelect(cardNum);
			
			if(dto!=null) {
				dto.setCardName(cardName);
				resultrow= dao.cardUpdate(dto);
			}
			
		}catch(Exception e) {
			
		}
		
		request.setAttribute("resultrow", resultrow);
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
