/* 
    파일명: CheckedUpdateService.java
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
import kr.co.ygtime.DTO.CheckBoxDTO;

public class CheckedUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			int checked = Integer.parseInt(request.getParameter("checked"));
			String checkBoxContents= request.getParameter("checkBoxContents");
			int cardNum = Integer.parseInt(request.getParameter("cardNum"));
			int checkNum = Integer.parseInt(request.getParameter("checkNum"));
			
			CardDAO dao = new CardDAO();
			CheckBoxDTO checkdto = new CheckBoxDTO();
			
			checkdto.setChecked(checked);
			checkdto.setCheckBoxContents(checkBoxContents);
			checkdto.setCardNum(cardNum);
			checkdto.setCheckNum(checkNum);
			
			int resultrow = dao.checkUpdate(checkdto);
			
			forward = new ActionForward();
			request.setAttribute("resultrow", resultrow);
			forward.setPath("/ajaxpath/result_row.jsp");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}
	
}
