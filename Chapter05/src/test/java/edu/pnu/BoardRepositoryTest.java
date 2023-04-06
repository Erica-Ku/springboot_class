package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepo;
	
	//@Test
	public void BoardInsertTest() {
		for (int i = 1; i <= 10; i++) {
			Board b = new Board();
			b.setTitle("title"+i);
			b.setWriter("writer"+i);
			b.setContent("content"+i);
			b.setCreateDate(new Date());
			b.setCnt(0L);
			
			boardRepo.save(b);
		}
	}
	
	//@Test
	public void BoardSelectTest() {
		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}
	
	//@Test
	public void BoardUpdateTest() {
		System.out.println("1번째 게시글 조회");
		Board board = boardRepo.findById(1L).get();
		
		System.out.println("1번째 게시글 제목 수정");
		board.setTitle("제목 수정");
		boardRepo.save(board);
	}
	
	//@Test
	public void BoardDeleteTest() {
		boardRepo.deleteById(1L);
	}
	
	//@Test
	public void BoardInsert100Test() {
		for (int i = 1; i <= 100; i++) {
			Board b = new Board();
			Random random = new Random();
			b.setTitle("title"+i);
			b.setWriter("writer"+i);
			b.setContent("content"+i);
			b.setCreateDate(new Date());
			b.setCnt(random.nextLong(101));
			
			boardRepo.save(b);
		}
	}
	
	//@Test
	public void BoardTitleLikeTest() {
		List<Board> list = boardRepo.findByTitleLike("%1%");
		System.out.println("검색결과");
		for (Board board : list) {
			System.out.println(board);
		}
	}
	
	//@Test
	public void BoardTitleContainingAndCntGreaterThanTest() {
		List<Board> list = boardRepo.findByTitleContainingAndCntGreaterThan("1", 50L);
		System.out.println("검색결과");
		for (Board board : list) {
			System.out.println(board);
		}
	}
	
	//@Test
	public void BoardCntBetweenOrderbySeqAscTest() {
		List<Board> list = boardRepo.findByCntBetweenOrderBySeqAsc(10L, 50L);
		System.out.println("검색결과");
		for (Board board : list) {
			System.out.println(board);
		}
	}
	
	//@Test
	public void BoardTitleLikeOrContentLikeOrderbySeqDescTest() {
		List<Board> list = boardRepo.findByTitleLikeOrContentLikeOrderBySeqDesc("%10%", "%2%");
		System.out.println("검색결과");
		for (Board board : list) {
			System.out.println(board);
		}
	}
	
//	@Test
//	public void BoardTest() {
//		List<Board> list = boardRepo.findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(10L, 50L);
//		System.out.println("검색결과");
//		for (Board board : list) {
//			System.out.println(board);
//		}
//	}
	
	@Test
	public void QueryAnnotationTest2() {
		List<Object[]> list = boardRepo.queryAnnotation2("title10");
		System.out.println("검색결과");
		for (Object[] board : list) {
			System.out.println(Arrays.toString(board));
		}
	}
	
	//@Test
	public void QueryAnnotationTest3() {
		List<Object[]> list = boardRepo.queryAnnotation3("title10");
		System.out.println("검색결과");
		for (Object[] board : list) {
			System.out.println(Arrays.toString(board));
		}
	}
	
	//@Test
	public void QueryAnnotationTest4() {
		Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "seq");
		List<Board> list = boardRepo.queryAnnotation4(paging);
		
		System.out.println("검색결과");
		for (Board board : list) {
			System.out.println(board);
		}
	}
}