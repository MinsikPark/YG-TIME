/* 
    파일명: CardDAO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 김 진 원
*/

package kr.co.ygtime.DAO;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.ygtime.DTO.CardDTO;
import kr.co.ygtime.DTO.CardMemberDTO;
import kr.co.ygtime.DTO.CheckBoxDTO;
import kr.co.ygtime.DTO.CommentDTO;
import kr.co.ygtime.DTO.UpLoadDTO;

public class CardDAO {
	DataSource ds = null;
	
	public CardDAO() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//카드 생성
	public int cardInsert(CardDTO card) {
		return 0;
	}
	
	//한개카드 검색
	public CardDTO cardSelect(int cardNum) {
		return null;
	}
	
	//모든 카드 검색
	public List<CardDTO> allCardSelect(int checkNum) {
		return null;
	}
	
	//카드 수정
	public int cardUpdate(CardDTO card) {
		return 0;
	}
	
	//카드 삭제 (삭제여부 업데이트)
	public int cardDelete(int carNum) {
		return 0;
	}
		
	//////////////////////////////////
	
	//체크박스생성
	public int checkInsert(CheckBoxDTO check) {
		return 0;
	}
	
	//체크박스검색(선택)
	public CheckBoxDTO checkSelect(int checkNum) {
		return null;
	}
	
	//모든 체크박스 검색
	public List<CheckBoxDTO> allCheckSelect(int cardNum) {
		return null;
	}
	
	//체크박스 수정
	public int checkUpdate(CheckBoxDTO check) {
		return 0;
	}
	
	//체크박스 삭제 (정말로 삭제)
	public int checkDelete(int checkNum) {
		return 0;
	}
	
	//////////////////////////////////
	
	//업로드 생성(추가)
	public int upLoadInsert(UpLoadDTO upLoad) {
		return 0;
	}
	
	//업로드 검색(선택)
	public UpLoadDTO upLoadSelect(int upLodadNum) {
		
		return null;
	}
	
	//업로드리스트검색(선택)
	public List<UpLoadDTO> allUpLoadSelect(int cardNum){
		
		return null;
	}
	
	//업로드삭제(정말로 삭제)
	public int upLoadDelete(int upLoadNum) {
		
		return 0;
	}
	
	//////////////////////////////////////
	
	//댓글 생성(추가)
	public int commentInsert(CommentDTO comment) {
		return 0;
	}
	
	//댓글 검색(선택)
	public CommentDTO upLoadInsert(int commentNum) {
		return null;
	}
	
	//모든 댓글 검색
	public List<CommentDTO> allCommentSelect(int cardNum) {
		return null;
	}
	
	//댓글 수정
	public int commentUpdate(CommentDTO comment) {
		return 0;
	}
	
	//댓글 삭제(정말로 삭제)
	public int commentDelete(int commentNum) {
		return 0;
	}
	
	/////////////////////////////////
	
	//카드멤버 추가
	public int cardMemberInsert(CardMemberDTO cardMember) {
		return 0;
	}
	
	//카드멤버 검색(선택)
	public CardMemberDTO cardMemberSelect(int commentNum) {
		return null;
	}
	
	//모든 카드멤버 검색
	public List<CardMemberDTO> allCardMemberSelect(int cardNum) {
		return null;
	}
	
	//카드멤버 삭제 (정말로 삭제)
	public int cardMemberDelete(int cardMemberNum) {
		return 0;
	}
}
