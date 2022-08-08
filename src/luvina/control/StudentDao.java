package luvina.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import luvina.connect.ConnectToSQL;
import luvina.model.Student;

public class StudentDao {
	Student student;
	List<Student> list;
	
	public List<Student> getAllStudent(){
		list = new ArrayList<>();
		Connection conn = ConnectToSQL.connect();
		String sql = "SELECT * FROM student";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setAddress(rs.getString("address"));
				student.setGpa(rs.getDouble("gpa"));
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void insertStudent(Student student) {
		Connection conn = ConnectToSQL.connect();
		String sql = "INSERT INTO student VALUES(?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setInt(1, student.getId());
			pre.setString(2, student.getName());
			pre.setInt(3, student.getAge());
			pre.setString(4, student.getAddress());
			pre.setDouble(5, student.getGpa());
			
			int rs = pre.executeUpdate();
			if(rs == 1) {
				System.out.println("Add student successed.");
			} else {
				System.out.println("Add student failed");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Student findById(int id) {
		Connection conn = ConnectToSQL.connect();
		String sql = "SELECT * FROM student WHERE id = ?";
		student = new Student();
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setInt(1, id);
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setAddress(rs.getString("address"));
				student.setGpa(rs.getDouble("gpa"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return student;
	}
	
	public void edit(Student stu) {
		Connection conn = ConnectToSQL.connect();
		String sql = "UPDATE student SET name = ?, age = ?, address = ?, gpa = ? "
				+ " WHERE id = ?";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setString(1, stu.getName());
			pre.setInt(2, stu.getAge());
			pre.setString(3, stu.getAddress());
			pre.setDouble(4, stu.getGpa());
			pre.setInt(5, stu.getId());
			
			int rs = pre.executeUpdate();
			if(rs == 1) {
				System.out.println("Edit student successed.");
			} else {
				System.out.println("Edit student failed");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public List<Student> sortByGpa() {
		list = new ArrayList<>();
		Connection conn = ConnectToSQL.connect();
		String sql = "SELECT * FROM student ORDER BY gpa DESC";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setAddress(rs.getString("address"));
				student.setGpa(rs.getDouble("gpa"));
					
				list.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public List<Student> sortByName() {
		list = new ArrayList<>();
		Connection conn = ConnectToSQL.connect();
		String sql = "SELECT * FROM student ORDER BY name";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			ResultSet rs = pre.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getInt("id"));
				student.setName(rs.getString("name"));
				student.setAge(rs.getInt("age"));
				student.setAddress(rs.getString("address"));
				student.setGpa(rs.getDouble("gpa"));
					
				list.add(student);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void delete(int id) {
		Connection conn = ConnectToSQL.connect();
		String sql = "DELETE FROM student WHERE id = ?";
		
		try {
			PreparedStatement pre = conn.prepareStatement(sql);
			
			pre.setInt(1, id);
			
			int rs = pre.executeUpdate();
			if(rs == 1) {
				System.out.println("Delete student successed.");
			} else {
				System.out.println("Delete student failed");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
