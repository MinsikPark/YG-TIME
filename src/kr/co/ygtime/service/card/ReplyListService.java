/* 
    파일명: ReplyListService.java
    설명: 
    작성일: 2018. 4. 16.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.ReplyDTO;
import net.sf.json.JSONArray;

public class ReplyListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		List<ReplyDTO> replyList = null;
		
		try {
			CardDAO carddao = new CardDAO();
			int cardNum = Integer.parseInt(request.getParameter("cardNum"));
			
			replyList = carddao.allReplySelect(cardNum);
			JSONArray json = JSONArray.fromObject(replyList);
			
			request.setAttribute("json", json);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonArray.jsp");
		
		return forward;
	}
	
}
