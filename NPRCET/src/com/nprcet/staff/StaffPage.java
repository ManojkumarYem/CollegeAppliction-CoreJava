package com.nprcet.staff;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.nprcet.admin.AdminPage;
import com.nprcet.login.DbDetails;
import com.nprcet.login.NprCollegeLogin;

public class StaffPage {
	static Scanner scanner = new Scanner(System.in);
	public static void staffLogin() {
		Scanner scanner = new Scanner(System.in);
		String name = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("WELCOME TO THE NPR STAFF PORTAL....\n");
		try {
			System.out.println("Enter the EMPLOYEE ID :");
			double employmentId = scanner.nextDouble();
			System.out.println("Enter the Password");
			String password = scanner.next();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from staff_details where EMP_ID= '" + employmentId + "' and Password ='" + password
					+ "' and Active= '1'";
			ResultSet rs = stmtObj.executeQuery(query);
			if (rs.next()) {
				name = rs.getString(2);
			}
			if (name != null) {
				System.out.println("WELCOME " + name + "\n");
				staffFrontPage(employmentId, password);
			} else {
				System.out.println("\nEMPLOYEE ID and PASSWORD INCORRECT PLEASE TRY AGAIN\n");
				staffLogin();
			}

		} catch (Exception e) {
			e.getMessage();
			System.err.println("LOGIN FAILED");
			staffLogin();
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

	public static void staffFrontPage(double employmentId, String password) {
		System.out.println("WELCOME TO THE NPR TEACHING STAFF PORTAL....\n");
		System.out.println("1 PERSONAL DETAILS\n2 ATTENDANCE\n3 LOGOUT...\n");
		try {
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				staffPersonalDetails(employmentId, password);
				break;
			case "2":
				staffAttendanceFrontPage(employmentId, password);
				break;
			case "3":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				staffFrontPage(employmentId, password);
				break;
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private static void staffPersonalDetails(double employmentId, String password) {
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from staff_details where EMP_ID= '" + employmentId + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println("-------------------------------------");
			if (rs.next()) {
				System.out.println(
						"---------------------------------------------------------------------------------------------------");
				System.out.println("Staff Name              :" + rs.getString(2) + "\n");
				System.out.println("Staff Employment ID     :" + rs.getBigDecimal(1) + "\n");
				System.out.println("Staff Father Name       :" + rs.getString(3) + "\n");
				System.out.println("Staff Mother Name       :" + rs.getString(4) + "\n");
				System.out.println("Sex                     :" + rs.getString(5) + "\n");
				System.out.println("Staff Date-of-Birth     :" + rs.getString(6) + "\n");
				System.out.println("Staff Age               :" + rs.getInt(7) + "\n");
				System.out.println("Staff Date-of-Joining   :" + rs.getString(8) + "\n");
				System.out.println("Staff Native            :" + rs.getString(9) + "\n");
				System.out.println("Staff  Mail_ID          :" + rs.getString(10) + "\n");
				System.out.println("Staff Mobile Number     :" + rs.getString(11) + "\n");
				System.out.println("Education Qualification :" + rs.getString(12) + "\n");
				System.out.println("Staff Department        :" + rs.getString(13) + "\n");
				System.out.println("Staff Role              :" + rs.getString(14) + "\n");
				System.out.println(
						"---------------------------------------------------------------------------------------------------");
			} else {
				System.out.println("Staff Not Found ");
			}
			System.out.println("1 GOTO STAFF PORTAL \n\n2 LOGOUT \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				staffFrontPage(employmentId, password);
				break;
			case "2":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				staffPersonalDetails(employmentId, password);
				break;
			}

		} catch (SQLException e) {
			e.getMessage();
			staffPersonalDetails(employmentId, password);
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public static void staffAttendanceFrontPage(double employmentId, String password) {
		System.out.println("WELCOME TO THE NPR STAFF ATTENDANCE PORTAL....\n");
		System.out
				.println("1 ADD STUDENT ATTENDANCE \n2 SHOW ALL STUDENTS ATTENDANCE \n3 STAFF FRONT PAGE\n4 LOGOUT \n");
		System.out.println("Enter Your Choice...");
		try {
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				addStudentAttendance(employmentId, password);
				break;
			case "2":
				showAllStudentAttendance(employmentId, password);
				break;
			case "3":
				staffFrontPage(employmentId, password);
				break;
			case "4":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				staffAttendanceFrontPage(employmentId, password);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addStudentAttendance(double employmentId, String password) {
		int registerNo = 0;
		Date date = null;
		String department = null;
		String attendance = null;
		String updateQuery = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		PreparedStatement preparedStatementObj = null;
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Enter the Register Number :");
			int registerNumber = scanner.nextInt();
			LocalDate dateObj = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String currentDate = dateObj.format(formatter);
			System.out.println("Enter the Department :");
			String table = scanner.next();
			switch (table) {
			case "cse":
				department = "BE.CSE";
				break;
			case "mech":
				department = "BE.MECH";
				break;
			case "civil":
				department = "BE.CIVIL";
				break;
			}
			connectionObj = DbDetails.getConnection();
			String checkQuery = "select * from Students_details where Reg_No= '" + registerNumber + "' and Department='"
					+ department + "'";
			stmtObj = connectionObj.createStatement();
			ResultSet rs = stmtObj.executeQuery(checkQuery);
			if (rs.next()) {
				String query = "select * from Student_Attendance where Reg_No= " + registerNumber + " and Date= '"
						+ currentDate + "'";
				ResultSet rss = stmtObj.executeQuery(query);
				while (rss.next()) {
					registerNo = rss.getInt(1);
					date = rss.getDate(2);
				}
				if (registerNo != 0 && date != null) {
					System.out.println(" This ID Already Exist so Update the Attendance Status :");
					System.out.println("Press 1 to PRESENT \n Press 2 to ABSENT\n");
					String choice = scanner.next();
					switch (choice) {
					case "1":
						attendance = "Present";
						updateQuery = "update Student_Attendance set Attendance= ' Present ' where Reg_No= "
								+ registerNumber + " and Date='" + currentDate + "'";
						stmtObj.executeUpdate(updateQuery);
						System.out.println("Updated");
						StaffPage.staffAttendanceFrontPage(employmentId, password);
						break;
					case "2":
						attendance = "Absent";
						updateQuery = "update Student_Attendance set Attendance= ' Absent ' where Reg_No= "
								+ registerNumber + " and Date='" + currentDate + "'";
						stmtObj.executeUpdate(updateQuery);
						System.out.println("Updated");
						StaffPage.staffAttendanceFrontPage(employmentId, password);
						break;
					default:
						System.out.println("Wrong Input");
						addStudentAttendance(employmentId, password);
						break;
					}

				} else {
					System.out.println("Enter the Attendance Status :");
					System.out.println("Press 1 to PRESENT \n Press 2 to ABSENT\n");
					String choice = scanner.next();
					switch (choice) {
					case "1":
						attendance = "Present";
						break;
					case "2":
						attendance = "Absent";
						break;
					default:
						System.out.println("Wrong Input");
						AdminPage.addStaffAttendance();
						break;
					}
					String vari = "Insert into Student_Attendance (Reg_No,Date,Attendance,Department) values(?,?,?,?) ";
					preparedStatementObj = connectionObj.prepareStatement(vari);
					preparedStatementObj.setInt(1, registerNumber);
					preparedStatementObj.setString(2, currentDate);
					preparedStatementObj.setString(3, attendance);
					preparedStatementObj.setString(4, department);
					preparedStatementObj.execute();
					System.out.println("Successfully Inserted Present");
					System.out.println("1 Attendance Portal \n 2 STAFF Front Page");
					String choice1 = scanner.next();
					switch (choice1) {
					case "1":
						addStudentAttendance(employmentId, password);
						break;
					case "2":
						StaffPage.staffFrontPage(employmentId, password);
						break;
					default:
						System.out.println("Wrong Input");
						addStudentAttendance(employmentId, password);
						break;
					}
				}
			} else {
				System.out.println("Wrong Input \n Please re-Enter the Attendance\n");
				addStudentAttendance(employmentId, password);
			}
			staffAttendanceFrontPage(employmentId, password);
		} catch (Exception e) {
			e.printStackTrace();
			staffAttendanceFrontPage(employmentId, password);
		} finally {
			try {
				connectionObj.close();
				preparedStatementObj.close();
				stmtObj.close();
				scanner.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void showAllStudentAttendance(double employmentId, String password) {
		Connection connectionObj = null;
		Statement stmtObj = null;
		String query = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Date  Formate yyyy-MM-dd");
		String date = scanner.next();
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			query = "Select * from Student_Attendance where Date='" + date + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			if(rs!=null) {
				while (rs.next()) {
					System.out.print("\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
							+ rs.getString(4) + "\n");
				}
			} else {
				System.out.println("This Date is Not Valid \n Please Re- Enter the Correct Date");
				showAllStudentAttendance(employmentId, password);
			}
			staffAttendanceFrontPage(employmentId, password);
		} catch (Exception e) {
			System.out.println("This Date is Not Valid \n Please Re- Enter the Correct Date");
			showAllStudentAttendance(employmentId, password);
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
				scanner.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
