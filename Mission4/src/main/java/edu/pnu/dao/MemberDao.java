package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberDao {

	List<MemberVO> getMembers();

	MemberVO getMember(Integer id);

	MemberVO addMember(MemberVO member);

	MemberVO updateMember(MemberVO member);

	boolean deleteMember(Integer id);

}