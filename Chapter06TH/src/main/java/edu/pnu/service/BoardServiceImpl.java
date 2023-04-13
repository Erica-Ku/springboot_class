package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepository boardRepo;
	
	public BoardServiceImpl() {
		
	}

	@Override
	public List<Board> getBoardList(Board board) {
		return (List<Board>) boardRepo.findAll();
	}

	@Override
	public void insertBoard() {
		for (int i = 1; i <= 10; i++) {
			Board board = new Board();
			board.setTitle("제목"+i);
			board.setWriter("작성자"+i);
			board.setContent("내용"+i);
			boardRepo.save(board);
		}
	}

	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}

	@Override
	public Board updateBoard(Board board) {
		Board b = boardRepo.findById(board.getSeq()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		boardRepo.save(b);
		return b;
	}

	@Override
	public void deleteBoard(Long seq) {
		boardRepo.deleteById(seq);
	}
}