package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberDao {

	private List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for (int i = 1 ; i <= 5 ; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	
	@Override
	public Map<String, Object> getMembers() {
		Map<String, Object> map = new HashMap<>();
		String sql = "멤버 확인";
		map.put("sql", sql);
		map.put("data", list);
		return map;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		String sql = "특정 멤버 확인";
		Map<String, Object> map = new HashMap<>();
		for (MemberVO m : list) {
			if (m.getId() == id) {
				map.put("sql", sql);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		
		String sql = "멤버 추가";
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		map.put("data", member);
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		String sql = "멤버 수정";
		Map<String, Object> map = new HashMap<>();
		for (MemberVO m : list) {
			if (m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				
				map.put("sql", sql);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		String sql = "멤버 삭제";
		Map<String, Object> map = new HashMap<>();
		for (MemberVO m : list) {
			if (m.getId() == id) {
				list.remove(m);
				
				map.put("sql", sql);
				map.put("data", m);
				return map;
			}
		}
		return null;
	}
}