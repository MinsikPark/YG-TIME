/* 
    파일명: ListDAO.java
    설명: 프로젝트 내용 추가 ListDAO
    작성일: 2018. 4. 9.
    작성자: 전 나 영
*/

package kr.co.ygtime.DAO;

import java.util.List;

import javax.sql.DataSource;

import kr.co.ygtime.DTO.ListDTO;

public class ListDAO {
	
	DataSource ds;

	public ListDAO() {
		super();
	}
	
	public int listInsert(ListDTO list) {
		
		return 0;		
	}
	public ListDTO listSelect(int boardNum) {
		return null;
		
	}
	
	public List<ListDTO> allListSelect(int boardNum){
		
		return null;
	}
	
	public int listUpdate(ListDTO list) {
		
		return 0;
	}
	
	public int listDelete(int listNum) {
		
		return 0;
	}
	
}
