/* 
    파일명: ProfileUpdateService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 17.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.member;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;

import kr.co.ygtime.DAO.MemberDAO;
import kr.co.ygtime.DTO.MemberDTO;


public class ProfileUpdateService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("여기옴");
		String userId = (String) request.getSession().getAttribute("sessionId");
		MultipartRequest multi = null;
		int filesize = 10*1024*1024;
		String savepath = request.getRealPath("/profile");
		int resultrow =0;
		
		try {
			System.out.println("content type : " + request.getContentType());
			 multi=new MultipartRequest(
	                    request
	                    , savepath
	                    , filesize
	                    , "UTF-8"
	                    , new DefaultFileRenamePolicy()); 
			 MemberDAO dao = new MemberDAO();
			 MemberDTO dto = dao.memberSelect(userId);
			
			 Enumeration filenames = multi.getFileNames();
			 
			 String file = (String)filenames.nextElement(); 
			 String cardUploadFile = multi.getFilesystemName(file);
			 dto.setUserProfile(cardUploadFile);
			 
 			 resultrow = dao.memberUpdate(dto);
 			 
 			 request.setAttribute("resultrow", resultrow);
			 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/ajaxpath/result_row.jsp");
		return forward;
	}
	
}
