package edu.pnu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.MemberVO;

public class MemberService {
	
	private List<MemberVO> list;
	
	public MemberService() {
		list = new ArrayList<>();
		for (int i = 1 ; i <= 5 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
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
		memberVO.setId(list.size()+1);
		memberVO.setRegidate(new Date());
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