package kr.co.ygtime.DTO;

public class InviteMsgDTO {
	private int msgNum;
	private String userId;
	private int projectNum;
	private String inviteUserId;
		
	public InviteMsgDTO() {}
	
	public InviteMsgDTO(int msgNum, String userId, int projectNum, String inviteUserId) {
		this.msgNum = msgNum;
		this.userId = userId;
		this.projectNum = projectNum;
		this.inviteUserId = inviteUserId;
	}
	
	public int getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public String getInviteUserId() {
		return inviteUserId;
	}
	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
}
