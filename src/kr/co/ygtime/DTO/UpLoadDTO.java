package kr.co.ygtime.DTO;

public class UpLoadDTO {
	private int fileNum;
	private int cardNum;
	private String filePath;
	
	public UpLoadDTO() {}
	
	public UpLoadDTO(int fileNum, int cardNum, String filePath) {
		this.fileNum = fileNum;
		this.cardNum = cardNum;
		this.filePath = filePath;
		
	}
	
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public int getCardNum() {
		return cardNum;
	}
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
