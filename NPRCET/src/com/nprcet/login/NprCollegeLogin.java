package com.nprcet.login;

import java.util.Scanner;
import com.nprcet.admin.AdminPage;
import com.nprcet.staff.StaffPage;
import com.nprcet.student.StudentPage;
public class NprCollegeLogin {
	public static void loginFrontPage() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO NPR COLLEGE OF ENGINEERING AND TECHNOLOGY\n");
		System.out.println("\t LOGIN MAIN PAGE \n");
		System.out.println("1 ADMIN \n\n2 TEACHING STAFF\n\n3 STUDENT \n");
		System.out.print("LOGIN :");
		try {
			String choice = scanner.next();
			switch (choice) {
			case "1":
				AdminPage.adminLogin();
				break;
			case "2":
				StaffPage.staffLogin();
				break;
			case "3":
				StudentPage.studentLogin();
				break;
			default:
				System.out.println("\t\t PLEASE ENTER 3 ROLES ONLY LIKE \n\t\t1 \n\t\t2 \n\t\t3 \n");
				loginFrontPage();
				break;
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
			loginFrontPage();
		}
	}
	public static void main(String[] args) {
		loginFrontPage();
	}
}