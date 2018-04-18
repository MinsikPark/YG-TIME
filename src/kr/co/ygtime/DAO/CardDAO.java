/* 
    파일명: CardDAO.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 9.
    작성자: 김 진 원
*/

package kr.co.ygtime.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into CARD(cardnum, listnum, cardname, cardcontents, cardsequential, deleteok)" + 
						    " values(card_idx.nextval,?,?,null,?,0)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, card.getListNum());
				pstmt.setString(2, card.getCardName());
				pstmt.setInt(3, card.getCardSequential());
				// pstmt.setInt(3, card.getCardSequential()); <- 순서 리스트 안에 순서 최대값 찾고 최대값 + 1 넣어주기;
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 리스트에 해당되는 카드의 최대 순번을 가져온다.
	 작성자명 : 김 진 원
	*/
	public int maxCardSequential(int listNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxCard = 0;
		try {
				conn = ds.getConnection();
				String sql ="select max(cardsequential) as cardmax from CARD where listnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, listNum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					maxCard =  rs.getInt("cardmax");
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return maxCard;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 한개 카드 검색
	 작성자명 : 김 진 원
	 */
	public CardDTO cardSelect(int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CardDTO carddto = null;
		
		try {
				conn = ds.getConnection();
				String sql ="select cardnum, listnum, cardname, cardcontents, cardsequential, deleteok "
						+ "from CARD where cardnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardNum);
				
				rs = pstmt.executeQuery();
				
				carddto = new CardDTO();
				
				if(rs.next()) {
					carddto.setCardNum(rs.getInt("cardnum"));
					carddto.setListNum(rs.getInt("listnum"));
					carddto.setCardName(rs.getString("cardname"));
					carddto.setCardContents(rs.getString("cardcontents"));
					carddto.setDeleteCheck(rs.getInt("cardsequential"));
					carddto.setCardSequential(rs.getInt("deleteok"));
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return carddto;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 모든 카드 검색
	 작성자명 : 김 진 원
	*/
	public List<CardDTO> allCardSelect(int listNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CardDTO> cardlist = null;
		try {
			
			conn = ds.getConnection();
			String sql= 	"select cardnum, listnum, cardname, cardcontents, cardsequential, deleteok "
							+ "from CARD where listnum=? and deleteok=0 ORDER BY cardsequential ASC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, listNum); 
			rs = pstmt.executeQuery();
			
			cardlist = new ArrayList<>();
			while(rs.next()) {
				
				CardDTO carddto = new CardDTO(); 
				carddto.setCardNum(rs.getInt("cardnum"));
				carddto.setListNum(rs.getInt("listnum"));
				carddto.setCardName(rs.getString("cardname"));
				carddto.setCardContents(rs.getString("cardcontents"));
				carddto.setDeleteCheck(rs.getInt("deleteok"));
				
				//순번 
				carddto.setCardSequential(rs.getInt("cardsequential"));

				cardlist.add(carddto);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return cardlist;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 카드 수정
	 작성자명 : 김 진 원
	*/
	public int cardUpdate(CardDTO card) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql="update CARD set listnum=?, cardname=?, cardcontents=? " //sequential 뺌.
					+ "where cardnum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, card.getListNum());
			pstmt.setString(2, card.getCardName());
			pstmt.setString(3, card.getCardContents());
			pstmt.setInt(4, card.getCardNum());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 카드 삭제 (삭제여부 업데이트)
	 작성자명 : 김 진 원
	*/
	public int cardDelete(int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql="update CARD set deleteok=1, cardsequential=-1 where cardnum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cardNum);
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
		
	//////////////////////////////////
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 체크박스생성
	 작성자명 : 김 진 원
	*/
	public int checkInsert(CheckBoxDTO check) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into CHECKBOX(checknum, cardnum, checked, checkboxcontents)" + 
						    " values(?,?,0,?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, check.getCheckNum());
				//1-> 카드넘버에 해당하는 체크넘버의 최대넘버 + 1
				pstmt.setInt(2, check.getCardNum());
				pstmt.setString(3, check.getCheckBoxContents());
				
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 되는 카드넘버에 최대 체크넘버를 가져온다 (카드넘버당 체크박스개수)
	 작성자명 : 김 진 원
	*/
	public int maxCheckNum(int cardnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxCheck = 0;
		try {
				conn = ds.getConnection();
				String sql ="select nvl(max(checknum), 0) as checkmax from CHECKBOX where cardnum=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardnum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					maxCheck =  rs.getInt("checkmax");
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return maxCheck;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 체크박스검색(선택)
	 작성자명 : 김 진 원
	*/
	public CheckBoxDTO checkSelect(int checkNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CheckBoxDTO checkdto = null;
		
		try {
				conn = ds.getConnection();
				String sql ="select checknum, cardnum, checked, checkboxcontents "
							+ "from CHECKBOX where checknum = ? and cardnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, checkNum);
				pstmt.setInt(2, cardNum);
				
				rs = pstmt.executeQuery();
				
				checkdto = new CheckBoxDTO();
				
				if(rs.next()) {
					checkdto.setCheckNum(rs.getInt("checknum"));
					checkdto.setCardNum(rs.getInt("cardnum"));
					checkdto.setChecked(rs.getInt("checked"));
					checkdto.setCheckBoxContents(rs.getString("checkboxcontents"));
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return checkdto;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 카드의 모든 체크박스 검색
	 작성자명 : 김 진 원
	*/
	public List<CheckBoxDTO> allCheckSelect(int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CheckBoxDTO> checklist = null;
		try {
			
			conn = ds.getConnection();
			String sql= "select checknum, cardnum, checked, checkboxcontents "
						+ "from CHECKBOX where cardnum=? ORDER BY checknum ASC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cardNum); 
			rs = pstmt.executeQuery();
			
			checklist = new ArrayList<>();
			while(rs.next()) {
				
				CheckBoxDTO checkdto = new CheckBoxDTO(); 
				checkdto.setCheckNum(rs.getInt("checknum"));
				checkdto.setCardNum(rs.getInt("cardnum"));
				checkdto.setChecked(rs.getInt("checked"));
				checkdto.setCheckBoxContents(rs.getString("checkboxcontents"));
				
				checklist.add(checkdto);  
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return checklist;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 체크박스 수정
	 작성자명 : 김 진 원
	*/
	public int checkUpdate(CheckBoxDTO check) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql="update CHECKBOX set checked=?, checkboxcontents=? "
					+ "where cardnum=? and checknum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, check.getChecked());
			pstmt.setString(2, check.getCheckBoxContents());
			pstmt.setInt(3, check.getCardNum());
			pstmt.setInt(4, check.getCheckNum());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 체크박스 삭제 (정말로 삭제) 삭제시 순번처리 해주기
	 작성자명 : 김 진 원
	*/
	public int checkDelete(int checkNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delrow = 0;
		int uprow = 0;
		
		try {
				conn = ds.getConnection();

				String sql="delete from CHECKBOX where cardnum=? and checknum=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardNum);
				pstmt.setInt(2, checkNum);
				
				delrow = pstmt.executeUpdate();
				
				if(delrow > 0) {
					pstmt.close();
					String numminus = "update CHECKBOX set checknum=checknum-1 "
									+ "where cardnum=? and checknum > ?";
					
					pstmt = conn.prepareStatement(numminus);
					pstmt.setInt(1, cardNum);
					pstmt.setInt(2, checkNum);
					
					uprow = pstmt.executeUpdate();
					
					if(uprow > 0) {
						conn.commit();
					}
				}
				
			
		}catch (Exception e) {
					try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return uprow;
	}
	
	//////////////////////////////////
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 업로드 생성(추가)
	 작성자명 : 김 진 원
	*/
	public int upLoadInsert(UpLoadDTO upLoad) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into UPLOAD(filenum, cardnum, filepath, originfilename)" + 
						    " values(?,?,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, upLoad.getFileNum());
				//1-> 카드넘버에 해당하는 파일넘버의 최대넘버 + 1
				pstmt.setInt(2, upLoad.getCardNum());
				pstmt.setString(3, upLoad.getFilePath());
				pstmt.setString(4, upLoad.getOriginFileName());
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 되는 카드넘버에 최대 파일넘버를 가져온다 (카드넘버당 파일개수)
	 작성자명 : 김 진 원
	*/
	public int maxFileNum(int cardnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxfile = 0;
		try {
				conn = ds.getConnection();
				String sql ="select max(filenum) as filemax from UPLOAD where cardnum=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardnum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					maxfile =  rs.getInt("filemax");
				}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return maxfile;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 업로드 검색(선택)
	 작성자명 : 김 진 원
	*/
	public UpLoadDTO upLoadSelect(int upLoadNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UpLoadDTO uploaddto = null;
		
		try {
				conn = ds.getConnection();
				String sql ="select filenum, cardnum, filepath , originfilename "
							+ "from UPLOAD where checknum = ? and cardnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, upLoadNum);
				pstmt.setInt(2, cardNum);
				
				rs = pstmt.executeQuery();
				
				uploaddto = new UpLoadDTO();
				
				if(rs.next()) {
					uploaddto.setFileNum(rs.getInt("filenum"));
					uploaddto.setCardNum(rs.getInt("cardnum"));
					uploaddto.setFilePath(rs.getString("filepath"));
					uploaddto.setOriginFileName(rs.getString("originfilename"));
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return uploaddto;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당카드의 모든 업로드리스트검색(선택)
	 작성자명 : 김 진 원
	*/
	public List<UpLoadDTO> allUpLoadSelect(int cardNum){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UpLoadDTO> uploadlist = null;
		try {
			
			conn = ds.getConnection();
			String sql= "select filenum, cardnum, filepath, originfilename "
						+ "from UPLOAD where cardnum=? ORDER BY filenum ASC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cardNum); 
			rs = pstmt.executeQuery();
			
			uploadlist = new ArrayList<>();
			while(rs.next()) {
				
				UpLoadDTO uploaddto = new UpLoadDTO(); 
				uploaddto.setFileNum(rs.getInt("filenum"));
				uploaddto.setCardNum(rs.getInt("cardnum"));
				uploaddto.setFilePath(rs.getString("filepath"));
				uploaddto.setOriginFileName(rs.getString("originfilename"));
				uploadlist.add(uploaddto);  
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return uploadlist;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 업로드삭제(정말로 삭제) 삭제시 순번처리
	 작성자명 : 김 진 원
	*/
	public int upLoadDelete(int upLoadNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delrow = 0;
		int uprow = 0;
		
		try {
				conn = ds.getConnection();

				String sql="delete from UPLOAD where cardnum=? and filenum=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardNum);
				pstmt.setInt(2, upLoadNum);
				
				delrow = pstmt.executeUpdate();
				if(delrow > 0) {
					pstmt.close();
					String numminus = "update UPLOAD set filenum=filenum-1 "
									+ "where cardnum=? and filenum > ?";
					
					pstmt = conn.prepareStatement(numminus);
					pstmt.setInt(1, cardNum);
					pstmt.setInt(2, upLoadNum);
					
					uprow = pstmt.executeUpdate();
					if(uprow > 0) {
						conn.commit();
					}
				}
				
			
		}catch (Exception e) {
					try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return uprow;
	}
	
	//////////////////////////////////////
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 댓글 생성(추가)
	 작성자명 : 김 진 원
	*/
	public int replyInsert(ReplyDTO reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into REPLY(replynum, userid, cardnum, replycontents)" + 
						    " values(?,?,?,?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, reply.getReplyNum());
				//1-> 카드넘버에 해당하는 댓글넘버의 최대넘버 + 1
				pstmt.setString(2, reply.getUserId());
				pstmt.setInt(3, reply.getCardNum());
				pstmt.setString(4, reply.getReplyContents());
				
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 되는 카드넘버에 최대 댓글넘버를 가져온다 (카드넘버당 댓글개수)
	 작성자명 : 김 진 원
	*/
	public int maxReplyNum(int cardnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int maxreply = 0;
		try {
				conn = ds.getConnection();
				String sql ="select nvl(max(replynum), 0) as replymax from REPLY where cardnum=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardnum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					maxreply =  rs.getInt("replymax");
				}
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return maxreply;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 댓글 검색(선택)
	 작성자명 : 김 진 원
	*/
	public ReplyDTO upLoadInsert(int replyNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReplyDTO replydto = null;
		
		try {
				conn = ds.getConnection();
				String sql ="select replynum, userid, cardnum, replycontents "
							+ "from REPLY where replynum = ? and cardnum = ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, replyNum);
				pstmt.setInt(2, cardNum);
				
				rs = pstmt.executeQuery();
				
				replydto = new ReplyDTO();
				
				if(rs.next()) {
					replydto.setReplyNum(rs.getInt("replynum"));
					replydto.setUserId(rs.getString("userid"));
					replydto.setCardNum(rs.getInt("cardnum"));
					replydto.setReplyContents(rs.getString("replycontents"));
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return replydto;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 해당 카드의 모든 댓글 검색
	 작성자명 : 김 진 원
	*/
	public List<ReplyDTO> allReplySelect(int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReplyDTO> replylist = null;
		try {
			
			conn = ds.getConnection();
			String sql= "select replynum, userid, cardnum, replycontents "
						+ "from REPLY where cardnum=? ORDER BY replynum ASC";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cardNum); 
			rs = pstmt.executeQuery();
			
			replylist = new ArrayList<>();
			while(rs.next()) {
				
				ReplyDTO replydto = new ReplyDTO(); 
				replydto.setReplyNum(rs.getInt("replynum"));
				replydto.setUserId(rs.getString("userid"));
				replydto.setCardNum(rs.getInt("cardnum"));
				replydto.setReplyContents(rs.getString("replycontents"));
				
				replylist.add(replydto);  
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return replylist;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 댓글 수정
	 작성자명 : 김 진 원
	*/
	public int replyUpdate(ReplyDTO reply) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql="update REPLY set replycontents=? "
					+ "where replynum=? and cardnum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getReplyContents());
			pstmt.setInt(2, reply.getReplyNum());
			pstmt.setInt(3, reply.getCardNum());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 댓글 삭제(정말로 삭제)
	 작성자명 : 김 진 원
	*/
	public int replyDelete(String userid, int replyNum, int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
				conn = ds.getConnection();
				
				//아이디검증(댓글을 쓴사람이 맞는지)
				String sel_id_sql = "select userid from REPLY where replynum=? and cardnum=?";
				
				//게시글 삭제
				String del_reply_sql = "delete from REPLY where replynum=? and cardnum=?";
				
				//게시글 삭제후 삭제한 댓글넘버 보다 큰 댓글들 넘버 -1
				String up_reply_sql = "update REPLY set replynum=replynum-1 where cardnum=? and replynum > ?";
				
				pstmt = conn.prepareStatement(sel_id_sql);
				pstmt.setInt(1, replyNum);
				pstmt.setInt(2, cardNum);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) { 
					//아이디가 일치하는지 확인
					if(userid.equals(rs.getString("userid"))) {
						pstmt.close();
						//실제 삭제
						pstmt = conn.prepareStatement(del_reply_sql);
						pstmt.setInt(1, replyNum);
						pstmt.setInt(2, cardNum);
						
						row = pstmt.executeUpdate();
						if(row > 0) {
							pstmt.close();
							//삭제 성공 후 댓글넘버 업데이트
							pstmt = conn.prepareStatement(up_reply_sql);
							pstmt.setInt(1, cardNum);
							pstmt.setInt(2, replyNum);
							
							row += pstmt.executeUpdate();
						}
					}
				}
						
				if(row > 0) {
					conn.commit();
				}
			
		}catch (Exception e) {
					try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/////////////////////////////////
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 카드멤버 추가
	 작성자명 : 김 진 원
	*/
	public int cardMemberInsert(CardMemberDTO cardMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
				conn = ds.getConnection();
				String sql ="insert into CARDMEMBER(cardnum, userid)" + 
						    " values(?,?)";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, cardMember.getCardNum());
				pstmt.setString(2, cardMember.getUserId());
				
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 카드넘버 당 모든 카드멤버 검색
	 작성자명 : 김 진 원
	*/
	public List<CardMemberDTO> allCardMemberSelect(int cardNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CardMemberDTO> cardmemberlist = null;
		try {
			
			conn = ds.getConnection();
			String sql= "select cardnum, userid "
						+ "from CARDMEMBER where cardnum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cardNum); 
			rs = pstmt.executeQuery();
			
			cardmemberlist = new ArrayList<>();
			while(rs.next()) {
				
				CardMemberDTO cardmemberdto = new CardMemberDTO(); 
				cardmemberdto.setCardNum(rs.getInt("cardnum"));
				cardmemberdto.setUserId(rs.getString("userid"));
				
				cardmemberlist.add(cardmemberdto);  
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return cardmemberlist;
	}
	
	/**
	 날      짜 : 2018. 4. 10.
	 기      능 : 카드멤버 삭제 (정말로 삭제)
	 작성자명 : 김 진 원
	*/
	public int cardMemberDelete(CardMemberDTO cardMember) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
				conn = ds.getConnection();

				String sql="delete from CARDMEMBER where cardnum=? and userid=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cardMember.getCardNum());
				pstmt.setString(2, cardMember.getUserId());
				
				row = pstmt.executeUpdate();
				
		}catch (Exception e) {
					try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
	
	/**
	 날      짜 : 2018. 4. 16.
	 기      능 : 카드 순서 수정
	 작성자명 : 아 윤 근
	*/
	public int cardSequenceUpdate(CardDTO card) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql="update CARD set listnum=?, cardsequential=? where cardnum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, card.getListNum());
			pstmt.setInt(2, card.getCardSequential());
			pstmt.setInt(3, card.getCardNum());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return row;
	}
}
