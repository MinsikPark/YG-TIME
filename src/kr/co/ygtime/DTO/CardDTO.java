package kr.co.ygtime.DTO;

public class CardDTO {
	
	
	private int listNum;
	private int cardNum;
	private String cardName;
	private String cardContents;
	private int deleteCheck;
	private int cardSequential;
	
	public CardDTO() {}
	
	public CardDTO(int listNum, int cardNum, String cardName, String cardContents,int deleteCheck, int cardSequential) {
		
		this.listNum = listNum;
		this.cardNum = cardNum;
		this.cardName = cardName;
		this.cardContents = cardContents;
		this.deleteCheck = deleteCheck;
		this.cardSequential = cardSequential;
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

	public int getDeleteCheck() {
		return deleteCheck;
	}

	public void setDeleteCheck(int deleteCheck) {
		this.deleteCheck = deleteCheck;
	}

	public int getCardSequential() {
		return cardSequential;
	}
	public void setCardSequential(int cardSequential) {
		this.cardSequential = cardSequential;
	}
	
}
