package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.LogDaoFileImpl;
import edu.pnu.dao.LogDaoH2Impl;
import edu.pnu.dao.MemberDao;
import edu.pnu.dao.MemberDaoH2Impl;
import edu.pnu.dao.MemberDaoListImpl;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {

	private MemberDao memberDao;
	private LogDao logDao;
	
	public MemberService() {
		//logDao = new LogDaoH2Impl();
		logDao = new LogDaoFileImpl();
		//memberDao = new MemberDaoH2Impl();
		memberDao = new MemberDaoListImpl();
	}
	
	@SuppressWarnings("unchecked")
	public List<MemberVO> getMembers() {
		Map<String, Object> map =  memberDao.getMembers();
		
		logDao.addLog("get", map.get("sql").toString(), map);
		
		return (List<MemberVO>) map.get("data");
	}
	
	public MemberVO getMember(Integer id) {
		Map<String, Object> map =  memberDao.getMember(id);
		
		logDao.addLog("get", map.get("sql").toString(), map);
		
		return (MemberVO) map.get("data");
	}
	
	public MemberVO addMember(MemberVO member) {
		Map<String, Object> map = memberDao.addMember(member);
		
		logDao.addLog("post", map.get("sql").toString(), map);
		
		return (MemberVO) map.get("data");
	}
	
	public MemberVO updateMember(MemberVO member) {
		Map<String, Object> map = memberDao.updateMember(member);
		
		logDao.addLog("put", map.get("sql").toString(), map);
		
		return (MemberVO) map.get("data");
	}
	
	public boolean deleteMember(Integer id) {
		Map<String, Object> map = memberDao.deleteMember(id);
		
		logDao.addLog("delete", map.get("sql").toString(), map);
		
		return true;
	}
}