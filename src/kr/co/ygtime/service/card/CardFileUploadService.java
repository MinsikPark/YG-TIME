/* 
    파일명: CardFileUploadService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 16.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.card;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.BorderUIResource.EtchedBorderUIResource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.CardDAO;
import kr.co.ygtime.DTO.UpLoadDTO;

public class CardFileUploadService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("업로드");
		
		
		MultipartRequest multi = null; // 파일 업로드를 위한 cos.jar 추가 및 객체 생성
		int filesize = 10*1024*1024; // 업로드 파일 max 사이즈 10메가
		String savepath = request.getRealPath("/cardfile");
		int resultrow =0;
		
		try {
			System.out.println("content type : " + request.getContentType());
			 multi=new MultipartRequest(
	                    request
	                    , savepath
	                    , filesize
	                    , "UTF-8"
	                    , new DefaultFileRenamePolicy()); 
			 CardDAO dao = new CardDAO();
			 int cardNum = Integer.parseInt(multi.getParameter("hiddenCardnum"));
			 Enumeration filenames = multi.getFileNames();
			 String file = (String)filenames.nextElement(); 
			 String cardUploadFile = multi.getFilesystemName(file);
			 String originFileName = multi.getOriginalFileName(file);
			 int fileNum= dao.maxFileNum(cardNum)+1;
			 System.out.println("cardUploadFile : " + cardUploadFile);
			 UpLoadDTO dto= new UpLoadDTO();
			 dto.setCardNum(cardNum);
			 dto.setFileNum(fileNum);
 			 dto.setFilePath(cardUploadFile);
 			 dto.setOriginFileName(originFileName);
 			 resultrow = dao.upLoadInsert(dto);
 			 
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
