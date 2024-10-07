package Beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Person {
    private String userID;
    private String fullName;
    private String semester;
    private String rollNo;
    private String email;
    private String contactNo;
    
    // Getter and Setter methods
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    // Method to authenticate the user
    public boolean authenticate(String userID, String password) {
        boolean isValid = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam_system", "root", "Kuldip19");

            String query = "SELECT * FROM students WHERE userID = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.userID = rs.getString("userID");
                this.fullName = rs.getString("fullName");
                this.semester = rs.getString("semester");
                this.rollNo = rs.getString("rollNo");
                this.email = rs.getString("email");
                this.contactNo = rs.getString("contactNo");
                isValid = true;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }
}
