package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface HomeService {
	
	public void insertMember();
	
	public void postBoard();
	
	public Board getBoard(Long seq);
	
	public List<Board> getBoardList();
	
	public void putBoard(Board board);
	
	public void deleteBoard(Long seq);
}