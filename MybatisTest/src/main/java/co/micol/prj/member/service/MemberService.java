package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	
	MemberVO memberLogin(String id, String password); //사실 로그인은 memberSelect만으로도 충분함
	boolean isMemberIdCheck(String id); // 존재하면 false (회원가입시 중복체크) is명명 존재하면 false return (관례적)
}
