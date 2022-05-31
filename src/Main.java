import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    static void display()
    {
        boolean disp = true;
        Scanner scaner = new Scanner(System.in);
        while(disp){
            System.out.println("Menu wyswietlania");
            System.out.println("Wyswietl turnieje - 1");
            System.out.println("Wyswietl mecze w danym turnieju - 2");
            System.out.println("Wyswietl gry w danym meczu - 3");
            System.out.println("Wyswietl zawodnikow dla danego meczu - 4");
            System.out.println("Wyswietl graczy - 5");
            System.out.println("Wyswietl dostepnych bohaterow w danym turnieju - 6");
            System.out.println("Wyswietl gry danego gracza - 7");
            System.out.println("Wyjscie do menu glownego - 0");
            System.out.println("Wybierz pozycje: ");
            int input = scaner.nextInt();

            switch (input) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
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