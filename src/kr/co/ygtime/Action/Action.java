/* 
    파일명: Action.java
    설명: 액션 인터페이스
    작성일: 2018. 4. 10.
    작성자: 김진원
*/

package kr.co.ygtime.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * M : DTO, DAO, SERVICE
 * V : 
 * C : controller
 */
public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
