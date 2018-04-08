package kr.co.ygtime.DTO;

public class ListDTO {
	private int projectNum;
	private int boardNum;
	private int listNum;
	private String listName;
	private int listSequential;
	
	public ListDTO() {}
	
	public ListDTO(int projectNum, int boardNum, int listNum, String listName, int listSequential) {
		this.projectNum = projectNum;
		this.boardNum = boardNum;
		this.listNum = listNum;
		this.listName = listName;
		this.listSequential = listSequential;
	}
	
	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getListNum() {
		return listNum;
	}
	public void setListNum(int listNum) {
		this.listNum = listNum;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getListSequential() {
		return listSequential;
	}
	public void setListSequential(int listSequential) {
		this.listSequential = listSequential;
	}
	
	
}
