/* 
    파일명: BoardContentUpdateService.java
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
import net.sf.json.JSONObject;

public class BoardContentUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		String boardTitle = request.getParameter("boardTitle");
	 	String boardStartDate = request.getParameter("boardStartDate");
	 	String boardEndDate = request.getParameter("boardEndDate");
	 	String label = request.getParameter("label");
	 	int resultrow = 0;
	 	
		BoardDAO boarddao = null;
		BoardDTO boarddto = null;
		try {
			boarddao = new BoardDAO();
			boarddto = boarddao.boardSelect(boardNum);
			if(boarddto!=null) {
				boarddto.setBoardTitle(boardTitle);
				boarddto.setBoardStartDate(boardStartDate);
				boarddto.setBoardEndDate(boardEndDate);
				boarddto.setLabel(label);
				
				resultrow = boarddao.boardUpdate(boarddto);
				int projectNum = boarddto.getProjectNum();
				
				JSONObject json = new JSONObject();
		 		json.put("resultrow", resultrow);
		 		json.put("projectNum", projectNum);
				
		 		request.setAttribute("json", json);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("resultrow", resultrow);
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;
	}

}
