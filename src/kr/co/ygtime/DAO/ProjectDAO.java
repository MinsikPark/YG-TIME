/* 
    파일명: ProjectDAO.java
    설명: 프로젝트 내용 추가 ProjectDAO
    작성일: 2018. 4. 9.
    작성자: 전 나 영
*/

package kr.co.ygtime.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import kr.co.ygtime.DTO.ProjectDTO;
import kr.co.ygtime.DTO.TeamDTO;

public class ProjectDAO {

	DataSource ds = null;

	// 프로젝트DAO 생성자
	public ProjectDAO() throws NamingException{
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	/**
	 날      짜 : 2018. 4. 9.
	 기      능 : 프로젝트 생성 Insert
	 작성자명 : 최 재 욱
	 */
	public int projectInsert(ProjectDTO project) { 

		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			String sql = "insert into project(projectnum, projectname, projectstartdate, projectenddate,deleteok) values(project_idx.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, project.getProjectName());
			pstmt.setString(2, project.getProjectStartDate());
			pstmt.setString(3, project.getProjectEndDate());
			pstmt.setInt(4, project.getDeleteOk());
			
			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("projectInsert 에러발생 :" + e.getMessage());
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultrow;
	}
	/**
	 * 
	 날      짜 : 2018. 4. 10.
	 기      능 : 프로젝트 삭제 Delete
	 작성자명 : 최 재 욱
	 */
	public int projectDelete(int projectNum) {
		int resultrow=0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			String sql="update project set deleteok = 1 where projectnum = ?";
			
			
			pstmt = conn.prepareStatement(sql);
			
			
			resultrow = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("projectDelete 에러 발생: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return resultrow;
	}
	
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 프로젝트 수정 Update 
	 작성자명 : 최 재 욱
	 */
	public int projectUpdate(ProjectDTO project) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			String sql = "update project set projectname=? where projectnum = ?";
			pstmt.executeQuery(sql);
			resultrow = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("projectUpdate 에러 발생: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultrow;
	}

	
	
    //프로젝트 조회 select
	public ProjectDTO projectSelect(int projectNum) {
		return null;
	}


	//모든 프로젝트 select
	public List<ProjectDTO> allProjectSelect(String userId) {

		return null;
	}
	
	
	//팀장 조회 select
	public boolean ownerSelect(int projectNum, String userId) {

		return true;
	}

	//팀원 삽입 insert
	public int teamInsert(TeamDTO team) {

		return 0;
	}
	
	//팀원 제명 Delete
	public int teamMemberDelete(int projectNum, String userId, String outUserId) {

		return 0;
	}
	
	//팀 탈퇴 Delete
	public int outFromTeam(TeamDTO team) {

		return 0;
	}

}
