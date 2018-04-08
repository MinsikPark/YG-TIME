package kr.co.ygtime.DTO;

public class InviteMsgDTO {
	private int msgNum;
	private String userId;
	private int projectNum;
	private String inviteUserId;
	private String inviteUserNicname;
	
	public InviteMsgDTO() {}
	
	public InviteMsgDTO(int msgNum, String userId, int projectNum, String inviteUserId, String inviteUserNicname) {
		this.msgNum = msgNum;
		this.userId = userId;
		this.projectNum = projectNum;
		this.inviteUserId = inviteUserId;
		this.inviteUserNicname = inviteUserNicname;
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
	public String getInviteUserNicname() {
		return inviteUserNicname;
	}
	public void setInviteUserNicname(String inviteUserNicname) {
		this.inviteUserNicname = inviteUserNicname;
	}
	
}
