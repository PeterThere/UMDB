import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String query = "select * from world.city";

        try {
            Statement stmt = mysqlConnect.connect().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String Name = rs.getString("Name");
                String CountryCode = rs.getString("CountryCode");
                String District = rs.getString("District");
                int Population = rs.getInt("Population");
                System.out.println(id + ", " + Name + ", " + CountryCode + ", " + District + ", " + Population);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
