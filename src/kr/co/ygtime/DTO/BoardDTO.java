package kr.co.ygtime.DTO;

public class BoardDTO {
	private int boardNum;
	private int projectNum;
	private String boardTitle;
	private String detail;
	private String boardStartDate;
	private String boardEndDate;
	private String label;
	
	public BoardDTO() {}
	
	public BoardDTO(int boardNum, int projectNum, String boardTitle, String detail, String boardStartDate, String boardEndDate, String label) {
		this.boardNum = boardNum;
		this.projectNum = projectNum;
		this.boardTitle = boardTitle;
		this.detail = detail;
		this.boardStartDate = boardStartDate;
		this.boardEndDate = boardEndDate;
		this.label = label;
	}
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(int projectNum) {
		this.projectNum = projectNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardStartDate() {
		return boardStartDate;
	}
	public void setBoardStartDate(String boardStartDate) {
		this.boardStartDate = boardStartDate;
	}
	public String getBoardEndDate() {
		return boardEndDate;
	}
	public void setBoardEndDate(String boardEndDate) {
		this.boardEndDate = boardEndDate;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
