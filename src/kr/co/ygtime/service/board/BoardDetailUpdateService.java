/* 
    파일명: BoardDetailUpdateService.java
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

public class BoardDetailUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String detail= request.getParameter("detail");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		
		BoardDAO dao = null;
		BoardDTO dto = null;
		
		int resultrow = 0;
		
		try {
			dao = new BoardDAO();
			dto = dao.boardSelect(boardNum);
			
			if(dto!=null) {
				dto.setDetail(detail);
				dto.setBoardStartDate(dto.getBoardStartDate().substring(0, 10));
				dto.setBoardEndDate(dto.getBoardEndDate().substring(0, 10));
				
				resultrow= dao.boardUpdate(dto);
				
			}
			
		}catch(Exception e) {
			
		}
		
		request.setAttribute("resultrow", resultrow);
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		
		return forward;
	}
	
}
