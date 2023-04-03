package com.rubypaper;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.domain.Board;

public class JPAClient_1 {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		// 데이터 입력
		//insertBoard(emf);
		// seq가 1인 데이터를 출력
		findBoardOne(emf, 12L);
		
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
}