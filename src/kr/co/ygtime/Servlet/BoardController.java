package kr.co.ygtime.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.service.board.BoardAddService;
import kr.co.ygtime.service.board.BoardContentUpdateService;
import kr.co.ygtime.service.board.BoardDateUpdateService;
import kr.co.ygtime.service.board.BoardDeleteService;
import kr.co.ygtime.service.board.BoardDetailUpdateService;
import kr.co.ygtime.service.board.BoardListService;
import kr.co.ygtime.service.board.BoardSelectService;
import kr.co.ygtime.service.board.BoardTitleUpdateService;

/**
 * 
  클래스명 : BoardController
  날      짜 : 2018. 4. 13.
  작성자명 : 박 민 식
 */
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BoardController() {

       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String cmdURI = requestURI.substring(contextPath.length());
        ActionForward forward =null;
	
        Action action = null;
		
        if(cmdURI.equals("/allboardlist.board")) { 
        	action = new BoardListService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        }else if(cmdURI.equals("/boardadd.board")) {
        	action = new BoardAddService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        }else if(cmdURI.equals("/boarddelete.board")) {
        	action = new BoardDeleteService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        }else if(cmdURI.equals("/boarddatemodify.board")) {
        	action = new BoardDateUpdateService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        }else if(cmdURI.equals("/boardcontentmodify.board")) {
        	action = new BoardContentUpdateService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        } else if(cmdURI.equals("/boardselect.board")) {
        	try {
        		action = new BoardSelectService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/boardtitlemodify.board")) {
        	action = new BoardTitleUpdateService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        } else if(cmdURI.equals("/boarddetailmodify.board")) {
        	action = new BoardDetailUpdateService();
        	try {
        	forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.getMessage();
        	}
        }
        
        
        
        //태우기 마지막
        if(forward != null){
        	if(forward.isRedirect()) {
        		response.sendRedirect(forward.getPath());
        	}
        	else {
        		RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        		dis.forward(request, response);
        	}
        }
	}

}
