package edu.pnu.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDao {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public MemberDao() {
		try {
			// JDBC 드라이버 로드
			Class.forName("org.h2.Driver");
			
			// DB에 연결
			String url = "jdbc:h2:tcp://localhost/~/springboot";
			String id = "sa";
			String pwd = "";
			con = DriverManager.getConnection(url, id, pwd);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> getMembers() {
		List<MemberVO> memberVO = new ArrayList<>();
		String query = "SELECT * FROM member";
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MemberVO mb = new MemberVO();
				mb.setId(rs.getInt(1));
				mb.setPass(rs.getString(2));
				mb.setName(rs.getString(3));
				mb.setRegidate(rs.getDate(4));
				
				memberVO.add(mb);
			}
		} catch (Exception e) {
			System.out.println("member 조회 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}

	public MemberVO getMember(Integer id) {
		MemberVO memberVO = new MemberVO();
		String query = "SELECT * FROM member WHERE id=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				memberVO.setId(rs.getInt(1));
				memberVO.setPass(rs.getString(2));
				memberVO.setName(rs.getString(3));
				memberVO.setRegidate(rs.getDate(4));
			}
		} catch (Exception e) {
			System.out.println("특정 member 조회 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}

	public MemberVO addMember(MemberVO memberVO) {
		try {
			String query = "INSERT INTO member ( "
						+ " id, pass, name) "
						+ " VALUES ( "
						+ " ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, memberVO.getId());
			psmt.setString(2, memberVO.getPass());
			psmt.setString(3, memberVO.getName());
			int rs = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("member 입력 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}

	public MemberVO updateMembers(MemberVO memberVO) {
		try {
			String query = "UPDATE member SET "
						+ " pass=?, name=? "
						+ " WHERE id=?";

			psmt = con.prepareStatement(query);
			psmt.setString(1, memberVO.getPass());
			psmt.setString(2, memberVO.getName());
			psmt.setInt(3, memberVO.getId());
			int rs = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("member 수정 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}

	public MemberVO removeMember(Integer id) {
		MemberVO memberVO = new MemberVO();
		try {
			String query = "DELETE FROM member WHERE id=?";
			
			psmt = con.prepareStatement(query);
			psmt.setInt(1, memberVO.getId());
			
			int rs = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("member 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return memberVO;
	}
}
