/* 
    파일명: CardMemberListService.java
    설명: 
    작성일: 2018. 4. 17.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.CardMemberDTO;
import kr.co.ygtime.DTO.MemberDTO;
import net.sf.json.JSONArray;

public class CardMemberListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int cardNum = Integer.parseInt(request.getParameter("cardNum"));
		
		List<CardMemberDTO> cardMemberList = null;
		try {
			
			CardDAO carddao = new CardDAO();
			MemberDAO memberdao = new MemberDAO();
			
			cardMemberList = carddao.allCardMemberSelect(cardNum);
			
			Iterator<CardMemberDTO> it = cardMemberList.iterator();
			
			List<MemberDTO> memberList = new ArrayList<>();
			
			while(it.hasNext()) {
				CardMemberDTO cardmemberdto = it.next();
				MemberDTO meberdto = memberdao.memberSelect(cardmemberdto.getUserId());
				memberList.add(meberdto);
			}
			
			JSONArray json = JSONArray.fromObject(memberList);
			request.setAttribute("json", json);
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/jsonArray.jsp");
		return forward;
	}
	
}
