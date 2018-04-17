/* 
    파일명: CardFileListService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 16.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.card;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.UpLoadDTO;
import net.sf.json.JSONArray;

public class CarduploadListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		List<UpLoadDTO> list = null;
		try {
		
			CardDAO dao = new CardDAO();
			list = dao.allUpLoadSelect(cardNum);
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
