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

import kr.co.ygtime.DTO.MemberDTO;
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
		ResultSet rs = null;
		Connection conn = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into project(projectnum, projectname, projectstartdate, projectenddate,deleteok) values(project_idx.nextval,?,sysdate,null,0)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, project.getProjectName());
			
			resultrow = pstmt.executeUpdate();

			if(resultrow > 0) {
				pstmt.close();
				String sql2 = "select Max(projectnum) as maxpronum from project";
				
				pstmt = conn.prepareStatement(sql2);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("maxpronum");
				}
			}
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
		return result;
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
	 날      짜 : 2018. 4. 13.
	 기      능 : 해당 프로젝트에 모든 멤버
	 작성자명 : 최 재 욱
	 */
	public List<MemberDTO> allProjectMemberSelect(int projectNum){
		MemberDTO memberdto = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		List<MemberDTO> listmemberdto = null;
		try {
			conn = ds.getConnection();
			String sql = "select m.* from team t join member m on t.userid = m.userid where projectnum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, projectNum);
			rs = pstmt.executeQuery();
			listmemberdto = new ArrayList<>();
			while(rs.next()) {
				memberdto = new MemberDTO();
				memberdto.setUserId(rs.getString("userid"));
				memberdto.setUserNicname(rs.getString("usernicname"));
				if(rs.getString("userprofile")==null) {
					memberdto.setUserProfile("profile.png");
				}else {
					memberdto.setUserProfile(rs.getString("userprofile"));
				}
				memberdto.setUserPwd(rs.getString("userpwd"));
				listmemberdto.add(memberdto);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return listmemberdto;
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
			
			pstmt.setString(1, project.getProjectName());
			pstmt.setInt(2, project.getProjectNum());
			
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
	
	
    /**
	     날      짜 : 2018. 4. 12.
	     기      능 : 프로젝트 완료  
	     작성자명 : 최 재 욱
     */
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
						pstmt.close();
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
	    날      짜 : 2018. 4. 12.
	    기      능 : 프로젝트 복구
	    작성자명 : 전 나 영
	*/
	public int projectProgress (int projectNum, String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
				conn = ds.getConnection();
				
				//아이디팀장검증
				String sql1= "select grade from team where projectnum=? and userid=?";
				
				//게시글 삭제
				String sql2 = "UPDATE project set projectenddate = null where projectnum =?";
				
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, projectNum);
				pstmt.setString(2, userId);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 
					//아이디가 일치하는지 확인
					if(rs.getInt("grade") == 0) {
						pstmt.close();
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
			pstmt.setInt(1,projectNum);
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
			String sql = "select p.* from project p join team t on p.PROJECTNUM = t.PROJECTNUM where t.userid = ? and p.DELETEOK=0 order by t.projectlastmoddate desc";
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
	public int ownerSelect(int projectNum, String userId) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int grade = 2;
		
		try {
			conn = ds.getConnection();
			String sql = "select grade from team where projectnum=? and userid=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNum);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				grade = rs.getInt("grade");
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
		
		return grade;
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
			//아이디팀장검증
			String sql1= "select grade from team where projectnum=? and userid=?";
			
			String sql2 = "delete from team where projectnum =? and userid =? ";
			
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, projectNum);
			pstmt.setString(2,userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//아이디가 일치하는지 확인
				if(rs.getInt("grade") == 0) {
					pstmt.close();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, projectNum);
					pstmt.setString(2, outUserId);
					
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
	 날      짜 : 2018. 4. 14.
	 기      능 : 자신의권한업데이트
	 작성자명 : 최 재 욱
	 */
	public int gradeUpdate(int projectNum, String userId, String modId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			//아이디팀장검증
			String sql1= "select grade from team where projectnum=? and userid=?";
			
			//팀장위임
			String sql2 = "update team set grade=? where projectnum=? and userid=?";

			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, projectNum);
			pstmt.setString(2, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				//아이디가 일치하는지 확인
				if(rs.getInt("grade") == 0) {
					pstmt.close();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, projectNum);
					pstmt.setString(3, modId);
					
					row = pstmt.executeUpdate();
					
				}else {
					pstmt.close();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, projectNum);
					pstmt.setString(3, modId);
					
					row = pstmt.executeUpdate();
				}
				
				if(row > 0) {
					conn.commit();
				}
			}
		}catch (SQLException e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		return  row;

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
	/**
	 날      짜 : 2018. 4. 16.
	 기      능 : 진행중 프로젝트 갯수 구하기
	 작성자명 : 최 재 욱
	 */
	public int countStartProject(String userId) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(p.projectnum) as projectcnt from project p join team t on p.projectnum = t.projectnum where t.userid = ? and p.PROJECTENDDATE is null and p.DELETEOK = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultrow = rs.getInt("projectcnt");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {}
		}
		
		
		return  resultrow;
	}
	/**
	 날      짜 : 2018. 4. 16.
	 기      능 : 완료된 프로젝트 갯수 구하기
	 작성자명 : 최 재 욱
	 */
	public int countEndProject(String userId) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(p.projectnum) as projectcnt from project p join team t on p.projectnum = t.projectnum where t.userid = ? and p.PROJECTENDDATE is not null";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultrow = rs.getInt("projectcnt");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {}
		}
		
		
		return  resultrow;
	}
	/**
	 날      짜 : 2018. 4. 16.
	 기      능 : list 갯수 구하기
	 작성자명 : 최 재 욱
	 */
	public int countListProject(String userId) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(l.listnum) as listcnt from list l join board b on l.BOARDNUM = b.BOARDNUM where l.DELETEOK = 0 and b.PROJECTNUM in(select p.projectnum from project p join team t on p.projectnum = t.projectnum where t.userid = ? and p.projectenddate is null)";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultrow = rs.getInt("listcnt");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {}
		}
		
		
		return  resultrow;
	}
	/**
	 날      짜 : 2018. 4. 16.
	 기      능 : card 갯수 구하기
	 작성자명 : 최 재 욱
	 */
	public int countcardProject(String userId) {
		int resultrow = 0;
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select count(c.cardnum) as cardcnt from card c join list l on c.LISTNUM = l.LISTNUM where c.DELETEOK = 0 and l.BOARDNUM in (select b.boardnum from board b join PROJECT p on b.projectnum = p.projectnum where p.PROJECTENDDATE is null and p.projectnum in (select projectnum from team where userid=?))";
					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultrow = rs.getInt("cardcnt");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {}
		}
		
		
		return  resultrow;
	}
	
	/**
	 날      짜 : 2018. 4. 17.
	 기      능 : 프로젝트넘버에 대한 팀DTO 조회
	 작성자명 : 김 진 원
	 */
	public List<TeamDTO> allTeamSelect(int projectNum) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		TeamDTO teamdto = null;
		List<TeamDTO> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "select projectnum, userid, grade, projectlastmoddate from team where projectnum=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, projectNum);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rs.next()) {
				teamdto = new TeamDTO();
				teamdto.setProjectNum(rs.getInt("projectnum"));
				teamdto.setUserId(rs.getString("userid"));
				teamdto.setGrade(rs.getInt("grade"));
				teamdto.setProjectLastModDate(rs.getString("projectlastmoddate"));
				
				list.add(teamdto);
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
		
		return list;
	}
}
