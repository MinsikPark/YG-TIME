/* 
    파일명: MemberDAO.java
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

import kr.co.ygtime.DTO.InviteMsgDTO;
import kr.co.ygtime.DTO.MemberDTO;

public class MemberDAO {
	DataSource ds;
	
	public MemberDAO() throws NamingException {
		Context context = new InitialContext();
		//JNDI 
		//context : container(was) 안에서 이름기반으로 검색 제공
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//회원가입
	public int memberInsert(MemberDTO member) {
		return 0;
	}
	
	//회원 검색
	public MemberDTO memberSelete(String userId) {
		return null;
	}
	
	//회원 수정
	public int memberUpdate(MemberDTO member) {
		return 0;
	}
	
	//회원 탈퇴
	public int memberDelete(String userId) {
		return 0;
	}
	
	////////////////
	
	//초대메세지보내기
	public int inviteMsgInsert(InviteMsgDTO invitemsg) {
		return 0;
	}
	
	//모든 초대메세지 검색 (불러오기)
	public List<InviteMsgDTO> inviteMsgSelect(String userId) {
		return null;
	}
	
	//초대메세지삭제
	public int inviteMsgDelete(String userId, int projectNum) {
		return 0;
	}
}
