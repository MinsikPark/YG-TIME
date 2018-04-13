/* 
    파일명: ListinsertService.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 전나영
*/
package kr.co.ygtime.list;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ListDAO;
import kr.co.ygtime.DTO.ListDTO;

public class ListinsertService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		ActionForward forward = null;
		ListDAO dao =null;
		ListDTO listdto = null;
		
		try {
			 dao = new ListDAO();
			 
			 int  boardNum = Integer.parseInt(request.getParameter("boardNum"));
			 int listNum = Integer.parseInt(request.getParameter("listNum"));
			 int listSequential = Integer.parseInt(request.getParameter("listSequential"));
			 
		
			 listdto = new ListDTO();
			 listdto.setBoardNum(boardNum);
			 listdto.setListNum(listNum);
			 listdto.setListSequential(listSequential);
			 
			 dao.listInsert(listdto);
			
			 request.setAttribute("resultrow", listdto);
			 

			forward = new ActionForward();
			forward.setPath("/ajaxpath/result_row.jsp");
			 
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
		return forward;
	}

}
