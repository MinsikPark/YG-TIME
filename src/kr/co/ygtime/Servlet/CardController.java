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
import kr.co.ygtime.service.card.CardDeleteService;
import kr.co.ygtime.service.card.CarduploadListService;
import kr.co.ygtime.service.card.CardFileUploadService;
import kr.co.ygtime.service.card.CardListService;
import kr.co.ygtime.service.card.CardMemberAddListService;
import kr.co.ygtime.service.card.CardMemberAddService;
import kr.co.ygtime.service.card.CardMemberDelService;
import kr.co.ygtime.service.card.CardMemberListService;
import kr.co.ygtime.service.card.CardNameUpdateService;
import kr.co.ygtime.service.card.CardSelectService;
import kr.co.ygtime.service.card.CardSequenceUpdateService;
import kr.co.ygtime.service.card.CardinsertService;
import kr.co.ygtime.service.card.CheckInsertService;
import kr.co.ygtime.service.card.CheckDeleteService;
import kr.co.ygtime.service.card.CheckListService;
import kr.co.ygtime.service.card.CheckedUpdateService;
import kr.co.ygtime.service.card.ReplyAddService;
import kr.co.ygtime.service.card.ReplyDelService;
import kr.co.ygtime.service.card.ReplyListService;
import kr.co.ygtime.service.card.ReplySelService;
import kr.co.ygtime.service.card.ReplyUpService;


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
        		action = new CheckInsertService();
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

        } else if(cmdURI.equals("/Checkupdate.card")) {
        	try {
        		action = new CheckedUpdateService();
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
        } else if(cmdURI.equals("/Checkdelete.card")) {
        	try {
        		action = new CheckDeleteService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardNameUpdate.card")) {
        	try {
        		action = new CardNameUpdateService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/ReplyAdd.card")) {
        	try {
        		action = new ReplyAddService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/Replylist.card")) {
        	try {
        		action = new ReplyListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        }else if(cmdURI.equals("/carddelete.card")) {
        	try {
        		action = new CardDeleteService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/ReplyDel.card")) {
        	try {
        		action = new ReplyDelService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/ReplySel.card")) {
        	try {
        		action = new ReplySelService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/ReplyUp.card")) {
        	try {
        		action = new ReplyUpService();
        		forward = action.execute(request, response);

        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/carduploadlist.card")) {
        	try {
        		action = new CarduploadListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardMemeberAddList.card")) {
        	try {
        		action = new CardMemberAddListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardMemeberAdd.card")) {
        	try {
        		action = new CardMemberAddService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardMemberList.card")) {
        	try {
        		action = new CardMemberListService();
        		forward = action.execute(request, response);
        	}
        	catch(Exception e) {
        		e.printStackTrace();
        	}
        } else if(cmdURI.equals("/CardMemeberDel.card")) {
        	try {
        		action = new CardMemberDelService();
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
