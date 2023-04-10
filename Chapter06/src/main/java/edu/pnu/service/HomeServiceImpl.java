package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@Service
public class HomeServiceImpl implements edu.pnu.service.HomeService {
	
	@Autowired
	private BoardRepository boardRepo;
	@Autowired
	private MemberRepository memberRepo;
	
	public HomeServiceImpl() {}

	@Override
	public void postBoard() {
		for (int i = 1; i <= 10; i++) {
			Board b = new Board();
			b.setTitle("title"+i);
			b.setContent("content"+i);
			b.setCreateDate(new Date());
			b.setCnt(0L);
			b.setMember(memberRepo.findById("아이디1").get());
			boardRepo.save(b);
		}
	}

	@Override
	public Board getBoard(Long seq) {
		Board board = boardRepo.findById(seq).get();
		return board;
	}

	@Override
	public List<Board> getBoardList() {
		List<Board> list = (List<Board>) boardRepo.findAll();
		return list;
	}

	@Override
	public void putBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		boardRepo.save(b);
	}

	@Override
	public void deleteBoard(Long seq) {
		boardRepo.deleteById(seq);
	}

	@Override
	public void insertMember() {
		for (int i = 1; i <= 10; i++) {
			Member m = new Member();
			m.setId("아이디"+i);
			m.setPassword("123"+i);
			m.setName("이름"+i);
			m.setRole("역할"+i);
			memberRepo.save(m);
		}
	}
}