package edu.pnu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDaoH2Impl_jT implements MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MemberDaoH2Impl_jT() {

	}

	@Override
	public Map<String, Object> getMembers() {
		String sql = "select * from member order by id asc";
		List<MemberVO> members = jdbcTemplate.query(sql, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				return m;
			}
		});
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		map.put("data", members);
		return map;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getMember(Integer id) {
		String sql = "select * from member where id=?";
		MemberVO member = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO m = new MemberVO();
				m.setId(rs.getInt("id"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setRegidate(rs.getDate("regidate"));
				return m;
			}
		});
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		map.put("data", member);
		return map;
	}

	private int getNextId() {
		String sql = "select max(id) from member";
		Integer nextId = jdbcTemplate.queryForObject(sql, Integer.class);
		if (nextId == null) {
			return 1;
		} else {
			return nextId + 1;
		}
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
		int id = getNextId();
		String sql = "insert into member (id,name,pass,regidate) values (?,?,?,now())";
		jdbcTemplate.update(sql, id, member.getName(), member.getPass());
		
		MemberVO m = new MemberVO();
		m.setId(id);
		m.setName(member.getName());
		m.setPass(member.getPass());
		m.setRegidate(new java.util.Date());
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		map.put("data", m);
		return map;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
		String sql = "update member set name=?, pass=? where id=?";
		jdbcTemplate.update(sql, member.getName(), member.getPass(), member.getId());
		
		Map<String, Object> map = new HashMap<>();
		map.put("sql", sql);
		map.put("data", member);
		return map;
	}
	
	@Override
	public Map<String, Object> deleteMember(Integer id) {
	    String sql = "delete from member where id=?";
	    jdbcTemplate.update(sql, id);

	    Map<String, Object> response = new HashMap<>();
	    response.put("sql", sql);
	    return response;
	}
}