package luvina.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import luvina.control.StudentDao;
import luvina.model.Student;

public class Main {
	static Student stu;
	static StudentDao studentDao = new StudentDao();
	static List<Student> list = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		int choose;

		do {
			menu();
			choose = Integer.parseInt(scan.nextLine());
			
			switch (choose) {
			case 0:
				System.out.println("Exit success!");
				break;
			case 1: 
				addStudent();
				break;
			case 2:
				editStudent();
				break;
			case 3:
				deleteStudent();
				break;
			case 4:
				sortByGPA();
				break;
			case 5:
				sortByName();
				break;
			case 6:
				showStudent();
				break;
			default:
				System.out.println("Must choose [0-6]");
				break;
			}
		} while (choose != 0);
		
	}
	
	static void menu() {
		System.out.println("1. Add student.");
		System.out.println("2. Edit student by id.");
		System.out.println("3. Delete student by id.");
		System.out.println("4. Sort student by gpa.");
		System.out.println("5. Sort student by name.");
		System.out.println("6. Show student.");
		System.out.println("0. Exit.");
		System.out.println("Your choose:");
	}
	
	static void addStudent() {
		stu = new Student();
		list = new ArrayList<>();
		list = studentDao.getAllStudent();
		
		System.out.println("--------ADD STUDENT---------");
		System.out.println("Enter student id:");
		stu.setId(Integer.parseInt(scan.nextLine()));
		boolean exists = false;
		for (Student student : list) {
			if(stu.getId() == student.getId()) {
				exists = true;
				break;
			}
		}
		if(exists) {
			System.out.println("Student id does exists!!!");
		} else {
			System.out.println("Enter student name: ");
			stu.setName(scan.nextLine());
			System.out.println("Enter student age:");
			stu.setAge(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter student address: ");
			stu.setAddress(scan.nextLine());
			System.out.println("Enter student gpa:");
			stu.setGpa(Double.parseDouble(scan.nextLine()));
			
			studentDao.insertStudent(stu);
		}
		
	}
	
	static void editStudent() {
		Student editStu = new Student();
		int id;
		list = new ArrayList<>();
		list = studentDao.getAllStudent(); 
		
		System.out.println("--------EDIT STUDENT---------");
		System.out.println("Enter student id, you want to edit: ");
		id = Integer.parseInt(scan.nextLine());
		
		boolean exists = false;
		for (Student student : list) {
			if(id == student.getId()) {
				exists = true;
				break;
			}
		}
		if(exists) {
			editStu.setId(id);
			System.out.println("Enter new name: ");
			editStu.setName(scan.nextLine());
			System.out.println("Enter new age: ");
			editStu.setAge(Integer.parseInt(scan.nextLine()));
			System.out.println("Enter new address: ");
			editStu.setAddress(scan.nextLine());
			System.out.println("Enter new gpa:");
			editStu.setGpa(Double.parseDouble(scan.nextLine()));
			
			studentDao.edit(editStu);
		} else {
			System.out.println("Student does not exists");
		}
		
	}
	
	
	static void deleteStudent() {
		list = studentDao.getAllStudent();
		int id;
		System.out.println("--------DELETE STUDENT---------");
		System.out.println("Enter student id, you want to delete: ");
		id = Integer.parseInt(scan.nextLine());
		boolean exists = false;
		for (Student student : list) {
			if(id == student.getId()) {
				exists = true;
				break;
			}
		}
		if(exists) {
			studentDao.delete(id);
		} else {
			System.out.println("Student does not exists");
		}
	}
	
	static void showStudent() {
		System.out.println("--------SHOW STUDENT---------");
		int id;
		System.out.println("Enter student id to show:");
		id = Integer.parseInt(scan.nextLine());
		Student stu = studentDao.findById(id);
		if(stu.getId() != 0) {
			System.out.println("ID: " + stu.getId() +"; NAME: " + stu.getName() + "; AGE: "
					+ stu.getAge() + "; ADDRESS: " + stu.getAddress() + "; GPA: " + stu.getGpa());
		} else {
			System.out.println("Student does not exists!");
		}
		
	}
	
	static void sortByGPA() {
		System.out.println("--------SORT STUDENT BY GPA---------");
		list = studentDao.sortByGpa();
		for (Student student : list) {
			System.out.println("ID: " + student.getId() +"; NAME: " + student.getName() + "; AGE: "
					+ student.getAge() + "; ADDRESS: " + student.getAddress() + "; GPA: " + student.getGpa());
		}
	}
	
	static void sortByName() {
		System.out.println("--------SORT STUDENT BY NAME---------");
		list = studentDao.sortByName();
		for (Student student : list) {
			System.out.println("ID: " + student.getId() +"; NAME: " + student.getName() + "; AGE: "
					+ student.getAge() + "; ADDRESS: " + student.getAddress() + "; GPA: " + student.getGpa());
		}
	}
	
}


