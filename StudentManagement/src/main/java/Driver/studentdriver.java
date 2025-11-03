package Driver;

import java.util.Scanner;

import service.studentservice;

public class studentdriver {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
	        studentservice service = new studentservice();
	        boolean running = true;

	        System.out.println("Welcome to Student Management System");

	        while (running) {
	            System.out.println("\n----- MENU -----");
	            System.out.println("1. Add Student");
	            System.out.println("2. Update Student Grade");
	            System.out.println("3. Delete Student");
	            System.out.println("4. View Student by ID");
	            System.out.println("5. View All Students");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();
  
	    // adding the data 
	            switch (choice) {
	                case 1:
	                    int res1 = service.addStudent();
	                    if (res1 != 0) {
	                        System.out.println("Student added successfully.");
	                    } else {
	                        System.out.println("Failed to add student.");
	                    }
	                    break;
	                    
	    // updating the data                 
	                case 2:
	                    int res2 = service.updateStudent();
	                    if (res2 != 0) {
	                        System.out.println("Student updated successfully.");
	                    } else {
	                        System.out.println("Student ID not found.");
	                    }
	                    break;   
	                    
	     // deleting the data 
	                case 3:
	                    int res3 = service.deleteStudent();
	                    if (res3 != 0) {
	                        System.out.println("Student deleted successfully.");
	                    } else {
	                        System.out.println("Student ID not found.");
	                    }
	                    break;
	                    
	    // viewing the data by id
	                case 4:
	                    service.viewStudentById();
	                    break;
	                    
	            
	     // viewing the all student details
	                case 5:
	                	service.viewAllStudents();
	                	break;
	       
	                case 6:
	                	System.out.println("Existing the system");
	                	running = false;
	                	break;
	                
	               
	                	default:
	                	     System.out.println("Invalid choice ! please enter a number between 1 and 6");
	                   
	                		
	        sc.close();
	    }
	

	        }
	}
}


