package kr.co.ygtime.DTO;

public class ProjectTeamDTO {
	private int projectNum;
	private String userId;
	private int grade;
	private String projectLastModDate;
	
	public ProjectTeamDTO() {}
	public ProjectTeamDTO(int projectNum, String userId, int grade, String projectLastModDate) {
		this.projectNum = projectNum;
		this.userId = userId;
		this.grade = grade;
		this.projectLastModDate = projectLastModDate;
	}
	
	
	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getProjectLastModDate() {
		return projectLastModDate;
	}
	public void setProjectLastModDate(String projectLastModDate) {
		this.projectLastModDate = projectLastModDate;
	}
	
	
}
