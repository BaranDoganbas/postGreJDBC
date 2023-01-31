import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

    private static Connection connection;
    private static Statement statement;

    //1. Adım: Driver'a kaydol
    //2. Adım: Datbase'e bağlan
    public static Connection connectToDataBase(String hostName, String dbName, String userName, String password) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + hostName + ":5432/" + dbName, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (connection != null) {
            System.out.println("Connection Success");
        } else {
            System.out.println("Connection Failed");
        }

        return connection;
    }

    //3. Adım: Statement oluştur.
    public static Statement createStatement() {

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //4. Adım: Query calistir.

    public static Boolean execute(String sql) {
        boolean isExecute;

        try {
            isExecute = statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecute;
    }

    //ExecuteQuery ve ExecuteUpdate methodlari odev...


    //5. adim: baglanti ve statement'i kapat.

    public static void closeConnectionAndStatement() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if (connection.isClosed() && statement.isClosed()) {
                System.out.println("Conncetion and statement closed");
            } else {
                System.out.println("Connection and statement NOT closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Table oluşturan method
    public static void createTable(String tableName, String... columnName_dataType) {
        StringBuilder columnName_dataValue = new StringBuilder("");

        for (String w : columnName_dataType) {

            columnName_dataValue.append(w).append(",");
        }

        columnName_dataValue.deleteCharAt(columnName_dataValue.length()-1);

        try {
            statement.execute("CREATE TABLE " + tableName + "(" + columnName_dataValue + ")");
            System.out.println(tableName + " has been created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
