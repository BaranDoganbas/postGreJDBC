import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "barutos05");
        Statement st = con.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1. YOL: OFFSET ve FETCH MEXT kullanarak
        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";

        ResultSet resultSet1 = st.executeQuery(sql1);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getString("company") + "--" + resultSet1.getInt("number_of_employees"));
        }

        //2. YOL: Subquery kullanarak

        String sql2 = "SELECT company, number_of_employees\n" +
                "FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees)\n" +
                "                            FROM companies\n" +
                "                            WHERE number_of_employees < (SELECT MAX(number_of_employees)\n" +
                "                                                         FROM companies))";

        ResultSet resultSet2 = st.executeQuery(sql2);

        while (resultSet2.next()) {

            System.out.println(resultSet2.getString(1) + "--" + resultSet2.getInt("number_of_employees"));
        }
        con.close();
        st.close();
        resultSet1.close();
        resultSet2.close();
    }
}