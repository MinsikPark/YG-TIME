/* 
    파일명: CheckDeleteService.java
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

public class CheckDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			int Cardnum = Integer.parseInt(request.getParameter("Cardnum"));
			int Checknum = Integer.parseInt(request.getParameter("Checknum"));
			
			CardDAO dao = new CardDAO();
			
			int resultrow = dao.checkDelete(Checknum, Cardnum);
			
			forward = new ActionForward();
			request.setAttribute("resultrow", resultrow);
			forward.setPath("/ajaxpath/result_row.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}
	
}
