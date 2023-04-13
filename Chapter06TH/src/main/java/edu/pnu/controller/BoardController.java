package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
//@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
//	@GetMapping("/board")
//	public List<Board> getBoardList() {
//		return boardService.getBoardList();
//	}
	
	@GetMapping("/getBoardList")
	public void getBoardList(Model model, Board board) {
		List<Board> list = boardService.getBoardList(board);
		model.addAttribute("boardList", list);
	}
	
	@PostMapping("/board")
	public void insertBoard() {
		boardService.insertBoard();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Long seq) {
		Board board = boardService.getBoard(seq);
		return board;
	}
	
	@PutMapping("/board")
	public Board updateBoard(Board board) {
		Board b = boardService.updateBoard(board);
		return b;
	}
	
	@DeleteMapping("/board/{seq}")
	public void deleteBoard(@PathVariable Long seq) {
		boardService.deleteBoard(seq);
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프~~");
		return "hello";
	}
}