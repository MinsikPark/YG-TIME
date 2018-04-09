package kr.co.ygtime.DTO;

public class CommentDTO {
	private int commentNum;
	private String userId;
	private int cardNum;
	private String commentContents;
	
	public CommentDTO() {}
	
	public CommentDTO(int commentNum, String userId, int cardNum, String commentContents) {
		this.commentNum = commentNum;
		this.userId = userId;
		this.cardNum = cardNum;
		this.commentContents = commentContents;
	}
	
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
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
	public String getCommentContents() {
		return commentContents;
	}
	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}
	
	
}
