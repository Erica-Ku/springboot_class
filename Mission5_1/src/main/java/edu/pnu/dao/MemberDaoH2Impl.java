package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl implements MemberDao {
	
	@Autowired
	private DataSource dataSource;
	
//	private Connection con = null;

	public MemberDaoH2Impl() {
//		try {
//            Class.forName("org.h2.Driver");
//            
//            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
//        }
//        catch (Exception e) {            
//            e.printStackTrace();
//        }
	}
	
	public Connection getConnection() {
		Connection tcon = null;
		try {
			tcon = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tcon;
	}
	
	@Override
	public Map<String, Object> getMembers() {
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from member order by id asc";
		try {
			List<MemberVO> list = new ArrayList<>();
			st = getConnection().createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				list.add(m);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from member where id=?";
		try {
			st = getConnection().prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			rs.next();
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setPass(rs.getString("pass"));
			m.setName(rs.getString("name"));
			m.setRegidate(rs.getDate("regidate"));
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", m);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private int getNextId() {
		Statement st = null;
		ResultSet rs = null;
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery("select max(id) from member");
			rs.next();
			return rs.getInt(1) + 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;		
	}
	
	@Override
	public Map<String, Object> addMember(MemberVO member) {
		int id = getNextId();
		PreparedStatement st = null;
		String sql = "insert into member (id,name,pass,regidate) values (?,?,?,?)";
		try {
			st = getConnection().prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, member.getName());
			st.setString(3, member.getPass());
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.executeUpdate();
			
			MemberVO m = new MemberVO();
			m.setId(id);
			m.setName(member.getName());
			m.setPass(member.getPass());
			m.setRegidate(new Date(System.currentTimeMillis()));
			
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", m);
			return map;
			//return getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		PreparedStatement st = null;
		String sql = "update member set name=?,pass=? where id=?";
		try {
			st = getConnection().prepareStatement(sql);
			st.setString(1, member.getName());
			st.setString(2, member.getPass());
			st.setInt(3, member.getId());
			st.executeUpdate();

			MemberVO m = new MemberVO();
			m.setId(member.getId());
			m.setPass(member.getPass());
			m.setName(member.getName());
			
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			map.put("data", m);
			return map;
			//return getMember(member.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
		PreparedStatement st = null;
		String sql = "delete from member where id=?";
		try {
			st = getConnection().prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
			
			Map<String, Object> map = new HashMap<>();
			map.put("sql", sql);
			
			return map;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}