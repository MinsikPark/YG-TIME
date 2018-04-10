/* 
    파일명: CommentDTO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 최 재 욱 
*/

package kr.co.ygtime.DTO;

public class ReplyDTO {
	private int replyNum;
	private String userId;
	private int cardNum;
	private String replyContents;
	
	public ReplyDTO() {}
	
	public ReplyDTO(int replyNum, String userId, int cardNum, String replyContents) {
		this.replyNum = replyNum;
		this.userId = userId;
		this.cardNum = cardNum;
		this.replyContents = replyContents;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}

	public String getReplyContents() {
		return replyContents;
	}

	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}
	
}
