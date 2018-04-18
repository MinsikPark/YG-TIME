/* 
    파일명: DownLoadDelService.java
    설명: 
    작성일: 2018. 4. 18.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;

public class DownLoadDelService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			int cardNum = Integer.parseInt(request.getParameter("cardNum"));
			int upLoadNum = Integer.parseInt(request.getParameter("upLoadNum"));
			
			CardDAO dao = new CardDAO();
			
			int resultrow = dao.upLoadDelete(upLoadNum, cardNum);
			
			forward = new ActionForward();
			request.setAttribute("resultrow", resultrow);
			forward.setPath("/ajaxpath/result_row.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}
	
}
