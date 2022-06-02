import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static void display()
    {
        boolean disp = true;
        Scanner scaner = new Scanner(System.in);
        while(disp) {
            System.out.println("Menu wyswietlania");
            System.out.println("Wyswietl turnieje - 1");
            System.out.println("Wyswietl mecze w wybranym turnieju - 2");
            System.out.println("Wyswietl gry w wybranym meczu - 3");
            System.out.println("Wyswietl zawodnikow dla wybranego meczu - 4"); //kłopot
            System.out.println("Wyswietl graczy - 5");
            System.out.println("Wyswietl dostepnych bohaterow w wybranym turnieju - 6");
            System.out.println("Wyswietl gry wybranego gracza - 7");
            System.out.println("Wyjscie do menu glownego - 0");
            System.out.println("Wybierz pozycje: ");
            int input = scaner.nextInt();

            switch (input) {
                case 1 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.Turniej");

                        if(!rs.next())
                        {
                            System.out.println("Nie ma żadnego zapisanego turnieju!\n");
                            break;
                        }

                        System.out.println("ID | Nazwa Turnieju | Liczba Graczy | System Rozgrywki");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getString("Nazwa_Turnieju") + ", "
                                    + rs.getInt("Liczba_Graczy") + ", "
                                    + rs.getString("System_Rozgrywki"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    Scanner scaner2 = new Scanner(System.in);
                    System.out.println("Podaj nazwe turnieju ktorego mecze chcesz wyswietlic: ");
                    String turniej = scaner2.nextLine();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.mecz where turniej_id = (select turniej_id from umdatabase.turniej where nazwa_turnieju = '" + turniej + "')");

                       if(!rs.next())
                       {
                          System.out.println("Turniej nie istnieje albo jest pusty!\n");
                          break;
                       }

                        System.out.println("ID | Data | Ilosc gier | Turniej_ID | Zawodnicy_ID |");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getString("Data") + ", "
                                    + rs.getInt("Ilość_gier") + ", "
                                    + rs.getInt("Turniej_ID") + ", "
                                    + rs.getInt("Zawodnicy_ID"));
                        }
                    } catch (SQLException e){
                       e.printStackTrace();
                    }
                }
                case 3 -> {
                    Scanner scaner3 = new Scanner(System.in);
                    System.out.println("Podaj nazwe turnieju: ");
                    String turniej = scaner3.nextLine();
                    System.out.println("Podaj ID meczu ktorego gry chcesz wyswietlic: ");
                    int mecz = scaner3.nextInt();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.gra where mecz_id = (select mecz_id from umdatabase.mecz where turniej_id = (select turniej_id from umdatabase.turniej where nazwa_turnieju = '" + turniej + "') AND mecz_id = '" + mecz + "')");
                        if(!rs.next())
                        {
                            System.out.println("Mecz nie istnieje albo nie zawiera żadnych gier!\n");
                            break;
                        }
                        System.out.println("ID | Ilosc rozegranych tur | ID Mapy | ID Meczu");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getInt("Ilość_rozegranych_tur") + ", "
                                    + rs.getInt("Mapy_ID") + ", "
                                    + rs.getInt("Mecz_ID"));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    Scanner scaner4 = new Scanner(System.in);
                    System.out.println("Podaj ID meczu ktorego zawodnikow chcesz wyswietlic: ");
                    int mecz = scaner4.nextInt();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.zawodnicy where ID = (select Zawodnicy_ID from umdatabase.mecz where ID = '" + mecz + "')");

                        if(!rs.next())
                        {
                            System.out.println("Mecz nie istnieje albo nie ma dodanych zawodnikow!");
                            break;
                        }

                        System.out.println("ID | Koncowe Zdrowie Gracza | ID Gracza | Nazwa bohatera");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getInt("Końcowe_Zdrowie_Gracza") + ", "
                                    + rs.getInt("Gracz_ID") + ", "
                                    + rs.getString("Bohaterowie_nazwa_bohatera"));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                case 5 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.gracz");

                        if(!rs.next())
                        {
                            System.out.println("W bazie mie ma żadnych graczy!\n");
                            break;
                        }

                        System.out.println("ID | Imie | Data urodzenia | Typ uczestnika");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getString("Imię") + ", "
                                    + rs.getString("Data_urodzenia") + ", "
                                    + rs.getString("Typ uczestnika"));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                case 6 -> {
                    Scanner scaner6 = new Scanner(System.in);
                    System.out.println("Podaj nazwe turnieju ktorego dostepnych bohaterow chcesz wysiwetlic: ");
                    String turniej = scaner6.nextLine();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.bohaterowie where Nazwa_bohatera = (select Bohaterowie_Nazwa_bohatera from umdatabase.turniej_bohaterowie where Turniej_ID = (select turniej_id from umdatabase.turniej where nazwa_turnieju = '" + turniej + "'))");

                        if(!rs.next())
                        {
                            System.out.println("Turniej nie istnieje albo nie ma przypisanych bohaterów!\n");
                            break;
                        }

                        System.out.println("Nazwa bohatera | Startowe zdrowie");
                        while (rs.next()) {
                            System.out.println(rs.getString("Nazwa_bohatera") + ", "
                                    + rs.getInt("Startowe_zdrowie"));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                }
                case 7 -> {
                    Scanner scaner4 = new Scanner(System.in);
                    System.out.println("Podaj ID gracza którego mecze chcesz wyswietlic: ");
                    int gracz = scaner4.nextInt();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.mecz where Zawodnicy_ID = (select ID from umdatabase.zawodnicy where Gracz_ID = '" + gracz + "')");

                        if(!rs.next())
                        {
                            System.out.println("Gracz nie istnieje albo nie ma przypisanych żadnych meczy!\n");
                            break;
                        }

                        System.out.println("ID | Data | Ilosc gier | ID Turnieju");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getString("Data") + ", "
                                    + rs.getInt("Ilość_gier") + ", "
                                    + rs.getInt("Turniej_ID"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 0 -> {
                    disp = false;
                    return;
                }
                default -> System.out.println("Niepoprawna opcja!");
            }
        }
    }

    static void insert()
    {

    }

    static void delete()
    {
        boolean del = true;
        Scanner scaner = new Scanner(System.in);
        while(del) {
            System.out.println("Menu usuwania");
            System.out.println("Usun gre - 1");
            System.out.println("Usun mecz - 2");
            System.out.println("Usun turniej - 3");
            System.out.println("Wyjscie do menu glownego - 0");
            System.out.println("Wybierz pozycje: ");

            int input = scaner.nextInt();
            switch (input) {
                case 1 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        System.out.println("Podaj ID gry: ");
                        int id_gry = scaner.nextInt();

                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.gra WHERE ID=id_gry");

                        if(ra == 0)
                        {
                            System.out.println("Brak gry o podanym ID");
                            break;
                        }
                        else {

                            System.out.println("Gra o nr ID " + id_gry + " została usunieta\n");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        System.out.println("Podaj ID meczu: ");
                        int id_meczu = scaner.nextInt();

                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.mecz WHERE ID=id_meczu");

                        if(ra == 0)
                        {
                            System.out.println("Brak meczu o podanym ID");
                            break;
                        }
                        else {

                            Statement stmt2 = mysqlConnect.connect().createStatement();
                            ra = stmt2.executeUpdate("DELETE FROM umdatabase.gra WHERE Mecz_ID=id_meczu");

                            System.out.println("Mecz o nr ID " + id_meczu + " został usuniety\n");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        System.out.println("Podaj ID turnieju: ");
                        int id_turnieju = scaner.nextInt();

                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.turniej WHERE ID=id_turnieju");

                        if(ra == 0)
                        {
                            System.out.println("Brak turnieju o podanym ID");
                            break;
                        }
                        else {

                            //Statement stmt2 = mysqlConnect.connect().createStatement();
                            //ra = stmt2.executeUpdate("DELETE FROM umdatabase.gra WHERE Mecz_ID=id_meczu");

                            Statement stmt3 = mysqlConnect.connect().createStatement();
                            ra = stmt3.executeUpdate("DELETE FROM umdatabase.mecz WHERE Turniej_ID=id_turnieju");

                            Statement stmt4 = mysqlConnect.connect().createStatement();
                            ra = stmt4.executeUpdate("DELETE FROM umdatabase.turniej_bohaterowie WHERE Turniej_ID=id_turnieju");

                            Statement stmt5 = mysqlConnect.connect().createStatement();
                            ra = stmt5.executeUpdate("DELETE FROM umdatabase.mapa_turniej WHERE Turniej_ID=id_turnieju");

                            System.out.println("Turniej o nr ID " + id_turnieju + " został usuniety\n");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 0 -> {
                    del = false;
                    return;
                }

                default -> System.out.println("Niepoprawna opcja!");
            }
        }
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        System.out.println("Program do zarzadzania baza danych");
        while (loop) {
            System.out.println("Wyswietlanie - 1");
            System.out.println("Dodawanie - 2");
            System.out.println("Usuwanie - 3");
            System.out.println("Wyjscie z programu - 0");
            System.out.println("Wybierz pozycje: ");
            int input = scan.nextInt();

            switch (input) {
                case 1 -> display();
                case 2 -> insert();
                case 3 -> delete();
                case 0 -> {
                    System.out.println("Do widzenia!");
                    loop = false;
                }
                default ->
                    System.out.println("Niepoprawna opcja!");
            }
        }
    }
}