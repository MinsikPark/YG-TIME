package kr.co.ygtime.DTO;

public class CardDTO {
	
	private int listNum;
	private int cardNum;
	private String cardName;
	private String cardContents;
	private int deleteOk;
	private int cardSequential;
	
	public CardDTO() {}
	
	public CardDTO(int listNum, int cardNum, String cardName, String cardContents,int deleteOk, int cardSequential) {
		
		this.listNum = listNum;
		this.cardNum = cardNum;
		this.cardName = cardName;
		this.cardContents = cardContents;
		this.deleteOk = deleteOk;
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
		return deleteOk;
	}

	public void setDeleteCheck(int deleteOk) {
		this.deleteOk = deleteOk;
	}

	public int getCardSequential() {
		return cardSequential;
	}
	public void setCardSequential(int cardSequential) {
		this.cardSequential = cardSequential;
	}
	
}
