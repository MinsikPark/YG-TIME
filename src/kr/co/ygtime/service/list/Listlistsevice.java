/* 
    파일명: Listlistsevice.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.list;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ListDAO;
import kr.co.ygtime.DTO.ListDTO;
import net.sf.json.JSONArray;

public class Listlistsevice implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		List<ListDTO> list = null;
		
		try {
			ListDAO listdao = new ListDAO();
			list = listdao.allListSelect(boardnum);
			
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
