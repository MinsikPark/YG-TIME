/* 
    파일명: ProjectDAO.java
    설명: 프로젝트 내용 추가 ProjectDAO
    작성일: 2018. 4. 9.
    작성자: 전 나 영
*/

package kr.co.ygtime.DAO;

import java.util.List;

import javax.sql.DataSource;

import kr.co.ygtime.DTO.ProjectDTO;
import kr.co.ygtime.DTO.TeamDTO;

public class ProjectDAO {

	DataSource ds;

	// 프로젝트DAO 생성자
	public ProjectDAO() {
		super();
	}

	//프로젝트 생성 Insert
	public int projectInsert(ProjectDTO project) { 

		return 0;
	}
	//프로젝트 삭제 Delete
	public int projectDelete(int projectNum) {

		return 0;
	}
	
	//프로젝트 수정 Update
	public int projectUpdate(ProjectDTO project) {

		return 0;
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
	
	//팀원 삭제 Delete
	public int teamDelete(int projectNum, String userId, String outUserId) {

		return 0;
	}
	
	//팀원 삭제 Delete
	public int teamDelete(TeamDTO team) {

		return 0;
	}

}
