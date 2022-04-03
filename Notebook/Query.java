package Notebook;

import java.awt.List;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;




public class Query {
	public static Connection connection;
	public static Statement statement;
	public static ResultSet rs;
	public static ArrayList<String> notes=new ArrayList<>();
	public static void getData() {
		try {
			String url="jdbc:mysql://localhost:3306/logbook";
			String username="root";
			String password="sambit";
			String query="select * from logbook.rec";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection(url,username,password);
			statement=connection.createStatement();
			rs=statement.executeQuery(query);
			while(rs.next()) {
				
				String row=rs.getInt(1)+")  "+rs.getString(2)+"  at "+"\n"+rs.getDate(3)+" - "+rs.getTime(3);
				Query.notes.add(row);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
	
	public static int write(String text) {
		
		try {
			String url="jdbc:mysql://localhost:3306/logbook";
			String username="root";
			String password="sambit";
			String query="insert into logbook.rec (recnote) values ('" +text+ "')";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection(url,username,password);
			statement=connection.createStatement();
			
			int status=statement.executeUpdate(query);
			return status;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	
public static int delete(int id) {
		
		try {
			String url="jdbc:mysql://localhost:3306/logbook";
			String username="root";
			String password="sambit";
			String query="delete from logbook.rec where recid="+id;
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection =DriverManager.getConnection(url,username,password);
			statement=connection.createStatement();
			
			int status=statement.executeUpdate(query);
			
			return status;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
}
