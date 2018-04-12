/* 
    파일명: ProjectDAO.java
    설명: 프로젝트 내용 추가 ProjectDAO
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

import com.sun.javafx.application.PlatformImpl.FinishListener;

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
			conn = ds.getConnection();
			String sql = "insert into project(projectnum, projectname, projectstartdate, projectenddate,deleteok) values(project_idx.nextval,?,sysdate,null,0)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, project.getProjectName());
			
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
			conn = ds.getConnection();
			String sql="update project set deleteok = 1 where projectnum = ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projectNum);
			
			
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
			conn = ds.getConnection();
			String sql = "update project set projectname=? where projectnum = ?";
			
			pstmt = conn.prepareStatement(sql);
			ProjectDTO projectdto = new ProjectDTO();
			
			pstmt.setString(1,projectdto.getProjectName());
			pstmt.setInt(2, projectdto.getProjectNum());
			
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
    
	public int projectComplete (int projectNum, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
				conn = ds.getConnection();
				
				//아이디팀장검증
				String sql1= "select grade from team where projectnum=? and userid=?";
				
				//게시글 삭제
				String sql2 = "UPDATE project set projectenddate = sysdate where projectnum =?";
				
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, projectNum);
				pstmt.setString(2, userId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 
					//아이디가 일치하는지 확인
					if(rs.getInt("grade") == 0) {
						pstmt = conn.prepareStatement(sql2);
						pstmt.setInt(1, projectNum);
						
						
						row = pstmt.executeUpdate();
					}
				}
						
				if(row > 0) {
					conn.commit();
				}
			
		}catch (Exception e) {
					try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 프로젝트 조회 select
	 작성자명 : 최 재 욱
	 */
	public ProjectDTO projectSelect(int projectNum) {
		ProjectDTO projectdto = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select projectnum, projectname, projectstartdate, projectenddate,deleteok from project where projectNum = ?";
			pstmt = conn.prepareStatement(sql);
			projectdto = new ProjectDTO();
			pstmt.setInt(1,projectdto.getProjectNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				projectdto.setProjectNum(rs.getInt("projectnum"));
				projectdto.setProjectName(rs.getString("projectname"));
				projectdto.setProjectStartDate(rs.getString("projectstartdate"));
				projectdto.setProjectEndDate(rs.getString("projectenddate"));
				projectdto.setDeleteOk(rs.getInt("deleteok"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return projectdto;
	}


	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 모든 프로젝트 select
	 작성자명 : 최 재 욱
	*/
	public List<ProjectDTO> allProjectSelect(String userId) {
		ProjectDTO projectdto = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<ProjectDTO> listprojectdto = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select projectnum, projectname, projectstartdate,"
					+ " projectenddate,deleteok from project where userid = ? order by projectenddate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			listprojectdto = new ArrayList<>();
			while(rs.next()) {
				projectdto = new ProjectDTO();
				projectdto.setProjectNum(rs.getInt("projectnum"));
				projectdto.setProjectName(rs.getString("projectname"));
				projectdto.setProjectStartDate(rs.getString("projectstartdate"));
				projectdto.setProjectEndDate(rs.getString("projectEndDate"));
				projectdto.setDeleteOk(rs.getInt("deleteOk"));
				
				listprojectdto.add(projectdto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		

		return listprojectdto;
	}
	
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 팀장 조회 select
	 작성자명 : 최 재 욱
	 */
	public boolean ownerSelect(int projectNum, String userId) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int grade = -1;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "select grade from team where projectnum = ? and userid = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projectNum);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				grade = rs.getInt("grade");
				if(grade == 0) {
					result = true;
				}else {
					result = false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				e.getMessage();
			}
		}
		
		return result;
	}

	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 팀원 삽입 insert
	 작성자명 : 최 재 욱
	*/
	public int teamInsert(TeamDTO team) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		
		try {
			conn = ds.getConnection();
			String sql = "insert into team(projectnum, userid, grade, projectlastmoddate) values(?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, team.getProjectNum());
			pstmt.setString(2,team.getUserId());
			pstmt.setInt(3, team.getGrade());
			
			resultrow = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		

		return resultrow;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 팀원 제명 Delete
	 작성자명 : 최 재 욱
	 */
	public int teamMemberDelete(int projectNum, String userId, String outUserId) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql1 = "select userid from team where grade = 1 and projectnum=? ";
			
			String sql2 = "delete from team where projectnum =? and userid =? ";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, projectNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//아이디가 일치하는지 확인
				if(userId.equals(rs.getString("userid"))) {
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, projectNum);
					pstmt.setString(2, userId);
					
					resultrow = pstmt.executeUpdate();
				}
			}
			if(resultrow > 0) {
				conn.commit();
			}
		} catch (Exception e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
	
	return resultrow;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 팀 탈퇴 Delete
	 작성자명 : 최 재 욱
	 */
	public int outFromTeam(TeamDTO team) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
			String sql = "delete from team where projectnum =? and userid =? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,team.getProjectNum());
			pstmt.setString(2, team.getUserId());
			
			resultrow = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {}
		}
	
		return resultrow;
	}

}
