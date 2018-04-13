
package kr.co.ygtime.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.BoardDAO;
import kr.co.ygtime.DTO.BoardDTO;
import net.sf.json.JSONArray;

public class BoardListService implements Action{

	/* 
    파일명: BoardListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 13.
    작성자: 박 민 식
	 */
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardListService");
			int projectNum = Integer.parseInt(request.getParameter("projectNum"));
			BoardDAO dao = null;
			List<BoardDTO> list = null;
			try {
				dao = new BoardDAO();
				list = dao.allBoardSelect(projectNum);
				request.getSession().setAttribute("projectNum", projectNum);
				JSONArray json = JSONArray.fromObject(list);
				request.setAttribute("json", json);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			ActionForward forward = new ActionForward();
			forward.setPath("/ajaxpath/jsonArray.jsp");
		return forward;
	}
	
}
