/* 
    파일명: BoardDAO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 김 진 원
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

import kr.co.ygtime.DTO.BoardDTO;

public class BoardDAO {
	DataSource ds = null;
	
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
	public int boardInsert(BoardDTO board) {
		System.out.println("boardInsert 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		String sql =  "insert into board(boardnum, projectnum, boardtitle, detail,"
					+ "boardStartDate, boardEndDate, label, deleteok, completeok)"
					+ " values(board_idx.nextval,?,?,?,?,?,?,0,0)";
		int resultrow = 0;		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoardNum());
			pstmt.setInt(2, board.getProjectNum());
			pstmt.setString(3, board.getBoardTitle());
			pstmt.setString(4, board.getDetail());
			pstmt.setString(5, board.getBoardStartDate());
			pstmt.setString(6, board.getBoardEndDate());
			pstmt.setString(7, board.getLabel());
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
			}
		}
		return resultrow;
	}
	

	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 보드 검색(선택)
	 작성자명 : 박 민 식
	 */
	
	public BoardDTO boardSelect(int boardNum){
		System.out.println("boardSelect 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select * from board where boardnum =?";
		BoardDTO dto = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto = new BoardDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setProjectNum(rs.getInt("projectNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setDetail(rs.getString("detail"));
				dto.setBoardStartDate(rs.getString("BoardStartDate"));
				dto.setBoardEndDate(rs.getString("BoardEndDate"));
				dto.setCompleteOk(rs.getInt("completeok"));
				dto.setDeleteOk(rs.getInt("deleteOk"));
				dto.setLabel(rs.getString("label"));
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
		
		return dto;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 모든 보드 검색
	 작성자명 : 박 민 식
	 */
	public List<BoardDTO> allBoardSelect(int projectNum) {
		System.out.println("boardSelect 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select * from board where projectNum =?";
		List<BoardDTO> dtolist = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projectNum);
			rs = pstmt.executeQuery();
			dtolist = new ArrayList<>();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto = new BoardDTO();
				dto.setBoardNum(rs.getInt("boardNum"));
				dto.setProjectNum(rs.getInt("projectNum"));
				dto.setBoardTitle(rs.getString("boardTitle"));
				dto.setDetail(rs.getString("detail"));
				dto.setBoardStartDate(rs.getString("BoardStartDate"));
				dto.setBoardEndDate(rs.getString("BoardEndDate"));
				dto.setCompleteOk(rs.getInt("completeok"));
				dto.setDeleteOk(rs.getInt("deleteOk"));
				dto.setLabel(rs.getString("label"));
				dtolist.add(dto);
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
		return dtolist;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 보드 수정
	 작성자명 : 박 민 식
	 */
	public int boardUpdate(BoardDTO board) {
		System.out.println("boardUpdate 함수");
		
		int boardNum = board.getBoardNum();
		String boardTitle = board.getBoardTitle();
		String detail = board.getDetail();
		String boardStartDate = board.getBoardStartDate();
		String boardEndDate = board.getBoardEndDate();
		String label = board.getLabel();
		
		PreparedStatement pstmt =null;
		Connection conn = null;
		String sql = "update board set boardtitle= ? , detail= ?, boardStartDate = ?, "
			       + "boardEndDate=?, label = ? where boardNum=?";
		int resultrow = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, detail);
			pstmt.setString(3, boardStartDate);
			pstmt.setString(4, boardEndDate);
			pstmt.setString(5, label);
			pstmt.setInt(6, boardNum);
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return resultrow;
	}
	

	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 보드삭제 ( 삭제여부 업데이트 )
	 작성자명 : 박 민 식
	 */
	public int boardDelete(int boardNum) {
		System.out.println("boardDelete 함수");
		PreparedStatement pstmt =null;
		Connection conn = null;
		String sql = "update board set deleteok=1 where boardNum=?";
		int resultrow = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		return resultrow;
	}
}
