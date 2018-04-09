/* 
    파일명: InviteMsgDTO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 최 재 욱 
*/

package kr.co.ygtime.DTO;

public class InviteMsgDTO {
	private int msgNum;
	private String userId;
	private int projectNum;
	private String inviteUserId;
	private String msgDate;
		
	public InviteMsgDTO() {}
	
	public InviteMsgDTO(int msgNum, String userId, int projectNum, String inviteUserId, String msgDate) {
		this.msgNum = msgNum;
		this.userId = userId;
		this.projectNum = projectNum;
		this.inviteUserId = inviteUserId;
		this.msgDate = msgDate;
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

	public String getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(String msgDate) {
		this.msgDate = msgDate;
	}
	
}
