package kr.co.ygtime.DTO;

public class CardDTO {
	private int projectNum;
	private int boardNum;
	private int listNum;
	private int cardNum;
	private String cardName;
	private String cardContents;
	private String uploadFileName;
	private int cardSequential;
	
	public CardDTO() {}
	
	public CardDTO(int projectNum, int boardNum, int listNum, int cardNum, String cardName, String cardContents, String uploadFileName, int cardSequential) {
		this.projectNum = projectNum;
		this.boardNum = boardNum;
		this.listNum = listNum;
		this.cardNum = cardNum;
		this.cardName = cardName;
		this.cardContents = cardContents;
		this.uploadFileName = uploadFileName;
		this.cardSequential = cardSequential;
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
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardContents() {
		return cardContents;
	}
	public void setCardContents(String cardContents) {
		this.cardContents = cardContents;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public int getCardSequential() {
		return cardSequential;
	}
	public void setCardSequential(int cardSequential) {
		this.cardSequential = cardSequential;
	}
	
}
