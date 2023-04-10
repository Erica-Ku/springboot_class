package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.DBoard;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("/board")
	public List<DBoard> getBoardList() {
		return boardService.getBoardList();
	}
	
	@GetMapping("/board/{seq}")
	public DBoard getBoard(@PathVariable Long seq) {
		return boardService.getBoard(seq);
	}
}