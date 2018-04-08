package kr.co.ygtime.DTO;

public class CheckListDTO {
	private int projectNum;
	private int boardNum;
	private int listNum;
	private int cardNum;
	private int checked;
	private String checkBoxContents;
	
	public CheckListDTO() {}
	
	public CheckListDTO(int projectNum, int boardNum, int listNum, int cardNum, int checked, String checkBoxContents) {
		this.projectNum = projectNum;
		this.boardNum = boardNum;
		this.listNum = listNum;
		this.cardNum = cardNum;
		this.checked = checked;
		this.checkBoxContents = checkBoxContents;
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
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public String getCheckBoxContents() {
		return checkBoxContents;
	}
	public void setCheckBoxContents(String checkBoxContents) {
		this.checkBoxContents = checkBoxContents;
	}
	
	
}
