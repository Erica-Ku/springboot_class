package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.dao.LogDao;
import edu.pnu.domain.MemberVO;

@Service
public class MemberService {
	
	private LogDao logDao;
	
	public MemberService() {
		logDao = new LogDao();
	}
	
	public List<MemberVO> getMembers() {
		return logDao.getMembers();
	}
	
	
}
