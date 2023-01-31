import java.sql.Connection;
import java.sql.Statement;

public class Runner {

    public static void main(String[] args) {
        //1. Adım: Driver'a kaydol
        //2. Adım: Database'e baglan
        Connection connection = JdbcUtils.connectToDataBase("localhost","techproed", "postgres", "barutos05");

        //3. Adım: Statement oluştur.
        Statement statement = JdbcUtils.createStatement();

        //4. Adım: Query calistir
        //JdbcUtils.execute("CREATE TABLE students (name VARCHAR(20), id INT, address VARCHAR(80))");

        JdbcUtils.createTable("DEF", "classes VARCHAR(20)", "teacher_name VARCHAR(20)", "id INT");

        System.out.println("Query executed");

        //5. adim: baglanti ve statement'i kapat.

        JdbcUtils.closeConnectionAndStatement();
    }
}
