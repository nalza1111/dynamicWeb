package co.micol.prj.book.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.prj.book.service.BookService;
import co.micol.prj.book.service.Impl.BookServiceImpl;
import co.micol.prj.book.vo.BookVO;
import co.micol.prj.common.Command;

public class BookList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 책목록 가져오기
		BookService dao = new BookServiceImpl();
		List<BookVO> books = new ArrayList<BookVO>();
		System.out.println("dd");
		books = dao.bookSelectList();
		System.out.println("ddd");
		request.setAttribute("books", books);
		
		return "book/bookList.tiles";
	}

}
