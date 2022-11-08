package co.micol.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.micol.prj.common.Command;
import co.micol.prj.meber.service.impl.MemberServiceImpl;
import co.micol.prj.member.service.MemberService;
import co.micol.prj.member.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//로그인 처리
		MemberService dao = new MemberServiceImpl();
		MemberVO member = new MemberVO();
		//세션유지
		HttpSession session = request.getSession(); //req의 세션값을 사용하기 위해
		
		String id = request.getParameter("id");
		String pw = request.getParameter("passwd");
		String message = "아이디 또는 패스워드가 틀립니다.";
		member = dao.memberLogin(id, pw);
		System.out.println(member);
		if(member != null) {
			session.setAttribute("id", member.getId());
			session.setAttribute("name", member.getName());
			session.setAttribute("responsibility", member.getResponsibility());
			message = member.getName() + "님 환영합니다.";
			
		}
		request.setAttribute("message", message);
		return "member/memberLogin.tiles";
	}

}
