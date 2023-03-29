package edu.pnu.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.pnu.domain.MemberVO;

public class LogDao {
	
	private MemberDao memberDao;
	private static final Logger log = LoggerFactory.getLogger(LogDao.class);
	
	public LogDao() {
		//memberDao = new MemberDaoH2Impl();
		memberDao = new MemberDaoListImpl();
	}
	
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		return memberDao.getMembers();
	}
	
	public MemberVO getMember(Integer id) {
		log.info("getMember()");
		return memberDao.getMember(id);
	}
	
	public MemberVO addMember(MemberVO member) {
		log.info("addMember()");
		return memberDao.addMember(member);
	}
	
	public MemberVO updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberDao.updateMember(member);
	}
	
	public boolean deleteMember(Integer id) {
		log.info("deleteMember()");
		return memberDao.deleteMember(id);
	}
}