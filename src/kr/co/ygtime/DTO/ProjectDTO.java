package kr.co.ygtime.DTO;

public class ProjectDTO {
	private int projectNum;
	private String projectName;
	private String projectStartDate;
	private String projectEndDate;
	
	public ProjectDTO() {}
	
	public ProjectDTO(int projectNum, String projectName, String projectStartDate, String projectEndDate) {
		this.projectNum = projectNum;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
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
