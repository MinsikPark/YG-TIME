/* 
    파일명: BoardSelectService.java
    설명: 
    작성일: 2018. 4. 14.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.BoardDAO;
import kr.co.ygtime.DTO.BoardDTO;
import net.sf.json.JSONObject;

public class BoardSelectService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int BoardNum = Integer.parseInt(request.getParameter("BoardNum"));
		BoardDAO dao = null;
		try {
			dao = new BoardDAO();
			BoardDTO boarddto = dao.boardSelect(BoardNum);
			
			JSONObject json = JSONObject.fromObject(boarddto);
			request.setAttribute("json", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;
	}
	
}
