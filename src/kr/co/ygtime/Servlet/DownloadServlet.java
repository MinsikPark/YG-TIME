package kr.co.ygtime.Servlet;


import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
   
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	 	String filename = request.getParameter("fileName");
		String savepath = "upload";
		String downloadpath = request.getRealPath(savepath);
		String FilePath = downloadpath + "\\" + filename;
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

 }

 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}