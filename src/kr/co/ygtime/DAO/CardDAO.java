/* 
    파일명: CardDAO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 김 진 원
*/

package kr.co.ygtime.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.ygtime.DTO.CardDTO;
import kr.co.ygtime.DTO.CardMemberDTO;
import kr.co.ygtime.DTO.CheckBoxDTO;
import kr.co.ygtime.DTO.ReplyDTO;
import kr.co.ygtime.DTO.UpLoadDTO;

public class CardDAO {
	DataSource ds = null;
	
	public CardDAO() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	/**
	 날      짜 : 2018. 4. 9.
	 기      능 : 카드 생성
	 작성자명 : 김 진 원
	*/
	public int cardInsert(CardDTO card) {
		/*Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into jspboard(idx,writer,pwd,subject,content,email,homepage,writedate,readnum,filename,filesize,refer)" + 
						    " values(jspboard_idx.nextval,?,?,?,?,?,?,sysdate,0,?,0,?)";
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1,boardata.getWriter());
				pstmt.setString(2,boardata.getPwd());
				pstmt.setString(3,boardata.getSubject());
				pstmt.setString(4,boardata.getContent());
				pstmt.setString(5,boardata.getEmail());
				pstmt.setString(6,boardata.getHomepage());
				pstmt.setString(7,boardata.getFilename());
				
				//계층형 게시판
				//refer , step , depth
				//1. 원본글 : refer , step(0) default, depth(0) default
				//2. 답변글 : refer , step(규칙) , depth(규칙)
				
				int refermax = getMaxRefer();
				int refer = refermax + 1;
				pstmt.setInt(8, refer); 
				
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		*/
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
	public int commentInsert(ReplyDTO comment) {
		return 0;
	}
	
	//댓글 검색(선택)
	public ReplyDTO upLoadInsert(int commentNum) {
		return null;
	}
	
	//모든 댓글 검색
	public List<ReplyDTO> allCommentSelect(int cardNum) {
		return null;
	}
	
	//댓글 수정
	public int commentUpdate(ReplyDTO comment) {
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
