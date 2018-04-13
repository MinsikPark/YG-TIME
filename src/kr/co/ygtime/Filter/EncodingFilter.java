/**
    파일명: EncodingFilter.java
    설명: 필터, UTF-8 처리 해주기
    작성일: 2018. 4. 10.
    작성자: 김진원
*/

package kr.co.ygtime.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
			description = "프로그램 실행시 한글처리를 위해 만든 파일",
			urlPatterns = {"/*"},
			//"/*" <--모든 파일에 대해 동작
			initParams = {
							@WebInitParam(name="encoding", value="UTF-8")
	        			 }   
		  )
public class EncodingFilter implements Filter {

	private String Encoding;
	
    public EncodingFilter() {
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null){
	         request.setCharacterEncoding(this.Encoding);
	    }
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.Encoding = fConfig.getInitParameter("encoding");
	}

}
