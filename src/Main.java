import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            Statement stmt = mysqlConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery("select * from world.city where CountryCode = 'USA' ");

            while (rs.next()) {
                System.out.println(rs.getInt("ID") + ", "
                        + rs.getString("Name") + ", "
                        + rs.getString("CountryCode") + ", "
                        + rs.getString("District") + ", "
                        + rs.getInt("Population"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
