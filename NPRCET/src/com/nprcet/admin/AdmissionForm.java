package com.nprcet.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.nprcet.login.DbDetails;
import com.nprcet.login.NprCollegeLogin;

public class AdmissionForm {
	static Scanner scanner = new Scanner(System.in);

	public static void AdmissionFormFrontPage() {
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("ADMISSION PORTAL \n");
		System.out.println(
				"1 ADMISSIONFORM FOR STUDENTS  \n\n2 ADMISSIONFORM FOR TEACHING STAFF\n\n3 GOTO ADMIN FRONTPAGE \n\n4 GOTO NPR LOGIN PAGE \n");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				AdmissionFormStudent();
				break;
			case "2":
				staffAdmissionForm();
				break;
			case "3":
				AdminPage.adminFrontPage();
				break;
			case "4":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				AdmissionFormFrontPage();
				break;
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public static void AdmissionFormStudent() {
		int registerNumber = 0;
		String department = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		PreparedStatement preparedStatementObj = null;
		System.out.println("\t\t ADMISSIONFORM FOR  STUDENTS\n");
		try {
			System.out.println("ENTER THE STUDENT NAME:");
			String name = scanner.next();
			System.out.println("ENTER THE STUDENT FATHER's NAME:");
			String fatherName = scanner.next();
			System.out.println("ENTER THE STUDENT MOTHER's NAME:");
			String motherName = scanner.next();
			System.out.println("ENTER THE STUDENT SEX:");
			String sex = scanner.next();
			System.out.println("ENTER THE STUDENT DATE OF BIRTH dd-MM-yyyy FORMAT:");
			String dateOfBirth = scanner.next();
			System.out.println("ENTER THE STUDENT AGE:");
			int age = scanner.nextInt();
			System.out.println("ENTER THE STUDENT DATE OF JOINING:");
			String dateOfJoin = scanner.next();
			System.out.println("ENTER THE STUDENT ADDRESS:");
			String address = scanner.next();
			System.out.println("ENTER THE STUDENT MAIL_ID:");
			String mailId = scanner.next();
			System.out.println("ENTER THE STUDENT Department:");
			System.out.println("1 BE.MECH \n2 BE.CSE \n3 BE.CIVIL");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				department = "BE.MECH";
				break;
			case "2":
				department = "BE.CSE";
				break;
			case "3":
				department = "BE.CIVIL";
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				AdmissionFormStudent();
				break;
			}
			connectionObj = DbDetails.getConnection();
			String var = "select * from Students_details where Department= '" + department
					+ "' order by Reg_no desc limit 1;";
			stmtObj = connectionObj.createStatement();
			ResultSet rs = stmtObj.executeQuery(var);
			if (rs.next()) {
				registerNumber = rs.getInt(1);
			}
			registerNumber = registerNumber + 1; // for increasing the register number
			String query = "insert into Students_Details(Reg_no,Name,Father_name,Mother_name,Sex,DOB,Age,DOJ,Department,Address,Mail_id) values(?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatementObj = connectionObj.prepareStatement(query);
			preparedStatementObj.setInt(1, registerNumber);
			preparedStatementObj.setString(2, name);
			preparedStatementObj.setString(3, fatherName);
			preparedStatementObj.setString(4, motherName);
			preparedStatementObj.setString(5, sex);
			preparedStatementObj.setString(6, dateOfBirth);
			preparedStatementObj.setInt(7, age);
			preparedStatementObj.setString(8, dateOfJoin);
			preparedStatementObj.setString(9, department);
			preparedStatementObj.setString(10, address);
			preparedStatementObj.setString(11, mailId);
			preparedStatementObj.executeUpdate();
			System.out.println("SUCESSFULL");
			AdmissionFormFrontPage();
		} catch (Exception e) {
			e.getMessage();
			AdmissionFormFrontPage();
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
				preparedStatementObj.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void staffAdmissionForm() {
		Connection connectionObj = null;
		PreparedStatement preparedStatementObj = null;
		System.out.println("\t\t ADMISSIONFORM FOR STAFF\n");
		try {
			System.out.println("ENTER THE STAFF NAME:");
			String name = scanner.next();
			System.out.println("ENTER THE STAFF FATHER's NAME:");
			String fatherName = scanner.next();
			System.out.println("ENTER THE STAFF MOTHER's NAME:");
			String motherName = scanner.next();
			System.out.println("ENTER THE STAFF SEX:");
			String sex = scanner.next();
			System.out.println("ENTER THE STAFF DATE OF BIRTH dd-MM-yyyy FORMAT:");
			String dateOfBirth = scanner.next();
			System.out.println("ENTER THE STAFF AGE:");
			int age = scanner.nextInt();
			System.out.println("ENTER THE STAFF DATE OF JOINING:");
			String dateOfJoin = scanner.next();
			System.out.println("ENTER THE STAFF ADDRESS:");
			String address = scanner.next();
			System.out.println("ENTER THE STAFF MAIL_ID:");
			String mailId = scanner.next();
			System.out.println("ENTER THE STAFF Mobile Number:");
			String mobileNumber = scanner.next();
			System.out.println("ENTER THE STAFF Education Qualification:");
			String education = scanner.next();
			System.out.println("ENTER THE STAFF Department:");
			String department = scanner.next();
			System.out.println("ENTER THE STAFF Role:");
			String role = scanner.next();
			connectionObj = DbDetails.getConnection();
			String query = "insert into Staff_Details(Name,Father_Name,Mother_Name,Sex,DOB,Age,DOJ,Address,Mail_id,Mobile_No,Education,Department,Role) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			preparedStatementObj = connectionObj.prepareStatement(query);
			preparedStatementObj.setString(1, name);
			preparedStatementObj.setString(2, fatherName);
			preparedStatementObj.setString(3, motherName);
			preparedStatementObj.setString(4, sex);
			preparedStatementObj.setString(5, dateOfBirth);
			preparedStatementObj.setInt(6, age);
			preparedStatementObj.setString(7, dateOfJoin);
			preparedStatementObj.setString(8, address);
			preparedStatementObj.setString(9, mailId);
			preparedStatementObj.setString(10, mobileNumber);
			preparedStatementObj.setString(11, education);
			preparedStatementObj.setString(12, department);
			preparedStatementObj.setString(13, role);
			preparedStatementObj.execute();
			System.out.println("SUCESSFULL");
			AdmissionFormFrontPage();
		} catch (Exception e) {
			e.getMessage();
			AdmissionFormFrontPage();
		} finally {
			try {
				connectionObj.close();
				preparedStatementObj.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
