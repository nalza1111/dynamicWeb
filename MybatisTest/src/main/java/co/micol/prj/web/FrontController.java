package co.micol.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.book.command.BookList;
import co.micol.prj.common.Command;
import co.micol.prj.main.MainCommand;
import co.micol.prj.member.command.AjaxIdCheck;
import co.micol.prj.member.command.Logout;
import co.micol.prj.member.command.MemberJoin;
import co.micol.prj.member.command.MemberJoinForm;
import co.micol.prj.member.command.MemberLogin;
import co.micol.prj.member.command.MemberLoginForm;
/**
 *모든요청을 받아들이는 컨트롤러
 * */
 
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();
	
	public FrontController() {
        super();
    }
    
    //요청한 것을 실행하는 명령을 모아 두는 곳
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand()); //처음 보여줄 페이지 명령
		map.put("/bookList.do", new BookList()); 
		//로그인
		map.put("/memberLoginForm.do", new MemberLoginForm());//로그인 폼
		map.put("/memberLogin.do", new MemberLogin());//로그인 처리
		map.put("/logout.do", new Logout());//로그아웃
		map.put("/memberJoinForm.do", new MemberJoinForm());//회원가입
		map.put("/ajaxIdCheck.do", new AjaxIdCheck());//ajax를 이용한 아이디중복체크
		map.put("/memberJoin.do", new MemberJoin());
	}
	
	//요청을 분석하고 실행, 결과를 돌려주는 곳
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //한글깨짐방지
		String uri = request.getRequestURI(); //요청한 uri를 구함(url에서 도메인네임을 제거=uri)
		String contextPath = request.getContextPath(); //루트를 구함(정확하게 contextPath)
		String page = uri.substring(contextPath.length()); //실제 수행할 요청을 구함
		
		Command command = map.get(page); //init 메소드에서 수행할 명령을 가져온다
		String viewPage = command.exec(request, response);//명령을 수행하고 결과를 돌려받음 ->뷰집단에 가서 적절한 페이지를찾음(이 과정을 뷰리절브라고 함)
		
		//viewResolve 파트
		if(!viewPage.endsWith(".do") && viewPage != null) { //viewReserve가 들어가는 곳###
			//ajax 처리 (스프링은 뷰리절브를 갖고 있지만 jsp서블릿은 없기에 ajax 스타트위드~~`~?)
			if(viewPage.startsWith("ajax:")){
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			//타일즈 처리 (만약.tiles면 ~.jsp를 찾기)
			if(!viewPage.endsWith(".tiles")) { //타일즈 안태움
				viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; 
			}
			//map 처리
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); //적절한 뷰를 찾아 디스패쳐
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(viewPage); //*.do 로 들어올 때 돌아가는 곳 권한을 위임하는데 req/resp가 없음
		}
		
	}

}
