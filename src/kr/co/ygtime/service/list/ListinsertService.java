/* 
    파일명: ListinsertService.java
    설명: 
    작성일: 2018. 4. 13.
    작성자: 전나영
*/
package kr.co.ygtime.service.list;

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

		//리스트insert = 해당 보드의 maxlistSequential을 가져온다 -> max순번+1해서  listSequential변수에 넣어서 insert
		
		ActionForward forward = null;
		ListDAO dao =null;
		ListDTO listdto = null;
		
		try {
			 dao = new ListDAO();
			 
			 int  boardNum = Integer.parseInt(request.getParameter("boardNum"));
			 String listname = request.getParameter("listname");
			 int listSequential =  dao.maxListSequential(boardNum);
			 
			 listdto = new ListDTO();
			 listdto.setBoardNum(boardNum);
			 listdto.setListName(listname);
			 listdto.setListSequential(listSequential+1);
			 
			 dao.listInsert(listdto);
			 int listnum = dao.listSelect(boardNum, listdto.getListSequential());
			 
			 request.setAttribute("resultrow", listnum);

			 forward = new ActionForward();
			 forward.setPath("/ajaxpath/result_row.jsp");
			 
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
