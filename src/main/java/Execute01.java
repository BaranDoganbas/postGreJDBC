import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Adim:
        Class.forName("org.postgresql.Driver");

        //2. Adim: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "barutos05");

        //3. Adim: Statement olustur
        Statement st = con.createStatement();

        //4. Adım: Query çalıştır.

        /*
        execute() methodu DDL(create,drop,alter table) ve DQL(select) icin kullanilabilir
        1) Eger execute() methodu DDL icin kullanilirsa 'false' return eder
        2) Eger execute() methodu DQL icin kullanilirsa ResultSet alindiginda 'true' aksi halde 'false' return eder
         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        boolean sql1 = st.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");
        System.out.println(sql1);//false return eder cunku data cagirmiyoruz.

        //2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(80)";
        boolean sql2b = st.execute(sql2);
        System.out.println("sql2b = " + sql2b);

        //3.Örnek: workers table'ini silin
        String sql3 = "DROP TABLE workers";
        boolean sql3b = st.execute(sql3);
        System.out.println("sql3b = " + sql3b);

        //5. adim: baglanti ve statement'i kapat.
        con.close();
        st.close();

    }
}