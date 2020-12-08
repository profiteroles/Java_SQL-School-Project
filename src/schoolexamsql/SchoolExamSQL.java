package schoolexamsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SchoolExamSQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/"; // 3306 is default port
        String user = "root";
        String password = ""; // you set password when MySQL is installed

        Connection con = null; // JDBC connection
        Statement stmt = null; // SQL statement object
        String dropIt,create,use,createIntoData,insert,query; // SQL string variables
        ResultSet result = null; // results after SQL execution
        

        try {
            con = DriverManager.getConnection(url, user, password); // connect to MySQL
            stmt = con.createStatement();

            dropIt = "DROP DATABASE IF EXISTS Exams;";
            create = "CREATE DATABASE Exams;";
            use = "USE Exams";
            createIntoData = "CREATE TABLE Exams(Subject VARCHAR(32), Score INTEGER);";
            insert = "INSERT INTO Exams(Subject, Score) VALUES(\"English\", 95),(\"Math\",98),(\"Science\",98);";
            query = "SELECT * FROM Exams;";
            
            // execute the order below
            stmt.execute(dropIt);
            stmt.execute(create);
            stmt.execute(use);
            stmt.execute(createIntoData);
            stmt.execute(insert);
            result = stmt.executeQuery(query); // execute the SQL query
            
            System.out.printf("%-35s%-12s \n",
                    "Subject", "Score");

            while (result.next()) { // loop until the end of the results
                
                String subject = result.getString("Subject");
                int score = result.getInt("Score");

                System.out.printf("%-35s%10d  \n",
                        subject, score);
            }
        } catch (Exception ex) {
            System.out.println("SQLException caught: " + ex.getMessage());
        } finally {
            // Close all database objects nicely
            try {
                if (result != null) {
                    result.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException caught: " + ex.getMessage());
            }
        }
    }    
}
/**
 *
 * @author 30023737
 */