package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	MemberService a;
	
	public MemberController() {
		a = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> members() {
		return a.getMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO member(@PathVariable Integer id) {
		return a.getMember(id);
	}
	
	@PostMapping("/member")
	public MemberVO addmember(MemberVO memberVO) {
		return a.addMember(memberVO);
	}
	
	@PutMapping("/member")
	public MemberVO updatemembers(MemberVO memberVO) {
		return a.updateMembers(memberVO);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO removemember(@PathVariable Integer id) {
		return a.removeMember(id);
	}
}