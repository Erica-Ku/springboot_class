package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Repository;

//@Repository
public class LogDaoH2Impl implements LogDao {
	private Connection con = null;
	
	public LogDaoH2Impl() {
		try {
            Class.forName("org.h2.Driver");
            
            con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/springboot", "sa", "");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
	}
	
	@Override
	public void addLog(String method, String sql, boolean success) {
		PreparedStatement st = null;
		try {
			st = con.prepareStatement("insert into dblog (method,sql,success) values (?,?,?)");
			st.setString(1, method);
			st.setString(2, sql);
			st.setBoolean(3, success);
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLog(String method, String sql, Object result) {
		addLog(method, sql, result==null? false: true);
	}
}