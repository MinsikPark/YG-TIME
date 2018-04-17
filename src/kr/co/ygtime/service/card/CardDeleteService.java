/*
    파일명: CardDeleteService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 17.
    작성자: 최 재 욱
*/
package kr.co.ygtime.service.card;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;

public class CardDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO carddao = null;
		ActionForward forward = null;
		int resultrow = 0;
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		System.out.println("재욱짱 ㅎㅎㅎㅎㅎ" + cardNum);
		try {
			carddao = new CardDAO();
			resultrow = carddao.cardDelete(cardNum);
			request.setAttribute("resultrow", resultrow);
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return forward;
	}

}
