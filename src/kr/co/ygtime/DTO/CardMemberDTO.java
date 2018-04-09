package kr.co.ygtime.DTO;

public class CardMemberDTO {
	private int cardNum;
	private String userId;
	
	public CardMemberDTO() {}
	
	public CardMemberDTO(int cardNum, String userId) {
		this.cardNum = cardNum;
		this.userId = userId;
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
