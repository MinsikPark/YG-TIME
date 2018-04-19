/* 
    파일명: BoardDeleteService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 13.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.BoardDAO;

public class BoardDeleteService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		BoardDAO boarddao = null;
		int resultrow = 0;
		try {
			boarddao = new BoardDAO();
			resultrow = boarddao.boardDelete(boardNum);
			
			request.setAttribute("resultrow", resultrow);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}

}
