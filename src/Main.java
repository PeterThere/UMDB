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
            System.out.println("Wyswietl zawodnikow dla wybranego meczu - 4");
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
                            System.out.println("Nie ma żadnego zapisanego turnieju!");
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
                          System.out.println("Turniej nie istnieje albo jest pusty!");
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
                    System.out.println("Podaj ID meczu ktorego gry chcesz wyswietlic: ");
                    int mecz = scaner3.nextInt();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.gra where mecz_id = '" + mecz + "'");
                        if(!rs.next())
                        {
                            System.out.println("Mecz nie istnieje albo nie zawiera żadnych gier!");
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
                }
                case 6 -> {
                }
                case 7 -> {
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


    }

    public static void main(String[] args) {/*
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
        */
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