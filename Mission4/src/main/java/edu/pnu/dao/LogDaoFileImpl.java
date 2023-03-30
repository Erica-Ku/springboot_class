package edu.pnu.dao;

import java.io.FileWriter;
import java.io.IOException;

public class LogDaoFileImpl implements LogDao {
	FileWriter fileWriter;

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

	public LogDaoFileImpl() {
		
	}

	@Override
	public void addLog(String method, String sql, Object result) {
		addLog(method, sql, result==null? false: true);
	}
}