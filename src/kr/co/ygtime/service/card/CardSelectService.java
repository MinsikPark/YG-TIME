/* 
    파일명: CardSelectService.java
    설명: 카드 선택 서비스
    작성일: 2018. 4. 15.
    작성자: 강 성 훈
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;
import net.sf.json.JSONObject;

public class CardSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		CardDAO dao = null;
		try {
			dao = new CardDAO();
			CardDTO carddto = dao.cardSelect(cardNum);
			
			JSONObject json = JSONObject.fromObject(carddto);
			request.setAttribute("json", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;
	}

}
