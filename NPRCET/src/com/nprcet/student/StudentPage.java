package com.nprcet.student;

import java.sql.*;
import java.util.Scanner;

import com.nprcet.login.DbDetails;
import com.nprcet.login.NprCollegeLogin;

public class StudentPage {
	static Scanner scanner = new Scanner(System.in);

	public static void studentLogin() {
		Scanner scanner = new Scanner(System.in);
		String name = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("WELCOME TO THE NPR STUDENT PORTAL....\n");
		try {
			System.out.println("Enter the REGISTER NUMBER :");
			int registerNumber = scanner.nextInt();
			System.out.println("Enter the Password");
			String password = scanner.next();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from Students_Details where Reg_no= " + registerNumber + " and Password ="
					+ password + " and Active= '1'";
			ResultSet rs = stmtObj.executeQuery(query);
			while (rs.next()) {
				name = rs.getString(2);
			}
			if (name != null) {
				System.out.println("WELCOME " + name + "\n");
				studentFrontPage(registerNumber, password);
			} else {
				System.out.println("\nREGISTER NUMBER and PASSWORD INCORRECT PLEASE TRY AGAIN\n");
				studentLogin();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			studentLogin();
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void studentFrontPage(int registerNumber, String password) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO THE NPR STUDENT PORTAL....\n");
		System.out.println("1 PERSONAL DETAILS\n2 COURSE DETAILS \n3 LOGOUT...\n");
		System.out.println("Enter Your Choice...");
		try {
//			do {
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				studentPersonalDetails(registerNumber, password);
				break;
			case "2":
				studentCourseDetails(registerNumber, password);
				break;
			case "3":
				NprCollegeLogin.loginFrontPage();
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				studentFrontPage(registerNumber, password);
				break;
			}
//		}while(choice!=null);
		} catch (Exception e) {
			e.getMessage();
		} finally {
			scanner.close();
		}
	}

	private static void studentPersonalDetails(int registerNumber, String password) {
		Statement stmtObj = null;
		Connection connectionObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from Students_details where Reg_No= '" + registerNumber + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println("-------------------------------------");
			if (rs.next()) {
				System.out.println(
						"---------------------------------------------------------------------------------------------------");
				System.out.println("Student Name              :" + rs.getString(2) + "\n");
				System.out.println("Student Register Number   :" + rs.getBigDecimal(1) + "\n");
				System.out.println("Student Father Name       :" + rs.getString(3) + "\n");
				System.out.println("Student Mother Name       :" + rs.getString(4) + "\n");
				System.out.println("Sex                       :" + rs.getString(5) + "\n");
				System.out.println("Student Date-of-Birth     :" + rs.getString(6) + "\n");
				System.out.println("Student Age               :" + rs.getInt(7) + "\n");
				System.out.println("Student Date-of-Joining   :" + rs.getString(8) + "\n");
				System.out.println("Student Department        :" + rs.getString(9) + "\n");
				System.out.println("Student Native            :" + rs.getString(10) + "\n");
				System.out.println("Student Mail_ID           :" + rs.getString(11) + "\n");
				System.out.println(
						"---------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Student NOT Found");
				studentPersonalDetails(registerNumber, password);
			}
			System.out.println("1 STUDENT FRONT PAGE \n\n2 LOGOUT \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				studentFrontPage(registerNumber, password);
				break;
			case "2":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				studentPersonalDetails(registerNumber, password);
				break;
			}
		} catch (Exception e) {
			e.getMessage();
			studentPersonalDetails(registerNumber, password);
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void studentCourseDetails(int registerNumber, String password) {
		System.out.println("ENGINEERING COURSE DETAILS....\n");
		System.out.println(" MECHANICAL ENGINEERING\n COMPUTER SCIENCE ENGINEERING\n CIVIL ENGINEERING\n)");
		System.out.println("1 STUDENT FRONT PAGE \n\n2 LOGOUT \n");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				studentFrontPage(registerNumber, password);
				break;
			case "2":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				studentCourseDetails(registerNumber, password);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}