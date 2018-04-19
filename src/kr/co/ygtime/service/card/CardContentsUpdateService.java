package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;

public class CardContentsUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String cardContents = request.getParameter("cardContents");
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		
		CardDAO carddao = null;
		CardDTO carddto = null;
		
		int resultrow = 0;
		
		try {
			carddao = new CardDAO();
			carddto = carddao.cardSelect(cardNum);
			
			if(carddto!=null) {
				carddto.setCardContents(cardContents);
				resultrow= carddao.cardUpdate(carddto);
			}
			
		}catch(Exception e) {
			
		}
		
		request.setAttribute("resultrow", resultrow);
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
