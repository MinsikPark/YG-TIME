/* 
    파일명: ChackInsertService.java
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
import kr.co.ygtime.DTO.CheckBoxDTO;

public class ChackInsertService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		CardDAO carddao = null;
		ActionForward forward = null;
		try {
			carddao = new CardDAO();
			
			CheckBoxDTO checkdto = new CheckBoxDTO();
			
			int cardnum = Integer.parseInt(request.getParameter("cardNum"));
			String checkboxcontents = request.getParameter("checkboxcontents");
			
			int checkNum = carddao.maxCheckNum(cardnum);
			System.out.println("checkNum : " + checkNum);
			checkdto.setCheckNum(checkNum+1);
			checkdto.setCardNum(cardnum);
			checkdto.setCheckBoxContents(checkboxcontents);
			
			int row = carddao.checkInsert(checkdto);
			
			request.setAttribute("resultrow", row);
			
			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		return forward;
	}
	
}
