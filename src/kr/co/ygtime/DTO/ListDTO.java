package kr.co.ygtime.DTO;

public class ListDTO {
	private int boardNum;
	private int listNum;
	private String listName;
	private int listSequential;
	private int deleteOk;
	public ListDTO() {}
	
	public ListDTO(int boardNum, int listNum, String listName, int listSequential, int deleteOk) {
		this.boardNum = boardNum;
		this.listNum = listNum;
		this.listName = listName;
		this.listSequential = listSequential;
		this.deleteOk = deleteOk;
	}
	
	
	public int getDeleteCheck() {
		return deleteOk;
	}

	public void setDeleteCheck(int deleteOk) {
		this.deleteOk = deleteOk;
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
