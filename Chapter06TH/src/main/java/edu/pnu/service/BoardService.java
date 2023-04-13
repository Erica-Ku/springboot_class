package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList(Board board);
	
	void insertBoard();
	
	Board getBoard(Long seq);
	
	Board updateBoard(Board board);
	
	void deleteBoard(Long seq);
}