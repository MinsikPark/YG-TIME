/* 
    파일명: ListUpdateService.java
    설명: 
    작성일: 2018. 4. 14.
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

public class ListUpdateService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		ListDAO dao =null;
		ListDTO listdto = null;
		
		try {
			 dao = new ListDAO();
			 
			 int  listNum = Integer.parseInt(request.getParameter("listnum"));
			 String listname = request.getParameter("listname");
			 
			 listdto = new ListDTO();
			 listdto.setListNum(listNum);
			 listdto.setListName(listname);
			 
			 int row = dao.listUpdate(listdto);
			 
			 if(row > 0) {
				 request.setAttribute("resultrow", row);
				 forward = new ActionForward();
				 forward.setPath("/ajaxpath/result_row.jsp");
			 }
			 
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
}
