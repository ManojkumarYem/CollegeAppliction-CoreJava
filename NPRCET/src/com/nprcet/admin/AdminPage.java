package com.nprcet.admin;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import com.nprcet.login.DbDetails;
import com.nprcet.login.NprCollegeLogin;

public class AdminPage {

	public static void adminLogin() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;		
		Statement stmtObj = null;
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("WELCOME TO THE NPR ADMIN PORTAL....\n");
		try {
			System.out.println("Enter the USERNAME :");
			String userName = scanner.nextLine();
			System.out.println("Enter the Password");
			String password = scanner.nextLine();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from admin_credential where UserName= '" + userName + "' and Password ='"
					+ password + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			if (rs.next()) {
				System.out.println("WELCOME " + rs.getString(2) + "\n");
				adminFrontPage();
			} else {
				System.out.println("\nUSER NAME and PASSWORD INCORRECT PLEASE TRY AGAIN\n");
				adminLogin();
			}

		} catch (Exception e) {
			System.err.println("LOGIN FAILED");
			System.out.println(e.getMessage());
			NprCollegeLogin.loginFrontPage();
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

	public static void adminFrontPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO THE NPR ADMIN PORTAL....\n");
		System.out.println(
				"1 STAFF DETAILS\n2 STUDENT DETAILS\n3 COURSE DETAILS\n4 ADMISSIONFORM\n5 CREDENTIAL DETAILS\n6 ATTENDANCE \n7 REMOVE STUDENT OR STAFF \n8 UPDATE DETAILS \n9 LOGOUT \n");
		System.out.println("Enter Your Choice...");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				staffDetailsFrontPage();
				break;
			case "2":
				studentDetailsFrontPage();
				break;
			case "3":
				CourseDetails();
				break;
			case "4":
				AdmissionForm.AdmissionFormFrontPage();
				break;
			case "5":
				allCredentialDetails();
				break;
			case "6":
				adminAttendanceFrontPage();
				break;
			case "7":
				inActiveUpdate();
				break;
			case "8":
				updateFrontPage();
				break;
			case "9":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				adminFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public static void staffDetailsFrontPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO NPR STAFF DETAILS\n");
		System.out.println(
				"1 ALL TEACHING STAFF DETAILS \n\n2 SEARCH TEACHING STAFF \n\n3 ADMIN FRONTPAGE \n\n4 LOGOUT \n");
		try {
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				showAllStaffDetails();
				break;
			case "2":
				searchStaffDetails();
				break;
			case "3":
				adminFrontPage();
				break;
			case "4":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				staffDetailsFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void showAllStaffDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from staff_details";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getBigDecimal(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t"
						+ rs.getString(8) + "\t" + rs.getString(9) + "\t" + rs.getString(10) + "\t" + rs.getString(11)
						+ "\t" + rs.getString(12) + "\t" + rs.getString(13) + "\t" + rs.getString(14) + "\t"
						+ rs.getInt(16));
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("1 GOTO STAFF DETAILS INFORMATION \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				staffDetailsFrontPage();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				showAllStaffDetails();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			staffDetailsFrontPage();
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

	public static void searchStaffDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			System.out.println("Enter the Employment ID:");
			double employmentId = scanner.nextDouble();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from staff_details where EMP_ID= '" + employmentId + "'";
			ResultSet rs = stmtObj.executeQuery(query);
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
				System.err.println("Staff Not Found \n Try Again");
				searchStaffDetails();
			}
			System.out.println("1 GOTO STAFF DETAILS INFORMATION \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				staffDetailsFrontPage();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				searchStaffDetails();
				break;
			}

		} catch (Exception e) {
			System.err.println("Register number missmatch");
			searchStaffDetails();

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

	public static void studentDetailsFrontPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("STUDENT DETAILS FRONT PAGE\n");
		System.out.println(
				"1 ALL STUDENTS DETAILS \n\n2 SEARCH STUDENT \n\n3 GOTO ADMIN FRONTPAGE \n\n4 GOTO NPR LOGIN PAGE \n");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				System.out.println("\t\t ALL STUDENTS DETAILS PAGE\n");
				allStudentDetails();
				break;
			case "2":
				System.out.println("\t\t SEARCH STUDENT PAGE\n");
				searchStudentDetails();
				break;
			case "3":
				adminFrontPage();
				break;
			case "4":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				studentDetailsFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void allStudentDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "Select * from Students_details";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
						+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7) + "\t"
						+ rs.getString(8) + "\t" + rs.getString(9) + "\t" + rs.getString(10) + "\t" + rs.getString(11)
						+ "\t" + rs.getInt(13));
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("1 GOTO STUDENT DETAILS INFORMATION \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				studentDetailsFrontPage();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\t WRONG INPUT.... TRY AGAIN........\n");
				allStudentDetails();
				break;
			}
		} catch (Exception e) {
			e.getMessage();
			allStudentDetails();
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

	public static void searchStudentDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			System.out.println("Enter the Register Number:");
			int registerNumber = scanner.nextInt();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from Students_details where Reg_No= '" + registerNumber + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println("-------------------------------------");
			if (rs.next()) {
				System.out.println(
						"---------------------------------------------------------------------------------------------------");
				System.out.println("Student Name              :" + rs.getString(2) + "\n");
				System.out.println("Student Register Number   :" + rs.getInt(1) + "\n");
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
				System.out.println("Student Not Found \nTry Again");
				searchStudentDetails();
			}
			System.out.println("1 GOTO STUDENT DETAILS INFORMATION \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				studentDetailsFrontPage();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				searchStudentDetails();
				break;
			}
		} catch (Exception e) {
			System.out.println(" ");
			System.out.println(e.getMessage());
			searchStudentDetails();
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

	public static void CourseDetails() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ENGINEERING COURSE DETAILS....\n");
		System.out.println(
				"1 MECHANICAL ENGINEERING\n2 COMPUTER SCIENCE ENGINEERING\n3 CIVIL ENGINEERING\n4 EXIT(go to admin front page)");
		System.out.println("Enter Your Choice...");
		String choice = scanner.next();
		switch (choice) {
//				case "1":
//					System.out.println("\t\tGO TO MECHANICAL ENGINEERING PAGE\n");
//					break;
//				case "2":
//					System.out.println("\t\tGO TO COMPUTER SCIENCE ENGINEERING PAGE\n");
//					break;
//				case "3":
//					System.out.println("\t\tGO TO CIVIL ENGINEERING PAGE\n");
//					break;
		case "4":
			adminFrontPage();
			break;
		default:
			System.out.println("\t\t WRONG INPUT.... TRY AGAIN........\n");
			CourseDetails();
			break;
		}
		scanner.close();
	}

	public static void allCredentialDetails() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\t\tCREDENTIAL DETAILS\n");
		System.out.println("1 ADD ADMIN CREDENTIAL\n2 ADMIN CREDENTIAL \n3 GOTO ADMIN FRONTPAGE \n ");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				createAdminCredential();
				break;
			case "2":
				showAdminCredential();
				break;
			case "3":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				allCredentialDetails();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createAdminCredential() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			System.out.println("Enter the UserName :");
			String userName = scanner.next();
			System.out.println("Enter the Password :");
			String password = scanner.next();
			System.out.println("Enter the MobileNo :");
			String mobileNo = scanner.next();
			stmtObj = connectionObj.createStatement();
			String query = "INSERT INTO admin_credential (UserName,Password,MobileNo) VALUES('" + userName + "','"
					+ password + "','" + mobileNo + "')";
			stmtObj.executeUpdate(query);
			System.out.println("1 record inserted...");
			System.out.println("1 GOTO CREDENTIAL PORTAL \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				allCredentialDetails();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				allCredentialDetails();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			allCredentialDetails();
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

	public static void showAdminCredential() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String query = "select * from admin_credential";
			ResultSet rs = stmtObj.executeQuery(query);
			System.out.println("No\t UserName\t Password\t MobileNo\t");
			System.out.println("-------------------------------------");
			while (rs.next()) {
				System.out.println(
						rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4));
			}
			System.out.println("-------------------------------------");
			System.out.println("1 GOTO CREDENTIAL PORTAL \n\n2 GOTO ADMIN FRONT PAGE \n");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				allCredentialDetails();
				break;
			case "2":
				adminFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				showAdminCredential();
				break;
			}
		} catch (Exception e) {
			e.getMessage();
			showAdminCredential();
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

	public static void adminAttendanceFrontPage() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("WELCOME TO THE NPR ATTENDANCE PORTAL....\n");
			System.out.println(
					"1 ADD STAFF ATTENDANCE \n2 SHOW ALL ATTENDANCE \n3 SEARCH ATTENDANCE \n4 ADMIN FRONT PAGE \n5 LOGOUT ");
			System.out.println("Enter Your Choice...");
			String choice = scanner.nextLine();
			switch (choice) {
			case "1":
				addStaffAttendance();
				break;
			case "2":
				showAllStaffAttendance();
				break;
			case "3":
				searchAttendance();
				break;
			case "4":
				adminFrontPage();
				break;
			case "5":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				adminAttendanceFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addStaffAttendance() {
		Scanner scanner = new Scanner(System.in);
		String updateQuery = null;
		BigDecimal empId = null;
		Date date = null;
		String attendance = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		PreparedStatement preparedStatementObj = null;
		try {
			System.out.println("Enter the Employee ID :");
			BigDecimal employmentId = scanner.nextBigDecimal();
			LocalDate localDate = LocalDate.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String currentDate = localDate.format(dateTimeFormatter);
			connectionObj = DbDetails.getConnection();
			String staffCheckQuery = "select * from staff_details where EMP_ID= '" + employmentId + "'";
			stmtObj = connectionObj.createStatement();
			ResultSet rs = stmtObj.executeQuery(staffCheckQuery);
			if (rs.next()) {
				String attendanceCheckQuery = "select * from staff_attendance where EMP_ID= " + employmentId
						+ " and Date='" + currentDate + "'";
				ResultSet rss = stmtObj.executeQuery(attendanceCheckQuery);
				while (rss.next()) {
					empId = rss.getBigDecimal(1);
					date = rss.getDate(2);
				}
				if (empId != null && date != null) {
					System.out.println(" This ID Already Exist so Update the Attendance Status :");
					System.out.println("Press 1 to PRESENT \n Press 2 to ABSENT\n");
					String choice = scanner.next();
					switch (choice) {
					case "1":
						updateQuery = "update Staff_Attendance set Attendance= ' Present ' where EMP_ID= "
								+ employmentId + " and Date='" + currentDate + "'";
						stmtObj.executeUpdate(updateQuery);
						System.out.println("Updated");
						AdminPage.adminAttendanceFrontPage();
						break;
					case "2":
						updateQuery = "update Staff_Attendance set Attendance= ' Absent ' where EMP_ID= " + employmentId
								+ " and Date='" + currentDate + "'";
						stmtObj.executeUpdate(updateQuery);
						System.out.println("Updated");
						AdminPage.adminAttendanceFrontPage();
						break;
					default:
						System.out.println("Wrong Input");
						addStaffAttendance();
						break;
					}
				} else {
					System.out.println("Enter the Attendance Status :");
					System.out.println("Press 1 to PRESENT \nPress 2 to ABSENT\n");
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
						addStaffAttendance();
						break;
					}
					String insertQuery = "Insert into Staff_Attendance (Emp_id,Date,Attendance) values(?,?,?) ";
					preparedStatementObj = connectionObj.prepareStatement(insertQuery);
					preparedStatementObj.setBigDecimal(1, employmentId);
					preparedStatementObj.setString(2, currentDate);
					preparedStatementObj.setString(3, attendance);
					preparedStatementObj.execute();
					System.out.println("Successfully Inserted " + attendance + "");
					System.out.println("1  ADD Attendance \n 2 Attendance Front Page");
					String choice1 = scanner.next();
					switch (choice1) {
					case "1":
						addStaffAttendance();
						break;
					case "2":
						AdminPage.adminAttendanceFrontPage();
						break;
					default:
						System.out.println("Wrong Input");
						AdminPage.adminAttendanceFrontPage();
						break;
					}
				}
			} else {
				System.out.println("Wrong Input \n Please re-Enter the Attendance\n");
				addStaffAttendance();
			}
		} catch (Exception e) {
			e.getMessage();
			AdminPage.adminAttendanceFrontPage();
		} finally {
			try {
				connectionObj.close();
				stmtObj.close();
				preparedStatementObj.close();
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void showAllStaffAttendance() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		String query = null;
		System.out.println("Enter the Date  Formate yyyy-MM-dd");
		String date = scanner.next();
		try {
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			query = "Select * from Staff_Attendance where Date='" + date + "'";
			ResultSet rs = stmtObj.executeQuery(query);
			if (rs != null) {
//				System.out.println("\tEMPLOYEEID \tDATE \t\tATTENDANCE \n");
				while (rs.next()) {
					System.out
							.print("\t" + rs.getBigDecimal(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\n");
				}
			} else {
				System.out.println("This Date is Not Valid \n Please Re- Enter the Correct Date");
				showAllStaffAttendance();
			}
			AdminPage.adminAttendanceFrontPage();
		} catch (Exception e) {
			System.out.println("This Date is Not Valid \n Please Re- Enter the Correct Date");
			showAllStaffAttendance();
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

	public static void searchAttendance() {
		String query = null;
		Connection connectionObj = null;
		Statement stmtObj = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Date  Formate yyyy-MM-dd");
		String date = scanner.next();
		System.out.println("1 Staff \n2 Students");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				System.out.println("Enter the Employment Id ");
				double employmentId = scanner.nextDouble();
				query = "Select * from Staff_Attendance where Date='" + date + "' and Emp_id=" + employmentId + "";
				break;
			case "2":
				System.out.println("Enter the Register Number");
				int registerNumber = scanner.nextInt();
				query = "Select * from Student_Attendance where Date='" + date + "' and Reg_no=" + registerNumber + "";
				break;
			default:
				System.out.println("Wrong Input Please Try Again");
				searchAttendance();
				break;
			}
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			ResultSet rs = stmtObj.executeQuery(query);
			if (rs.next()) {
				System.out.println("\tEMPLOYEEID \tDATE \t\tATTENDANCE \n");
				System.out.print("\t" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\n");
			} else {
				System.out.println("This Date is Not Valid \n Please Re- Enter the Correct Date");
				searchAttendance();
			}
			AdminPage.adminAttendanceFrontPage();
		} catch (Exception e) {
			e.getMessage();
			System.out.println("This Date  or ID is Not Valid \n Please Re- Enter the Correct Date and ID");
			searchAttendance();
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

	public static void inActiveUpdate() {
		Connection connectionObj = null;
		Statement stmtObj = null;
		String checkQueryActive = null;
		String checkQueryInActive = null;
		String updateQueryInactive = null;
		String updateQueryactive = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("1 Staff \n2 Students");
		String choice = scanner.next();
		try {
			switch (choice) {
			case "1":
				System.out.println("Enter the Employee ID:");
				double employmentId = scanner.nextDouble();
				checkQueryInActive = "select * from Staff_details where EMP_ID=" + employmentId + " and Active='1'";
				checkQueryActive = "select * from Staff_details where EMP_ID=" + employmentId + " and Active='0'";
				updateQueryInactive = "update Staff_details set Active='0' where Emp_id=" + employmentId + "";
				updateQueryactive = "update Staff_details set Active='1' where Emp_id=" + employmentId + "";
				break;
			case "2":
				System.out.println("Enter the Register Number:");
				int registerNumber = scanner.nextInt();
				checkQueryInActive = "select * from Students_details where Reg_no=" + registerNumber
						+ " and Active='1'";
				checkQueryActive = "select * from Students_details where Reg_no=" + registerNumber + " and Active='0'";
				updateQueryInactive = "update Students_details set Active='0' where Reg_no=" + registerNumber + "";
				updateQueryactive = "update Students_details set Active='1' where Reg_no=" + registerNumber + "";
				break;
			default:
				System.out.println("Wrong input \ntry again");
				inActiveUpdate();
				break;
			}
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			System.out.println("1 In Active \n2  Active");
			int option = scanner.nextInt();
			if (option == 1) {
				ResultSet resultObj = stmtObj.executeQuery(checkQueryInActive);
				if (resultObj.next()) {
					stmtObj.executeUpdate(updateQueryInactive);
					System.out.println("IN ACTIVATED");
				} else {
					System.out.println(" Employee ID not Valid or Already IN ACTIVATED ");
				}
			} else if (option == 2) {
				ResultSet resultObj = stmtObj.executeQuery(checkQueryActive);
				if (resultObj.next()) {
					stmtObj.executeUpdate(updateQueryactive);
					System.out.println("ACTIVATED");
				} else {
					System.out.println("Employee ID not Valid or Already ACTIVATED");
				}
			}
			adminFrontPage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			inActiveUpdate();
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

	public static void updateFrontPage() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("WELCOME TO THE UPDATE PORTAL....\n");
			System.out.println("1 UPDATE STAFF DETAILS \n2 UPDATE STUDENT DETAILS \n3 ADMIN FRONT PAGE \n4 LOGOUT ");
			System.out.println("Enter Your Choice...");
			String choice = scanner.next();
			switch (choice) {
			case "1":
				updateStaffDetails();
				break;
			case "2":
				updateStudentDetails();
				break;
			case "3":
				adminFrontPage();
				break;
			case "4":
				NprCollegeLogin.loginFrontPage();
				break;
			default:
				System.out.println("\t\tWRONG INPUT.... TRY AGAIN........\n");
				updateFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void updateStaffDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		String updateQuery = null;
		try {
			System.out.println("Enter the Employee ID :");
			double employmentId = scanner.nextDouble();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String checkQuery = "select * from Staff_Details where EMP_ID= " + employmentId + "";
			ResultSet rs = stmtObj.executeQuery(checkQuery);
			if (rs.next()) {
				System.out.println(
						" 1 Name \n 2 Father Name \n 3 Mother Name \n 4 Sex \n 5 Date of Birth \n 6 Age \n 7 Date of Joining \n 8 Mail ID \n 9 Address \n 10 Password \n 11 Active Status \n 12 Mobile Number \n 13 Role \n 14 Back \n");
				System.out.println("What do you want to update");
				String choice = scanner.next();
				switch (choice) {
				case "1":
					System.out.println("Enter the Name to update :");
					String name = scanner.next();
					updateQuery = "update Staff_Details set Name='" + name + "' where EMP_ID=" + employmentId + "";
					break;
				case "2":
					System.out.println("Enter the Father Name to update :");
					String fatherName = scanner.next();
					updateQuery = "update Staff_Details set Father_Name='" + fatherName + "' where EMP_ID= "
							+ employmentId + "";
					break;
				case "3":
					System.out.println("Enter the Mother Name to update :");
					String motherName = scanner.next();
					updateQuery = "update Staff_Details set Mother_Name='" + motherName + "' where EMP_ID="
							+ employmentId + "";
					break;
				case "4":
					System.out.println("Enter the Sex to update :");
					String sex = scanner.next();
					updateQuery = "update Staff_Details set Sex='" + sex + "' where EMP_ID=" + employmentId + "";
					break;
				case "5":
					System.out.println("Enter the Date of Birth to update yyyy-mm-dd :");
					String dateOfBirth = scanner.next();
					updateQuery = "update Staff_Details set DOB='" + dateOfBirth + "' where EMP_ID=" + employmentId
							+ "";
					break;
				case "6":
					System.out.println("Enter the Age to update :");
					String age = scanner.next();
					updateQuery = "update Staff_Details set Age=" + age + " where EMP_ID=" + employmentId + "";
					break;
				case "7":
					System.out.println("Enter the Date of Joining to update yyyy-mm-dd:");
					String dateOfJoin = scanner.next();
					updateQuery = "update Staff_Details set DOJ='" + dateOfJoin + "' where EMP_ID=" + employmentId + "";
					break;
				case "8":
					System.out.println("Enter the Mail Id to update :");
					String mailId = scanner.next();
					updateQuery = "update Staff_Details set Mail_id='" + mailId + "' where EMP_ID=" + employmentId + "";
					break;
				case "9":
					System.out.println("Enter the Address to update :");
					String address = scanner.next();
					updateQuery = "update Staff_Details set Address='" + address + "' where EMP_ID=" + employmentId
							+ "";
					break;
				case "10":
					System.out.println("Enter the Password to update :");
					String password = scanner.next();
					updateQuery = "update Staff_Details set Password='" + password + "' where EMP_ID=" + employmentId
							+ "";
					break;
				case "11":
					System.out.println("Enter the Active Status to update :");
					String active = scanner.next();
					updateQuery = "update Staff_Details set Active='" + active + "' where EMP_ID=" + employmentId + "";
					break;
				case "12":
					System.out.println("Enter the Mobile Number to update :");
					String mobileNumber = scanner.next();
					updateQuery = "update Staff_Details set Mobile_No='" + mobileNumber + "' where EMP_ID="
							+ employmentId + "";
					break;
				case "13":
					System.out.println("Enter the Role to update :");
					String role = scanner.next();
					updateQuery = "update Staff_Details set Role='" + role + "' where EMP_ID=" + employmentId + "";
					break;
				case "14":
					updateFrontPage();
					break;
				default:
					System.out.println("Wrong input please try again..");
					updateStaffDetails();
					break;
				}
				stmtObj.executeUpdate(updateQuery);
				System.out.println("Sucessfully Updated");
			} else {
				System.out.println("Employee ID Wrong Try again");
				updateStaffDetails();
			}
			updateFrontPage();
		} catch (Exception e) {
			e.printStackTrace();
			updateStaffDetails();
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

	public static void updateStudentDetails() {
		Scanner scanner = new Scanner(System.in);
		Connection connectionObj = null;
		Statement stmtObj = null;
		String updateQuery = null;
		try {
			System.out.println("Enter the Register Number :");
			int registerNumber = scanner.nextInt();
			connectionObj = DbDetails.getConnection();
			stmtObj = connectionObj.createStatement();
			String checkQuery = "select * from Students_Details where Reg_no= " + registerNumber + "";
			ResultSet rs = stmtObj.executeQuery(checkQuery);
			if (rs.next()) {
				System.out.println(
						" 1 Name \n 2 Father Name \n 3 Mother Name \n 4 Sex \n 5 Date of Birth \n 6 Age \n 7 Date of Joining \n 8 Mail ID \n 9 Address \n 10 Password \n 11 Active Status \n 12 Back \n");
				System.out.println("What do you want to update");
				String choice = scanner.next();
				switch (choice) {
				case "1":
					System.out.println("Enter the Name to update :");
					String name = scanner.next();
					updateQuery = "update Students_Details set Name='" + name + "' where Reg_no=" + registerNumber + "";
					break;
				case "2":
					System.out.println("Enter the Father Name to update :");
					String fatherName = scanner.next();
					updateQuery = "update Students_Details set Father_name='" + fatherName + "' where Reg_no= "
							+ registerNumber + "";
					break;
				case "3":
					System.out.println("Enter the Mother Name to update :");
					String motherName = scanner.next();
					updateQuery = "update Students_Details set Mother_name='" + motherName + "' where Reg_no="
							+ registerNumber + "";
					break;
				case "4":
					System.out.println("Enter the Sex to update :");
					String sex = scanner.next();
					updateQuery = "update Students_Details set Sex='" + sex + "' where Reg_no=" + registerNumber + "";
					break;
				case "5":
					System.out.println("Enter the Date of Birth to update yyyy-mm-dd :");
					String dateOfBirth = scanner.next();
					updateQuery = "update Students_Details set DOB='" + dateOfBirth + "' where Reg_no=" + registerNumber
							+ "";
					break;
				case "6":
					System.out.println("Enter the Age to update :");
					String age = scanner.next();
					updateQuery = "update Students_Details set Age=" + age + " where Reg_no=" + registerNumber + "";
					break;
				case "7":
					System.out.println("Enter the Date of Joining to update yyyy-mm-dd:");
					String dateOfJoin = scanner.next();
					updateQuery = "update Students_Details set DOJ='" + dateOfJoin + "' where Reg_no=" + registerNumber
							+ "";
					break;
				case "8":
					System.out.println("Enter the Mail Id to update :");
					String mailId = scanner.next();
					updateQuery = "update Students_Details set Mail_id='" + mailId + "' where Reg_no=" + registerNumber
							+ "";
					break;
				case "9":
					System.out.println("Enter the Address to update :");
					String address = scanner.next();
					updateQuery = "update Students_Details set Address='" + address + "' where Reg_no=" + registerNumber
							+ "";
					break;
				case "10":
					System.out.println("Enter the Password to update :");
					String password = scanner.next();
					updateQuery = "update Students_Details set Password='" + password + "' where Reg_no="
							+ registerNumber + "";
					break;
				case "11":
					System.out.println("Enter the Active Status to update :");
					String active = scanner.next();
					updateQuery = "update Students_Details set Active='" + active + "' where Reg_no=" + registerNumber
							+ "";
					break;
				case "12":
					updateFrontPage();
					break;
				default:
					System.out.println("Wrong input please try again..");
					updateStudentDetails();
					break;
				}
				stmtObj.executeUpdate(updateQuery);
				System.out.println("Sucessfully Updated");
			} else {
				System.out.println("Register Number Wrong Try again");
				updateStudentDetails();
			}
			updateFrontPage();
		} catch (Exception e) {
			e.printStackTrace();
			updateStudentDetails();
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