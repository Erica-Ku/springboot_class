package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.pnu.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {

	List<Board> findByTitleLike(String keyword);
	
	List<Board> findByTitleContainingAndCntGreaterThan(String keyword, Long cnt);
	
	List<Board> findByCntBetweenOrderBySeqAsc(Long cnt1, Long cnt2);
	
	List<Board> findByTitleLikeOrContentLikeOrderBySeqDesc(String keyword1, String keyword2);
	
	//List<Board> findByCntGreaterThanEqualAndCntLessThanEqualOrderBySeqAsc(Long cnt1, Long cnt2);
	
	@Query("SELECT b.seq, b.title, b.writer, b.createDate "
			+ "FROM Board b "
			+ "WHERE b.title like %:keyword% "
			+ "ORDER BY b.seq DESC")
	List<Object[]> queryAnnotation2(@Param("keyword") String keyword);
	
	@Query(value = "select seq, title, writer, create_date "
			+ "from board where title like '%'||?1||'%' "
			+ "order by seq desc", nativeQuery = true)
	List<Object[]> queryAnnotation3(String keyword);
	
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotation4(Pageable paging);
}