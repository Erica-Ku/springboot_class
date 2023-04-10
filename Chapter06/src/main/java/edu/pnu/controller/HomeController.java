package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.HomeServiceImpl;

@RestController
public class HomeController {
	
	@Autowired
	private HomeServiceImpl homeServiceImpl;
	
	@PostMapping("/board")
	public String postBoard() {
		homeServiceImpl.insertMember();
		homeServiceImpl.postBoard();
		return "성공";
	}
	
	@GetMapping("/board")
	public List<Board> getBoardList() {
		return homeServiceImpl.getBoardList();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		return homeServiceImpl.getBoard(seq);
	}
	
	@PutMapping("/board")
	public String putBoard(Board board) {
		homeServiceImpl.putBoard(board);
		return "성공";
	}
	
	@DeleteMapping("/board/{seq}")
	public String deleteBoard(@PathVariable Long seq) {
		homeServiceImpl.deleteBoard(seq);
		return "성공";
	}
}