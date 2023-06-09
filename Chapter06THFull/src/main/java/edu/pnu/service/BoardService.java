package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {
	
	List<Board> getBoardList(Board board);
	
	void insertBoard(Board board);
	
	Board getBoard(Board board);
	
	Board updateBoard(Board board);
	
	Board deleteBoard(Board board);
}