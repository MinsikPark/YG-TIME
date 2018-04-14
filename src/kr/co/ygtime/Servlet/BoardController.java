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
import kr.co.ygtime.service.board.BoardListService;
import kr.co.ygtime.service.card.CardListService;


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
		System.out.println(cmdURI);
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
        } else if(cmdURI.equals("/Cardlist.board")) {
        	try {
        		System.out.println("111111111111111111111");
        		action = new CardListService();
        		System.out.println("222222222222222222222");
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
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
