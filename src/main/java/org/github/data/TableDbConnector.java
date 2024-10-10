package org.github.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TableDbConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/java_hr_db";
    private static final String USER = "root";
    private static final String PASSWORD = "57575han";

    // 데이터베이스 연결을 생성하는 메서드
    public Connection createConnection() {
        Connection connection = null;

        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 데이터베이스에 연결
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("MySQL 데이터베이스에 성공적으로 연결되었습니다.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("MySQL 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return connection;
    }

    // 데이터베이스 연결 종료 메서드
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("MySQL 연결이 닫혔습니다.");
            } catch (SQLException e) {
                System.out.println("MySQL 연결을 닫는 중 오류가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
}