package kr.co.ygtime.DTO;

public class MemberDTO {
	private String userId;
	private String userPwd;
	private String userNicname;
	private String userProfile;
	
	public MemberDTO() {}
	
	public MemberDTO(String userId, String userPwd, String userNicname, String userProfile) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userNicname = userNicname;
		this.userProfile = userProfile;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserNicname() {
		return userNicname;
	}
	public void setUserNicname(String userNicname) {
		this.userNicname = userNicname;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	
}
