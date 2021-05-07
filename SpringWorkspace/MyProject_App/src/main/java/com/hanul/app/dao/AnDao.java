package com.hanul.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AnDao {

	DataSource dataSource;
	
	public AnDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/team01");
			/*dataSource = (DataSource) context.lookup("java:/comp/env/CSS");*/
		} catch (NamingException e) {
			e.getMessage();
		}

	}
	
	private void dbClose(Connection connection, PreparedStatement prepareStatement) {
		try {				
			if (prepareStatement != null) {
				prepareStatement.close();
			}
			if (connection != null) {
				connection.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int anJoin(String id, String passwd, String name, String phonenumber, String address) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int state = -100;
		
		try {
			connection = dataSource.getConnection();
			String query = "insert into member(id, passwd, name, phonenumber, address) " + 
			               "values('" + id + "', '" + passwd + "', '" + name + "', '" + 
					        			phonenumber + "', '" + address + "' )";
			prepareStatement = connection.prepareStatement(query);
			state = prepareStatement.executeUpdate();
			
			if (state > 0) {
				System.out.println(state + " »ğÀÔ¼º°ø");				
			} else {
				System.out.println(state + " »ğÀÔ½ÇÆĞ");
			}
			
		} catch (Exception e) {			
			System.out.println(e.getMessage());
		} finally {
			dbClose(connection, prepareStatement);
		}

		return state;
	}

}
