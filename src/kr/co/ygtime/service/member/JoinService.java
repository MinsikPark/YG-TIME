/* 
    파일명: JoinService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 전나영
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

public class JoinService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		MultipartRequest multi = null; // 파일 업로드를 위한 cos.jar 추가 및 객체 생성
		int filesize = 10*1024*1024; // 업로드 파일 max 사이즈 10메가
		System.out.println("1");
	
		String savepath = request.getRealPath("/profile");
		System.out.println(savepath);
		System.out.println("2");

		int result =0;
		try {
			
			System.out.println(request.getContentType());
			 multi=new MultipartRequest(
	                    request
	                    , savepath
	                    , filesize
	                    , "UTF-8"
	                    , new DefaultFileRenamePolicy()); 
			 
			System.out.println(4);
			String userId = multi.getParameter("email");
			String userPwd = multi.getParameter("password");
			String userNicname = multi.getParameter("nickName");
		  	Enumeration filenames = multi.getFileNames(); 
			String file = (String)filenames.nextElement(); 
			String userProfile = multi.getFilesystemName(file); // 변경된 이름 >>a(1).jsp중복된 파일
			
			System.out.println("filename :" + userProfile);

			System.out.println(5);
			MemberDTO member = new MemberDTO();
			member.setUserId(userId);
			member.setUserPwd(userPwd);
			member.setUserNicname(userNicname);
			member.setUserProfile(userProfile);
			MemberDAO dao = new MemberDAO();
			result = dao.memberInsert(member);
			
			if(result>0) {

				request.setAttribute("resultrow", "success");
			}
			else {

				request.setAttribute("resultrow", "fail");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		forward = new ActionForward();
		//회원가입 성공.
   		forward.setRedirect(false);
   		forward.setPath("/ajaxpath/result_row.jsp");
 	
		return forward;
	}

}