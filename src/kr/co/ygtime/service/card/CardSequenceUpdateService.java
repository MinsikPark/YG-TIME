/* 
    파일명: UpdateCardSequenceService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 15.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.card;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.CardDTO;
import net.sf.json.JSONObject;

public class CardSequenceUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("들어옴?");
		String listNum = request.getParameter("listNum");
		String seq = request.getParameter("sequential");
		System.out.println(listNum);
		System.out.println(seq);
		System.out.println("왜 스트링??");
		/*CardDAO dao = null;
		try {
			dao = new CardDAO();
			CardDTO carddto = dao.cardSelect(CardNum);
			
			JSONObject json = JSONObject.fromObject(carddto);
			request.setAttribute("json", json);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonObject.jsp");
		
		return forward;*/
		return null;
	}

	
}
