package com.rubypaper;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rubypaper.domain.Board;

public class JPAClient_1 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		// 데이터 입력
		insertBoard(emf);
		// seq가 1인 데이터를 출력
		findBoardOne(emf, 1L);
		// 입력된 전체 데이터를 출력(JPA Query)
		findBoardManyJPAQuery(emf);
		// 입력된 전체 데이터를 출력(Native Query)
		findBoardManyNativeQuery(emf);
		// seq가 1인 데이터를 수정
		updateBoard(emf, 1L);
		// 수정된 정보를 확인
		findBoardOne(emf, 1L);
		// seq가 2인 데이터를 삭제
		deleteBoard(emf, 2L);
		// 삭제 결과를 확인
		findBoardManyJPAQuery(emf);
		
		emf.close();
	}
	
	public static void insertBoard(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			for (int i = 0; i<=10; i++) {
				Board board = new Board();
				board.setTitle("JPA 제목"+i);
				board.setWriter("관리자"+i);
				board.setContent("JPA 글 등록 잘 되네요.");
				board.setCreateDate(new Date());
				board.setCnt(0L);
				em.persist(board);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void findBoardOne(EntityManagerFactory emf, Long seq) {
		EntityManager em = emf.createEntityManager();
		try {
			Board board = em.find(Board.class, seq);
			System.out.println(board.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void findBoardManyJPAQuery(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Board> result = em.createQuery("select b from Board b", Board.class);
			List<Board> list = result.getResultList();
			for (Board board : list) {
				System.out.println(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void findBoardManyNativeQuery(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		try {
			// 방법1
			List<?> list = em.createNativeQuery("select * from Board", Board.class).getResultList();
			for (Object b : list) {
				System.out.println(b);
			}
			System.out.println("-".repeat(100));
			// 방법2
			@SuppressWarnings("unchecked")
			List<Object[]> list1 = em.createNativeQuery("select * from Board").getResultList();
			for (Object[] b : list1) {
				for (int i = 0; i < b.length; i++) {
					if (i!=0) System.out.println(",");
					System.out.println(b[i]);
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public static void updateBoard(EntityManagerFactory emf, Long seq) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board board = em.find(Board.class, seq);
			board.setTitle("JPA 제목 수정");
			board.setWriter("관리자 수정");
			board.setContent("JPA 글 내용 수정");
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void deleteBoard(EntityManagerFactory emf, Long seq) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board board = em.find(Board.class, seq);
			em.remove(board);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
}