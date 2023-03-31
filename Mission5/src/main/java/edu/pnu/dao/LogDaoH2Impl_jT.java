package edu.pnu.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDaoH2Impl_jT implements LogDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public LogDaoH2Impl_jT(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addLog(String method, String sql, boolean success) {
		try {
			jdbcTemplate.update("insert into dblog (method, sql, success) values (?, ?, ?)", method, sql, success);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLog(String method, String sql, Object result) {
		addLog(method, sql, result == null ? false : true);
	}
}