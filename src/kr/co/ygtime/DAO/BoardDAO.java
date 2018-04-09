/* 
    파일명: BoardDAO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 김 진 원
*/

package kr.co.ygtime.DAO;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.ygtime.DTO.BoardDTO;

public class BoardDAO {
	DataSource ds;
	
	public BoardDAO() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	/*
	 날      짜 : 2018. 4. 9.
	 기      능 : 보드 생성
	 작성자명 : 박 민 식
	 */
	public int boardInsert(int boardNum) {
		return 0;
	}
	
	//보드 검색(선택)
	public BoardDTO boardSelect(int boardNum) {
		return null;
	}
	
	//모든 보드 검색
	public List<BoardDTO> allBoardSelect(int projectNum) {
		return null;
	}
	
	//보드 수정
	public int boardUpdate(BoardDTO board) {
		return 0;
	}
	
	//보드삭제 ( 삭제여부 업데이트 )
	public int boardDelete(int boardNum) {
		return 0;
	}
}
