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
import java.util.ArrayList;
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
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 리스트삽입 insert
	 작성자명 : 박 민 식
	 */
	public int listInsert(ListDTO list) {
		System.out.println("listInsert 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		String sql = "insert into list(boardnum, listnum, listname, deleteok,listsequential )"
					+ "value (?,list_idx.nextval,?,0,?)";
		int resultRow = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, list.getBoardNum());
			pstmt.setString(2, list.getListName());
			pstmt.setInt(3, list.getListSequential());
			
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
 날      짜 : 2018. 4. 10.
 기      능 : 리스트 sequence 최대값 뽑아오기 
 작성자명 : 박 민 식
 */
	
	public int maxListSequential(int boardNum) {
		System.out.println("listInsert 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select max(listsequential) as maxlistsequential from list where boardNum =?";
		int maxListSequential = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxListSequential = rs.getInt("maxlistsequential");
			}
			
		}catch(Exception e) {
			e.printStackTrace();			
		}finally {
	
			try {
				rs.close();
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
			}
		}

		return maxListSequential;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 리스트수정 update
	 작성자명 : 박 민 식
	 */
	public int listUpdate(ListDTO list) {
		System.out.println("listUpdate 함수");
		int listNum = list.getListNum();
		String listName = list.getListName();
		int listSequential = list.getListSequential();
		int resultRow = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String updateSql = "update list set listName=?,"
				+ "listSequential = ? where listNum =?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(updateSql);
			
			pstmt.setString(1, listName);
			pstmt.setInt(2, listSequential);
			pstmt.setInt(3, listNum);
			
			resultRow = pstmt.executeUpdate();
			
					
			
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
	 기      능 : 리스트삭제 delete
	 작성자명 : 박 민 식
	 */
	public int listDelete(int listNum) {
		System.out.println("listDelete 함수");
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String updateSql = "update list set deleteok = 1, listSequential= -1 where listnum =?";
		
		int resultRow = 0;
		
		try {
		    conn= ds.getConnection();
			pstmt = conn.prepareStatement(updateSql);
			pstmt.setInt(1, listNum);
			
			resultRow = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	 날      짜 : 2018. 4. 10.
	 기      능 : 리스트조회 select
	 작성자명 : 박 민 식
	 */
	public ListDTO listSelect(int listNum) {
		System.out.println("listSelect 함수");
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
					System.out.println("삭제된 리스트");
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
		
		return dto;
		
	}
		
	

	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 모든 리스트조회 select
	 작성자명 : 박민식
	 */
	public List<ListDTO> allListSelect(int boardNum){
		System.out.println("allListSelect 함수");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectListSql = "select * from list where boardNum =? order by listSequential asc";
		List<ListDTO> dtolist =null;
		
		try {

			dtolist =  new ArrayList<>();
			conn = ds.getConnection();
			pstmt= conn.prepareStatement(selectListSql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ListDTO dto = new ListDTO();
				dto.setBoardNum(rs.getInt("boardnum"));
				dto.setListName(rs.getString("listname"));
				dto.setListNum(rs.getInt("listnum"));
				dto.setListSequential(rs.getInt("listSequential"));
				dto.setDeleteOk(rs.getInt("deleteOk"));
				dtolist.add(dto);
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
		
		return dtolist;
	}
	
}
