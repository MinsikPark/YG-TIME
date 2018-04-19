/* 
    파일명: ListSelectSerivce.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 김 진 원
*/

package kr.co.ygtime.service.list;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ListDAO;
import kr.co.ygtime.DTO.ListDTO;
import net.sf.json.JSONObject;

public class ListSelectSerivce implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		ListDAO listdao =null;
		ListDTO listdto = null;
		
		try {
			listdao = new ListDAO();
			 
			 int  listNum = Integer.parseInt(request.getParameter("listNum"));
			 
			 listdto = listdao.listSelect(listNum);
			 
			 JSONObject json = JSONObject.fromObject(listdto);
			 request.setAttribute("json", json);
			 
			 forward = new ActionForward();
			 forward.setPath("/ajaxpath/jsonObject.jsp");
			 
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
}
