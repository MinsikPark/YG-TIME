/* 
    파일명: Idcheck.java
    설명: 
    작성일: 2018. 4. 11.
    작성자: 전나영
*/
package kr.co.ygtime.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.MemberDAO;

public class IdcheckService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	
		PrintWriter out = null;
		String id = request.getParameter("email");

		
		MemberDAO dao = null;
		String check = null;
		try {
			out = response.getWriter(); 
			dao = new MemberDAO();
	
			 check = dao.isIdcheck(id);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		if(check.equals("true")) {
		    out.print("true");
		}else if(check.equals("false")) {
		    out.print("false");
		}else if(check.equals("empty") ){
			out.print("empty");
		}

	
		return null;
	}

}
