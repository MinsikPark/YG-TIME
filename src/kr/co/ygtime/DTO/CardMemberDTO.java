/* 
    파일명: CardMemberDTO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 최 재 욱 
*/

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
