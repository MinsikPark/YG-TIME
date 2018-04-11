/* 
    파일명: MsgAgreeService.java
    설명: 프로젝트 내용 추가 Service부
    작성일: 2018. 4. 11.
    작성자: 김진원
*/

package kr.co.ygtime.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ygtime.Action.Action;
import kr.co.ygtime.Action.ActionForward;
import kr.co.ygtime.DAO.ProjectDAO;
import kr.co.ygtime.DTO.TeamDTO;

public class MsgAgreeService implements Action{


	//초대 메세지를 수락한다.
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//초대승인 -> 1.팀 insert -> 2.메세지 Delete -> 3.메인화면으로
		
		ActionForward forward = null;
		
		try {
			//파라미터로 아이디와 초대받은 프로젝튼 넘버를 가져온다
			String userid = request.getParameter("userid");
			int projectnum = Integer.parseInt(request.getParameter("projectnum"));
			
			//TeamDTO 객체에 넣어준다.
			TeamDTO teamdto = new TeamDTO();
			teamdto.setUserId(userid);
			teamdto.setProjectNum(projectnum);
			
			ProjectDAO projectdao = new ProjectDAO();
			
			//2. 팀 insert
			int row = projectdao.teamInsert(teamdto); //DB 팀에 넣어준다.
			
			forward = new ActionForward();
			if(row > 0) {
				
				System.out.println("드러왔따2");
				//초대 승인 (팀 insert) 성공
				//3.메세지 Delete 경로
				forward.setRedirect(false);
	       	    forward.setPath("/msgdel.member?userid="+userid+"&projectnum="+projectnum);
			}else {
				//실패 (main 화면 가기)
				System.out.println("드러왔따3");
				forward.setRedirect(false);
				forward.setPath("/member_test/invite_choice_test.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}
	
}
