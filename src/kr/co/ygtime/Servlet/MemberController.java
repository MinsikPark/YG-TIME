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
import kr.co.ygtime.service.member.CompleteService;
import kr.co.ygtime.service.member.IdcheckService;
import kr.co.ygtime.service.member.InviteListService;
import kr.co.ygtime.service.member.InviteMsgService;
import kr.co.ygtime.service.member.MsgAgreeService;
import kr.co.ygtime.service.member.MsgDeleteSerivce;
import kr.co.ygtime.service.member.ProfileImgService;
import kr.co.ygtime.service.member.ProfileUpdateService;
import kr.co.ygtime.service.member.WithdrawalService;
import kr.co.ygtime.service.member.JoinService;
import kr.co.ygtime.service.member.LoginService;
import kr.co.ygtime.service.member.MemberInfoService;
import kr.co.ygtime.service.member.MemberModifyService;
import kr.co.ygtime.service.member.MemeberSelectService;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
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
        ActionForward forward =null;
        Action action = null;
        

        if(cmdURI.equals("/login.member")) {
        	try{
				action = new LoginService();
        		forward= action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			
			}
        }else if(cmdURI.equals("/logout.member")){
        	
        	try {
        		request.getSession().invalidate();
            	forward = new ActionForward();
            	forward.setPath("login.jsp");
        	} 
        	catch (Exception e) {
        		e.printStackTrace();
        		
        	}
        }else if(cmdURI.equals("/Join.member")) {
    
        	
         	action = new JoinService();
        	try {
        		forward = action.execute(request, response);
        	} 
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        }else if(cmdURI.equals("/idcheck.member")){
        	action = new IdcheckService();
        	try {
        		forward = action.execute(request, response);
        	} 
        	catch (Exception e) {
        		e.printStackTrace();
        	}
        }else if(cmdURI.equals("/msgagree.member")) {
        	action = new MsgAgreeService();
        	
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }else if(cmdURI.equals("/msgdel.member")) {
        	action = new MsgDeleteSerivce();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	
        }else if(cmdURI.equals("/list.member")) {
        	action = new InviteListService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        	
        }else if(cmdURI.equals("/invite.member")) {
        	action = new InviteMsgService();
        	try {
				forward = action.execute(request, response);
			} 
        	catch (Exception e) {
				e.printStackTrace();
			}
        }else if(cmdURI.equals("/mod.member")) {

            action = new MemberInfoService();
            try {
            forward = action.execute(request, response);
            }
            catch(Exception e) {
                e.getMessage();
            }
        }else if(cmdURI.equals("/mod1.member")) {
            action = new MemberModifyService();
            try {
            forward = action.execute(request, response);
            }
            catch(Exception e) {
                e.getMessage();
            }
        }else if(cmdURI.equals("/profileimg.member")) {
        	action = new ProfileImgService();
        	  try {
                  forward = action.execute(request, response);
                  }
                  catch(Exception e) {
                      e.getMessage();
                  }
        }else if(cmdURI.equals("/userSelect.member")) {

            action = new MemeberSelectService();
            try {
            forward = action.execute(request, response);
            }
            catch(Exception e) {
                e.getMessage();
            }
        }else if(cmdURI.equals("/profileImgUpdate.member")) {
            try {
            action = new ProfileUpdateService();
            forward = action.execute(request, response);
            }
            catch(Exception e) {
                e.getMessage();
            }
        }else if(cmdURI.equals("/comple.member")) {

            action = new CompleteService();
            try {
            forward = action.execute(request, response);
            }
            catch(Exception e) {
                e.getMessage();
            }
        }
        else if(cmdURI.equals("/withdrawal.member")) {
            action = new WithdrawalService();
            request.getSession().invalidate();
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
