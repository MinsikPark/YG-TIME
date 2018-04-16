/* 
    파일명: DownLoadService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 16.
    작성자: 박 민 식
*/

package kr.co.ygtime.service.card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.util.jar.pack.Package.File;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;

public class DownLoadService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String filename = request.getParameter("fileName");
		String savepath = "cardfile";
		String downloadpath = request.getRealPath(savepath);
		String FilePath = downloadpath + "\\" + filename;
		System.out.println("FilePath : " + FilePath);
		byte[] b = new byte[4096];
		try {
			
			FileInputStream in = new FileInputStream(FilePath);
		    String sMimeType = request.getServletContext().getMimeType(FilePath); //파일의 타입 정보
		    if(sMimeType == null){
		     //알수 없는 형식의 파일은 
		     //application/octet-stream 기본값으로 (알수 없는 파일 형식)
		     sMimeType = "application/octet-stream";
		    }
		    request.setAttribute("FilePath", FilePath);
		    //2. client 전달되는 형식을 지정(Type)
		    response.setContentType(sMimeType);
		    response.setHeader("Content-Disposition", 
		            "attachment;filename="+new String(filename.getBytes(),"ISO8859_1"));
		    ServletOutputStream out2 = response.getOutputStream();
		    int numread;
		    while((numread = in.read(b,0,b.length)) != -1){
		       out2.write(b,0,numread);
		    }
		    
		    out2.flush();
		    out2.close();
		    in.close(); 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/ajax/result_row.jsp");
		return forward;
	}
	
}
