package co.micol.prj.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.micol.prj.member.vo.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberSelectList();
	MemberVO memberSelect(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberDelete(MemberVO vo);
	int memberUpdate(MemberVO vo);
	//매개변수가 2개라면 달라짐 어노테이션표시
	MemberVO memberLogin(@Param("id") String id, @Param("password") String password); //사실 로그인은 memberSelect만으로도 충분함
	boolean isMemberIdCheck(String id); // 존재하면 false (회원가입시 중복체크) is명명 존재하면 false return (관례적)
	//mapper가 못받아들이기때문에 파라미터로 넘김
}
