package edu.pnu.dao;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

//@Repository
public class LogDaoFileImpl implements LogDao {
	FileWriter fileWriter;
	
	public LogDaoFileImpl() {
		
	}
	
	@Override
	public void addLog(String method, String sql, boolean success) {
		try {
			fileWriter = new FileWriter("test.txt");
			fileWriter.write(method);
			fileWriter.write(sql);
			fileWriter.write(String.valueOf(success));
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addLog(String method, String sql, Object result) {
		addLog(method, sql, result==null? false: true);
	}
}