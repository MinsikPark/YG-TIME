package kr.co.ygtime.DTO;

public class CardMemberDTO {
	private int projectNum;
	private int boardNum;
	private int listNum;
	private int cardNum;
	private String userId;
	
	public CardMemberDTO() {}
	
	public CardMemberDTO(int projectNum, int boardNum, int listNum, int cardNum, String userId) {
		this.projectNum = projectNum;
		this.boardNum = boardNum;
		this.listNum = listNum;
		this.cardNum = cardNum;
		this.userId = userId;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
