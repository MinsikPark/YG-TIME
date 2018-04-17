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
import kr.co.ygtime.service.card.CardContentsUpdateService;
import kr.co.ygtime.service.card.CarduploadListService;
import kr.co.ygtime.service.card.CardFileUploadService;
import kr.co.ygtime.service.card.CardListService;
import kr.co.ygtime.service.card.CardSelectService;
import kr.co.ygtime.service.card.CardSequenceUpdateService;
import kr.co.ygtime.service.card.CardinsertService;
import kr.co.ygtime.service.card.ChackInsertService;
import kr.co.ygtime.service.card.CheckListService;


/**
  클래스명 : CardController
  날      짜 : 2018. 4. 14.
  작성자명 : 김 진 원
 */
@WebServlet("*.card")
public class CardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CardController() {
        super();
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
        
        if(cmdURI.equals("/Cardinsert.card")) {
        	try{
        		action = new CardinsertService();
        		forward= action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        } else if(cmdURI.equals("/Cardlist.card")) {
        	try {
        		action = new CardListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Cardcontentsupdate.card")) {
        	try {
        		action = new CardContentsUpdateService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardSequenceUpdate.card")) {
        	try {
        		action = new CardSequenceUpdateService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Cardselect.card")) {
        	try {
        		action = new CardSelectService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Checkinsert.card")) {
        	try {
        		action = new ChackInsertService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Checklist.card")) {
        	try {
        		action = new CheckListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }else if(cmdURI.equals("/cardfileupload.card")) {
        	try {
        		action = new CardFileUploadService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }else if(cmdURI.equals("/carduploadlist.card")) {
        	try {
        		action = new CarduploadListService();
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
