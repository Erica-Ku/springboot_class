package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService() {
		memberDao = new MemberDao();
	}
	
	public List<MemberVO> getMembers() {
		return memberDao.getMembers();
	}

	public MemberVO getMember(Integer id) {
		return memberDao.getMember(id);
	}

	public MemberVO addMember(MemberVO memberVO) {
		return memberDao.addMember(memberVO);
	}

	public MemberVO updateMembers(MemberVO memberVO) {
		return memberDao.updateMembers(memberVO);
	}

	public MemberVO removeMember(Integer id) {
		return memberDao.removeMember(id);
	}
}