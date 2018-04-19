/* 
    파일명: UpdateCardSequenceService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 15.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;

public class CardSequenceUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO dao = null;
		int listNum = Integer.parseInt(request.getParameter("listNum").substring(7));
		String[] seq = (request.getParameter("sequential")).split(",");
		try {
			dao = new CardDAO(); 
			for(int i = 0; i < seq.length; i++) {
				if(seq[i].substring(0, 3).equals("div")) continue;

				CardDTO card = new CardDTO();
				card.setListNum(listNum);
				card.setCardNum(Integer.parseInt(seq[i]));
				card.setCardSequential(i);
				dao.cardSequenceUpdate(card);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;
	}

	
}
