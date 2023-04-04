package com.rubypaper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rubypaper.domain.Board1;

public class JPAClient_2 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04MySQL");
		
		// 데이터 입력
		//insertBoard(emf);
		// id가 1인 데이터를 출력
		//findBoardOne(emf, 1L);
		// 입력된 전체 데이터를 출력(JPA Query)
		//findBoardManyJPAQuery(emf);
		// 입력된 전체 데이터를 출력(Native Query)
		//findBoardManyNativeQuery(emf);
		// id가 1인 데이터를 수정
		updateBoard(emf, 1L);
		// 수정된 정보를 확인
		//findBoardOne(emf, 1L);
		// id가 2인 데이터를 삭제
		//deleteBoard(emf, 2L);
		// 삭제 결과를 확인
		//findBoardManyJPAQuery(emf);
		
		emf.close();
	}
	
	public static void insertBoard(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			for (int i = 0; i<=10; i++) {
				Board1 board = Board1.builder()
									.title("title"+i)
									.content("content"+i)
									.writer("writer")
									.build();
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
	
	public static void findBoardOne(EntityManagerFactory emf, Long id) {
		EntityManager em = emf.createEntityManager();
		try {
			Board1 board = em.find(Board1.class, id);
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
			TypedQuery<Board1> result = em.createQuery("select b from BoardJPA b", Board1.class);
			List<Board1> list = result.getResultList();
			for (Board1 board : list) {
				System.out.println(board.toString());
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
			List<?> list = em.createNativeQuery("select * from BoardJPA", Board1.class).getResultList();
			for (Object b : list) {
				System.out.println(b.toString());
			}
			System.out.println("-".repeat(100));
			// 방법2
			@SuppressWarnings("unchecked")
			List<Object[]> list1 = em.createNativeQuery("select * from BoardJPA").getResultList();
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
	
	public static void updateBoard(EntityManagerFactory emf, Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board1 board = em.find(Board1.class, id);
			board.setTitle("수정");
			System.out.println(board.toString());
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}
	
	public static void deleteBoard(EntityManagerFactory emf, Long id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Board1 board = em.find(Board1.class, id);
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