/* 
    파일명: ProjectDTO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 최 재 욱 
*/

package kr.co.ygtime.DTO;

public class ProjectDTO {
	private int projectNum;
	private String projectName;
	private String projectStartDate;
	private String projectEndDate;
	private int deleteOk;
	
	public ProjectDTO() {}
	
	public ProjectDTO(int projectNum, String projectName, String projectStartDate, String projectEndDate, int deleteOk) {
		this.projectNum = projectNum;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.deleteOk = deleteOk;
	}
	
	public int getDeleteOk() {
		return deleteOk;
	}

	public void setDeleteOk(int deleteOk) {
		this.deleteOk = deleteOk;
	}

	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	
	
}
