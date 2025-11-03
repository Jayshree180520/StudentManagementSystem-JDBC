package service;

import java.sql.*;
import java.util.Scanner;

import entity.studententity;

public class studentservice {
    private static Connection con;
    private static final String url = "jdbc:postgresql://localhost:5432/demo";
    private static final String user = "postgres";
    private static final String pswd = "123";
    Scanner sc = new Scanner(System.in);

    static {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pswd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a new student
    public int addStudent() {
        int res = 0;
        System.out.print("Enter Students ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Students Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        studententity s = new studententity();
        s.setId(id);
        s.setName(name);
        s.setAge(age);
        s.setGrade(grade);

        String sql = "INSERT INTO students (id, name, age, grade) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, s.getId());
            ps.setString(2, s.getName());
            ps.setInt(3, s.getAge());
            ps.setString(4, s.getGrade());
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Update student info
    public int updateStudent() {
        int res = 0;
        System.out.print("Enter Students ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new Grade: ");
        String grade = sc.nextLine();
        String sql = "UPDATE students SET grade = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, grade);
            ps.setInt(2, id);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // Delete student by ID
    public int deleteStudent() {
        int res = 0;
        System.out.print("Enter Students ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    // View student by ID
    public void viewStudentById() {
        System.out.print("Enter Student ID to fetch: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM students WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Grade: " + rs.getString("grade"));
            } else {
                System.out.println("No students found with ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all students
    public void viewAllStudents() {
        String sql = "SELECT * FROM students";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("-------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Grade: " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
