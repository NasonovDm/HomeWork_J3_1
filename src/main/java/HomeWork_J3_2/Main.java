package HomeWork_J3_2;


import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;





    public static void main(String[] args) {

    private static void rollbackEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('101101', 'car', 50000);");
        Savepoint sp1 = connection.setSavepoint();
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('102102', 'car', 50000);");
        connection.rollback(sp1);
        stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES ('103103', 'car', 50000);");
        connection.setAutoCommit(true);
    }
    private static void transactionEx() throws SQLException {
        connection.setAutoCommit(false);
        long t = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES (" + i + ", 'car', 100);");
        }
        System.out.println(System.currentTimeMillis() - t);
        connection.setAutoCommit(true);
    }

    // создание таблицы
    private static void createTableEx() throws SQLException {

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS goodsTable (\n" +
                "    id    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    prodid  TEXT UNIQUE,\n" +
                "    title TEXT,\n" +
                "    cost INTEGER\n" +
                ");");
    }

    // заполнение таблицы
        private static void transactionEx() throws SQLException {
            connection.setAutoCommit(false);
            long t = System.currentTimeMillis();
            for (int i = 0; i < 5000; i++) {
                stmt.executeUpdate("INSERT INTO goodsTable (prodid, title, cost) VALUES (" + i + ", 'car', 100);");
            }
            System.out.println(System.currentTimeMillis() - t);
            connection.setAutoCommit(true);
        }


    // чистка таблицы
    private static void clearTableEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM goodsTable;");
    }

    private static void deleteOneEntryEx() throws SQLException {
        stmt.executeUpdate("DELETE FROM goodsTable WHERE id = 5;");
    }

    // изменение таблицы
    private static void updateEx() throws SQLException {
        System.out.println("изменяем");
        Scanner scanner = new Scanner(System.in);
        String cost = scanner.nextLine();
        String id = scanner.nextLine();
        String sql = String.format("UPDATE goodsTable SET cost = '%s' WHERE id = '%s';", cost, id);
        stmt.executeUpdate(sql);
    }

    private static void insertEx() throws SQLException {
        stmt.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob4', 100);");
    }

    // узнаем цену товара
    private static void selectEx() throws SQLException {
        System.out.println("ищем");
        Scanner scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        String sql = String.format("SELECT cost FROM goodsTable where prodid = '%s';", res);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("cost"));
        }
    }

    // удаление таблицы
        private static void dropTableEx() throws SQLException {
            stmt.executeUpdate("DROP TABLE IF EXISTS goodsTable;");
        }


    public static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
