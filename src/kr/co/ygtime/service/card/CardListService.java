/* 
    파일명: cardListSerice.java
    설명: 
    작성일: 2018. 4. 14.
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
import kr.co.ygtime.DTO.CardDTO;
import net.sf.json.JSONArray;

public class CardListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		List<CardDTO> cardlist = null;
		
		try {
			CardDAO carddao = new CardDAO();
			int listNum = Integer.parseInt(request.getParameter("listNum"));
			
			cardlist = carddao.allCardSelect(listNum);
			JSONArray json = JSONArray.fromObject(cardlist);
			
			request.setAttribute("json", json);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonArray.jsp");
		
		return forward;
	}
	
}
