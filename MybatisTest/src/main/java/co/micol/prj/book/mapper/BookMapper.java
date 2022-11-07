package co.micol.prj.book.mapper;

import java.util.List;

import co.micol.prj.book.vo.BookVO;

public interface BookMapper {
	List<BookVO> bookSelectList();  //book 전체리스트 가져오기 getBookList(R)
	BookVO bookSelect(BookVO vo);   //한권의 책 상세내역(R) getBook
	int bookInsert(BookVO vo);      //도서입력(C) setBook
	int bookDelete(BookVO vo);      //도서삭제(D) deleteBook
	int bookUpdate(BookVO vo);      //도서변경(U) updateBook
}
