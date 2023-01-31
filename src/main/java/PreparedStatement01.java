import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "barutos05");
        Statement st = con.createStatement();

        /*
        PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
        Parametrelendirilmis(Parameterised) SQL query(sorgu)'leri ile calisir. Bir sorguyu 0 veya daha fazla parametre ile kullanabiliriz
         */

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        //1. Adım: Prepared statement query'sini olustur.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        //2. Adım: Prepared statement object'ini olustur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Adım: setInt(), setString(), ... methodlarini kullanarak soru isaretleri (?) yerlerine deger gir.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Adım: Query'yi calistir.
        int updatedRowNumber = pst1.executeUpdate();
        System.out.println("Guncellenmis satir sayisi = " + updatedRowNumber);

        String sql2 = "SELECT * FROM companies";

        ResultSet rs1 = st.executeQuery(sql2);

        while (rs1.next()) {
            System.out.println(rs1.getInt(1) + "--" + rs1.getString(2) + "--" + rs1.getString(3));
        }

        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.

        pst1.setInt(1, 5555);
        pst1.setString(2, "GOOGLE");

        int updatedRowNumber2 = pst1.executeUpdate();
        System.out.println("Guncellenmis satir sayisi 2 = " + updatedRowNumber2);

        ResultSet rs2 = st.executeQuery(sql2);

        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + "--" + rs2.getString(2) + "--" + rs2.getString(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();
    }
}
