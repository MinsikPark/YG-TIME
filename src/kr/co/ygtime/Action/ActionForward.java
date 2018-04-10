/* 
    파일명: ActionForward.java
    설명: 액션 포워드 
    작성일: 2018. 4. 10.
    작성자: 김진원
*/

package kr.co.ygtime.Action;

public class ActionForward {
	private boolean isRedirect =false;
	private String path = null;
	
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
