package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.MemberVO;

public class MemberService {
	
	List<MemberVO> list = new ArrayList<>();
	
	public MemberService() {
		list.add(new MemberVO(1, "1234", "홍길동", new Date()));
		list.add(new MemberVO(2, "1235", "홍이동", new Date()));
		list.add(new MemberVO(3, "1236", "홍삼동", new Date()));
	}
	
	public List<MemberVO> getMembers() {
		return list;
	}
	
	public MemberVO getMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId()==id) {
				return m;
			}
		}
	    return null;
	}
	
	public MemberVO addMember(MemberVO memberVO) {
		list.add(memberVO);
		return memberVO;
	}
	
	public MemberVO updateMembers(MemberVO memberVO) {
		for (MemberVO m : list) {
			if (m.getId()==memberVO.getId()) {
				m.setName(memberVO.getName());
				m.setPass(memberVO.getPass());
				return m;
			}
		}
		
		return null;
	}
	
	public MemberVO removeMember(Integer id) {
		for (MemberVO m : list) {
			if (m.getId()==id) {
				list.remove(m);
				return m;
			}
		}
		return null;
	}
}