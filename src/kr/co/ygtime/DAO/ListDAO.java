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

	//리스트DAO생성자
	public ListDAO() {
		super();
	}
	
	//리스트삽입 insert
	public int listInsert(ListDTO list) {
		
		return 0;		
	}

	//리스트수정 update
	public int listUpdate(ListDTO list) {
		
		return 0;
	}
	//리스트삭제 delete
	public int listDelete(int listNum) {
		
		return 0;
	}
	
	//리스트조회 select
	public ListDTO listSelect(int boardNum) {
		return null;
		
	}
		
	//모든 리스트조회 select
	public List<ListDTO> allListSelect(int boardNum){
		
		return null;
	}
	
}