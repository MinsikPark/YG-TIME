package kr.co.ygtime.DTO;

public class GradeDTO {
	private int grade;
	private String lank;

	public GradeDTO() {}
	
	public GradeDTO(int grade , String lank) {
		this.grade = grade;
		this.lank = lank;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getLank() {
		return lank;
	}
	public void setLank(String lank) {
		this.lank = lank;
	}
}
