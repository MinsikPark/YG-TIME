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

	public ProjectDAO() {
		super();
	}

	public int projectInsert(ProjectDTO project) {

		return 0;
	}

	public int projectDelete(int projectNum) {

		return 0;
	}

	public ProjectDTO projectSelect(int projectNum) {

		return null;
	}


	public int projectUpdate(ProjectDTO project) {

		return 0;
	}


	public List<ProjectDTO> allProjectSelect(String userId) {

		return null;
	}
	
	
	
	public boolean ownerSelect(int projectNum, String userId) {

		return true;
	}

	public int teamInsert(TeamDTO team) {

		return 0;
	}

	public int teamDelete(int projectNum, String userId, String outUserId) {

		return 0;
	}

	public int teamDelete(TeamDTO team) {

		return 0;
	}

}
