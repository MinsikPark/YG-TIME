/* 
    파일명: BoardAddService.java
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

public class BoardAddService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

			String boardTitle = request.getParameter("boardTitle");
		 	String boardStartDate = request.getParameter("boardStartDate");
		 	String boardEndDate = request.getParameter("boardEndDate");
		 	String label = request.getParameter("label");
		 	int projectNum = (int) request.getSession().getAttribute("projectNum");
		 	int resultrow = 0;
		 	BoardDAO dao = null;
		 	BoardDTO dto = null;
		 	try {
		 		dao = new BoardDAO();
		 		dto = new BoardDTO();
		 		dto.setBoardEndDate(boardEndDate);
		 		dto.setBoardTitle(boardTitle);
		 		dto.setBoardStartDate(boardStartDate);
		 		dto.setLabel(label);
		 		dto.setProjectNum(projectNum);
		 		
		 		resultrow = dao.boardInsert(dto);
		 		
		 		JSONObject json = new JSONObject();
		 		json.put("resultrow", resultrow);
		 		json.put("projectNum", projectNum);
		 		
		 		request.setAttribute("json", json);
		 	}catch(Exception e) {
		 		e.printStackTrace();
		 	}
		 	
		 	ActionForward forward = new ActionForward();
		 	forward.setPath("/ajaxpath/jsonObject.jsp");
		return forward;
	}

}
