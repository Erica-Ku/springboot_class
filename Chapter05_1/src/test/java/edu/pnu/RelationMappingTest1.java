package edu.pnu;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;

@SpringBootTest
public class RelationMappingTest1 {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	//@Test
	public void testManyToOneInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("둘리");
		member1.setRole("User");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("도우너");
		member2.setRole("Admin");
		memberRepo.save(member2);
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle("둘리가 등록한 게시글"+i);
			board.setContent("둘리가 등록한 게시글 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
		
		for (int i = 1; i <= 3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle("도우너가 등록한 게시글"+i);
			board.setContent("도우너가 등록한 게시글 내용"+i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	
	@Test
	public void testManyToOneSelect() {
		Board board = boardRepo.findById(1L).get();  // 조인이 실행되어 연관관계에 있는 회원 정보까지 함께 조회됨
		System.out.println(board.getSeq()+"번 게시글 정보");
		System.out.println("제목 : "+board.getTitle());
		System.out.println("내용 : "+board.getContent());
		if (board.getMember() != null) {
			System.out.println("작성자 : "+board.getMember().getName());
			System.out.println("작성자 권한 : "+board.getMember().getRole());
		}
		else {
			System.out.println("멤버 정보가 없음");
		}
	}
}