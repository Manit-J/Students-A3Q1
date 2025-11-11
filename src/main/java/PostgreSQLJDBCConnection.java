import java.sql.*;

public class PostgreSQLJDBCConnection {

    public static void main(String[] args) {
        getALlStudents();
        //updateStudentEmail(4, "testingUpdate@test.com");
        //deleteStudent(4);
    }

    public static void getALlStudents() {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                // Create statement
                Statement stmt = conn.createStatement(); // Execute SQL query
                String SQL = "SELECT * FROM students";
                ResultSet rs = stmt.executeQuery(SQL); // Process the result
                while (rs.next()) {
                    int id = rs.getInt("student_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String enrollmentDate = rs.getString("enrollment_date");

                    System.out.println("student_id: " + id + " first_name: " + firstName + " last_name: "
                            + lastName + " email: " + email + " enrollment_date: " + enrollmentDate);
                }
                // Close resources
                rs.close();
                stmt.close();
            } else {
                System.out.println("Failed to establish connection.");
            }

            // Close the connection (in a real scenario, do this in a finally
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void addStudent(String first_name, String last_name, String email, String enrollment_date) {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                // INSERT Operation
                String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, first_name);
                    pstmt.setString(2, last_name);
                    pstmt.setString(3, email);
                    pstmt.setDate(4, Date.valueOf(enrollment_date));
                    pstmt.executeUpdate();
                    System.out.println("Data inserted using PreparedStatement.");
                }
            } else {
                System.out.println("Failed to establish connection.");
            }

            // Close the connection (in a real scenario, do this in a finally
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail(int student_id, String new_email) {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                // INSERT Operation
                String insertSQL = "UPDATE students SET email = ? WHERE student_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, new_email);
                    pstmt.setInt(2, student_id);
                    pstmt.executeUpdate();
                    System.out.println("Data updated using PreparedStatement.");
                }
            } else {
                System.out.println("Failed to establish connection.");
            }

            // Close the connection (in a real scenario, do this in a finally
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteStudent(int student_id) {
        // JDBC & Database credentials
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "admin";

        try { // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                // INSERT Operation
                String insertSQL = "DELETE FROM students WHERE student_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setInt(1, student_id);
                    pstmt.executeUpdate();
                    System.out.println("Data updated using PreparedStatement.");
                }
            } else {
                System.out.println("Failed to establish connection.");
            }

            // Close the connection (in a real scenario, do this in a finally
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


}

