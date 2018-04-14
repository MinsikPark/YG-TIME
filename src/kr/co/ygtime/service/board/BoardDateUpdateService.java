/* 
    파일명: BoardDateUpdateService.java
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
import kr.co.ygtime.DTO.BoardDTO;

public class BoardDateUpdateService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String boardStartDate= request.getParameter("boardStartDate");
		String boardEndDate = request.getParameter("boardEndDate");
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		BoardDAO dao = null;
		BoardDTO dto = null;
		int resultrow = 0;
		try {
			dao = new BoardDAO();
			dto = dao.boardSelect(boardNum);
			System.out.println("board : " + dao);
			if(dto!=null) {
				dto.setBoardStartDate(boardStartDate);
				dto.setBoardEndDate(boardEndDate);
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
