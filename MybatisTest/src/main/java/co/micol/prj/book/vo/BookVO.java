package co.micol.prj.book.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookVO { //vo 멤버변수와 게터세터(웹에서는 vo) (자바에서는 DTO) 테이블과 1대1매칭
	private String bookCode;
	private String bookTitle;
	private String bookAuthor;
	private String bookPress;
	private int bookPrice;
}
