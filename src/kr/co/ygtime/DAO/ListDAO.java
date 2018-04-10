/* 
    파일명: ListDAO.java
    설명: 프로젝트 내용 추가 ListDAO
    작성일: 2018. 4. 9.
    작성자: 전 나 영
*/

package kr.co.ygtime.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import kr.co.ygtime.DTO.ListDTO;

public class ListDAO {
	
	DataSource ds;

	//리스트DAO생성자
	public ListDAO() throws NamingException{
		Context context = new InitialContext();

		//ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 리스트삽입 insert
	 작성자명 : 박 민 식
	 */
	public int listInsert(ListDTO list) {
		PreparedStatement pstmt =null;
		Connection conn = null;
		String sql = "insert into list(boardnum, listnum, listname, deleteok,listsequential )"
					+ "value (?,list_idx.nextval,?,?,?)";
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, list.getBoardNum());
			pstmt.setInt(2, list.getListNum());
			pstmt.setString(3, list.getListName());
			pstmt.setInt(4, list.getDeleteOk());
			
			resultRow= pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
			}
		}
		
		return resultRow;		
	}


	
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 리스트수정 update
	 작성자명 : 박 민 식
	 */
	public int listUpdate(ListDTO list) {
		
		int boardNum = list.getBoardNum();
		int listNum = list.getListNum();
		String listName = list.getListName();
		int listSequential = list.getListSequential();
		int deleteOk = list.getDeleteOk();
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql= "select listnum from list where listnum = ?";
		String updateSql = "update list set boardNum =? , listNum =?, listName=?,"
				+ "listSequential = ?, deleteOk=? where listNum =?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt.close();
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, boardNum);
				pstmt.setInt(2, listNum);
				pstmt.setString(3, listName);
				pstmt.setInt(4, listSequential);
				pstmt.setInt(5, deleteOk);
				pstmt.setInt(6, listNum);
				resultRow = pstmt.executeUpdate();
			}
					
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return resultRow;
	}
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 리스트삭제 delete
	 작성자명 : 박 민 식
	 */
	public int listDelete(int listNum) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select deleteok from list where listnum =?";
		String updateSql = "update list set deleteok = 1 where listnum =?";
		
		int resultRow = 0;
		
		try {
			pstmt = conn.prepareStatement(selectSql);
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당 list가 있는지 여부 확인
				pstmt.close();
				int deleteok = Integer.parseInt(rs.getString("deleteok"));	
				if(deleteok==0) { // list가 존재 할 때, 해당 list가 삭제되어 있는 상태인지 아닌지 확인 
						          // 삭제되어 있지 않다면, update  
					pstmt = conn.prepareStatement(updateSql);
					pstmt.setInt(1, listNum);
					
					resultRow = pstmt.executeUpdate();
				}else { // 이미 삭제된 상태라면 한번 console로 확인
					System.out.println("이미 삭제되어 있음");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
			}
		
		}
		return resultRow;
	}
	
	//리스트조회 select
	public ListDTO listSelect(int listNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectSql = "select * from list where listNum =?";
		ListDTO dto = null;
		try {
			pstmt= conn.prepareStatement(selectSql);
			pstmt.setInt(1, listNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int deleteOk = Integer.parseInt(rs.getString("deleteok"));
				if(deleteOk == 0){ // board가 삭제되어 있지 않은 상태라면?
					dto = new ListDTO();
					dto.setBoardNum(rs.getInt("boardnum"));
					dto.setListName(rs.getString("listname"));
					dto.setListNum(rs.getInt("listnum"));
					dto.setListSequential(rs.getInt("listSequential"));	
				}else {
					System.out.println("삭제된 리스트ㄴ");
				}
				
			}
			
			
		} catch(Exception e){
			
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
			}
		}
		
		return null;
		
	}
		
	//모든 리스트조회 select
	public List<ListDTO> allListSelect(int boardNum){
		
		return null;
	}
	
}
