/* 
    파일명: MemberDAO.java
    설명: 프로젝트 내용추가
    작성일: 2018. 4. 9.
    작성자: 전 나 영
*/

 

package kr.co.ygtime.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.ygtime.DTO.InviteMsgDTO;
import kr.co.ygtime.DTO.MemberDTO;

public class MemberDAO {

	
	
	
	public MemberDAO()  {
		
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Context context;
		
		try {
			context = new InitialContext();//context : container(was) 안에서 이름기반으로 검색 제공
			ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			System.out.println("DB 연결 실패 : " + e);
			e.printStackTrace();
		}

		
	}
/*	private String userId;
	private String userPwd;
	private String userNicname;
	private String userProfile;*/
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 회원가입
	 작성자명 : 전 나 영
	 */	
	public int memberInsert(MemberDTO member) {
		
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		String sql = "INSERT INTO MEMBER(userId,userPwd,userNicname,userProfile) VALUES (?,?,?,?)";
		int row =0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserNicname());
			pstmt.setString(4, member.getUserProfile());
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}

		return row;
}

	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 회원 검색
	 작성자명 : 전 나 영
	 */	
	public MemberDTO memberSelect(String userId) {
		
		
		MemberDTO memberDTO = null;
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT USERID FROM MEMBER WHERE USERID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				
				memberDTO = new MemberDTO();
				memberDTO.setUserId(rs.getString("userId"));
				memberDTO.setUserPwd(rs.getString("userPwd"));
				memberDTO.setUserNicname(rs.getString("userNickname"));
				memberDTO.setUserProfile(rs.getString("userProfile"));
							
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		
	}
		return memberDTO;
}
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 회원 수정
	 작성자명 : 전 나 영
	 */
	public int memberUpdate(MemberDTO member) {
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "UPDATE MEMBER SET USERPWD = ? , USERNICKNAME = ? , USERPROFILE = ?  WHERE USERID = ?";
		int row =0;
		try {
	
			conn = ds.getConnection();
			
			
			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getUserNicname());
			pstmt.setString(3, member.getUserProfile());
			pstmt.setString(4, member.getUserId());
			
		
			pstmt = conn.prepareStatement(sql);
			row =pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
	
		return row;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 회원 탈퇴
	 작성자명 : 전 나 영
	 */
	public int memberDelete(String userId) {
		
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM MEMBER WHERE USERID=?";
		int row =0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			row = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return row;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 초대메세지보내기
	 작성자명 : 전 나 영
	 */
	public int inviteMsgInsert(InviteMsgDTO invitemsg) {

		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		String sql = "INSERT INTO MEMBER(msgNum,userId,projectNum,inviteUserId,msgDate) VALUES (?,?,?,?,?)";
		int row =0;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,invitemsg.getMsgNum());
			pstmt.setString(2,invitemsg.getUserId());
			pstmt.setInt(3,invitemsg.getProjectNum());
			pstmt.setString(4,invitemsg.getInviteUserId());
			pstmt.setString(5,invitemsg.getMsgDate());
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}

		return row;
	}
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 모든 초대메세지 검색 (불러오기)
	 작성자명 : 전 나 영
	 */
	public List<InviteMsgDTO> inviteMsgSelect(String userId) {
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<InviteMsgDTO> list =  new ArrayList();
		
		String sql ="SELECT * FROM MEMBER WHERE USERID= ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

	
			if(rs.next()) {
				
				InviteMsgDTO msgDTO = new InviteMsgDTO();
				msgDTO.setMsgNum(rs.getInt("msgNum"));
				msgDTO.setUserId(rs.getString("userId"));
				msgDTO.setProjectNum(rs.getInt("projectNum"));
				msgDTO.setInviteUserId(rs.getString("inviteUserId"));
				msgDTO.setMsgDate(rs.getString("msgDate"));
			
				list.add(msgDTO);
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		
		return list;
	}
	
	
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 초대메세지 삭제
	 작성자명 : 전 나 영
	 */
	public int inviteMsgDelete(String userId, int projectNum) {
		
		DataSource ds = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "DELETE FROM MEMBER WHERE USERID=? AND PROJECTNUM=?";
		int row =0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, projectNum);
			row = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return row;
		
	
	}
}
