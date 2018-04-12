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
	DataSource ds = null;
	
	public MemberDAO() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	/**
	 * 
	 날      짜 : 2018. 4. 11.
	 기      능 : 아이디 중복 체크
	 작성자명 : 전 나 영
	 */
	public String isIdcheck(String id) {
		String ischeckid= null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
				conn = ds.getConnection();
				String sql = "select userId from MEMBER where userId=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
		
				rs = pstmt.executeQuery();
				
				if(rs.next()) 
				{
					ischeckid = "true";
				
				}else {
					ischeckid = "false";
				}	
				
				if(id.equals("")) {
		
					ischeckid = "empty";
				}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return ischeckid;
	}
	
	/**
	 * 
	 날      짜 : 2018. 4. 9.
	 기      능 : 회원가입
	 작성자명 : 전 나 영
	 */	
	public int memberInsert(MemberDTO member) {
		
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = "SELECT userid, userpwd, usernicname, userprofile FROM MEMBER WHERE USERID=?";
			System.out.println(userId);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				
				memberDTO = new MemberDTO();
				memberDTO.setUserId(rs.getString("userid"));
				memberDTO.setUserPwd(rs.getString("userpwd"));
				memberDTO.setUserNicname(rs.getString("usernicname"));
				memberDTO.setUserProfile(rs.getString("userprofile"));
				System.out.println("멤버rsnext : " + memberDTO.getUserId());			
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row =0;
		try {
			conn = ds.getConnection();
			String sql = "UPDATE MEMBER SET USERPWD = ? , usernicname = ? , USERPROFILE = ?  WHERE USERID = ?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member.getUserPwd());
			pstmt.setString(2, member.getUserNicname());
			pstmt.setString(3, member.getUserProfile());
			pstmt.setString(4, member.getUserId());
			
			row =pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
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
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row =0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "DELETE FROM MEMBER WHERE USERID=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			row = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row =0;

		try {
			conn = ds.getConnection();
			
			String sql = "INSERT INTO INVITEMSG(userId,projectNum,inviteUserId,msgDate) VALUES (?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,invitemsg.getUserId());
			pstmt.setInt(2,invitemsg.getProjectNum());
			pstmt.setString(3,invitemsg.getInviteUserId());
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally{
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<InviteMsgDTO> list =  null;
		
		String sql ="SELECT * FROM INVITEMSG WHERE userid= ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			System.out.println("드러왔니");
			while(rs.next()) {
				
				InviteMsgDTO msgDTO = new InviteMsgDTO();
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
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row =0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "DELETE FROM INVITEMSG WHERE USERID=? AND PROJECTNUM=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, projectNum);
			row = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		
		return row;
		
	
	}
}
