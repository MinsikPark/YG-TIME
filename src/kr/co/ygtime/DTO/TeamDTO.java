/* 
    파일명: TeamDTO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 최 재 욱 
*/

package kr.co.ygtime.DTO;

public class TeamDTO {
	private int projectNum;
	private String userId;
	private int grade;
	private String projectLastModDate;
	
	public TeamDTO() {}
	public TeamDTO(int projectNum, String userId, int grade, String projectLastModDate) {
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
