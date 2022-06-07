import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
            System.out.println("Wyswietl graczy dla wybranego meczu - 4"); //kłopot
            System.out.println("Wyswietl wszystkich graczy - 5");
            System.out.println("Wyswietl dostepnych bohaterow w wybranym turnieju - 6");
            System.out.println("Wyswietl mecze wybranego gracza - 7");
            System.out.println("Wyswietl wszystkich bohaterow - 8");
            System.out.println("Wyjscie do menu glownego - 0");
            System.out.println("Wybierz pozycje: ");
            int input = scaner.nextInt();
            scaner.nextLine();

            switch (input) {
                case 1 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.Turniej");

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
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.mecz where turniej_id = (select id from umdatabase.turniej where nazwa_turnieju = '" + turniej + "')");

                        System.out.println("ID | Data | Ilosc gier | Turniej_ID |");
                        while (rs.next()) {
                            System.out.println(rs.getInt("ID") + ", "
                                    + rs.getString("Data") + ", "
                                    + rs.getInt("Ilość_gier") + ", "
                                    + rs.getInt("Turniej_ID"));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    Scanner scaner3 = new Scanner(System.in);
                    System.out.println("Podaj ID meczu ktorego gry chcesz wyswietlic: ");
                    int mecz = scaner3.nextInt();
                    scaner3.nextLine();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.gra where gra.Mecz_ID = " + mecz);
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
                    System.out.println("Podaj ID meczu ktorego graczy chcesz wyswietlic: ");
                    int mecz = scaner4.nextInt();

                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.gracz where gracz.ID IN (SELECT Gracz_ID FROM zawodnicy WHERE GRA_ID IN (SELECT ID FROM gra WHERE MECZ_ID = '" +mecz + "') )");

                        System.out.println("ID | Imie | Data urodzenia | Typ uczestnika");
                        while (rs.next()) {
                            System.out.println(rs.getInt(3) + ", "
                                    + rs.getString(1) + ", "
                                    + rs.getString(2) + ", "
                                    + rs.getString(4));
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

                        System.out.println("ID | Imie | Data urodzenia | Typ uczestnika");
                        while (rs.next()) {
                            System.out.println(rs.getInt(3) + ", "
                                    + rs.getString(1) + ", "
                                    + rs.getString(2) + ", "
                                    + rs.getString(4));
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
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.bohaterowie where Nazwa_bohatera IN (select Bohaterowie_Nazwa_bohatera from umdatabase.turniej_bohaterowie where Turniej_ID IN (select ID from umdatabase.turniej where nazwa_turnieju = '" + turniej + "'))");

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
                    scaner4.nextLine();
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.mecz where ID IN (select Mecz_ID from umdatabase.gra where gra.ID IN (SELECT Gra_ID from zawodnicy where Gracz_ID = '" + gracz + "'))");

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
                case 8 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement();
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.bohaterowie");

                        System.out.println("Nazwa bohatera | Startowe zdrowie");
                        while (rs.next()) {
                            System.out.println(rs.getString(1) + ", "
                                    + rs.getInt(2));
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

    static void insert(){
        MysqlConnect mysqlConnect = new MysqlConnect();
        boolean disp = true;
        Scanner scanner = new Scanner(System.in);
        while(disp) {
            System.out.println("Menu dodawania");
            System.out.println("Dodaj turniej - 1");
            System.out.println("Dodaj mecz i gry- 2");
            System.out.println("Dodaj gracza- 3");
            System.out.println("Dodaj bohatera - 4");
            System.out.println("Wyjscie do menu glownego - 0");
            System.out.println("Wybierz pozycje: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1 -> {
                    System.out.print("Podaj nazwę turnieju:");
                    String nazwaTurnieju = scanner.nextLine();
                    System.out.print("Podaj maksymalna liczbe graczy:");
                    int liczbaGraczy = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Opisz system rozgrywki:");
                    String systemRozgrywki = scanner.nextLine();
                    int idTurnieju =0;
                    try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                        ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.turniej");
                        rs.moveToInsertRow();
                        rs.updateString(2, nazwaTurnieju);
                        rs.updateInt(3, liczbaGraczy);
                        rs.updateString(4, systemRozgrywki);
                        rs.insertRow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.turniej");
                        while (rs.next()) {
                            rs.moveToCurrentRow();
                            idTurnieju = rs.getInt("ID");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Dodano turniej!");
                    String nazwaBohatera;
                    do{
                        System.out.println("Podaj nazwe dostepnego bohatera dla turnieju:");
                        nazwaBohatera = scanner.nextLine();
                        try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                            ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.turniej_bohaterowie");
                            rs.moveToInsertRow();
                            rs.updateInt(1, idTurnieju);
                            rs.updateString(2, nazwaBohatera);
                            rs.insertRow();
                        } catch (SQLException e) {
                            break;
                        }
                    }while(nazwaBohatera != "0");
                }
                case 2 -> {
                    //Dodawanie meczu do turnieju
                    System.out.print("Podaj ID turnieju:");
                    int idTurnieju = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Podaj ilosc gier:");
                    int liczbaGier = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Podaj liczbe graczy bioracych udzial w meczu:");
                    int liczbaGraczy = scanner.nextInt();
                    scanner.nextLine();
                    int idMeczu = 0;
                    try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                        ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.mecz");
                        rs.moveToInsertRow();
                        rs.updateInt(3, liczbaGier);
                        rs.updateInt(4, idTurnieju);
                        rs.insertRow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    try {
                        Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        ResultSet rs = stmt.executeQuery("select * from umdatabase.mecz");
                        while (rs.next()) {
                            rs.moveToCurrentRow();
                            idMeczu = rs.getInt(1);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Dodano mecz!");
                    //Dodawanie gier do meczu
                    for(int i = 1; i <= liczbaGier; i++){
                        System.out.println("Dodawanie " + i + ". gry:");
                        System.out.println("Podaj ilosc rozegranych tur w " + i + ". grze:");
                        int iloscTur = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Podaj ID mapy w " + i + ". grze:");
                        int idMapy = scanner.nextInt();
                        scanner.nextLine();
                        int idGry = 0;
                        try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                            ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.gra");
                            rs.moveToInsertRow();
                            rs.updateInt(2, iloscTur);
                            rs.updateInt(3, idMeczu);
                            rs.updateInt(4, idMapy);
                            rs.insertRow();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        try {
                            Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                            ResultSet rs = stmt.executeQuery("select * from umdatabase.gra");
                            while (rs.next()) {
                                rs.moveToCurrentRow();
                                idGry = rs.getInt("ID");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //Dodawanie graczy do gry
                        for(int j = 1; j <= liczbaGraczy; j++){
                            System.out.println("Podaj ID " + j + ". gracza:");
                            int idGracza = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Podaj bohatera " + j + ". gracza:");
                            String bohaterGracza = scanner.nextLine();
                            System.out.println("Podaj koncowe zdrowie " + j + ". gracza:");
                            int zdrowieGracza = scanner.nextInt();
                            scanner.nextLine();
                            try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                                ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.zawodnicy");
                                rs.moveToInsertRow();
                                rs.updateInt(2, zdrowieGracza);
                                rs.updateInt(3, idGracza);
                                rs.updateString(4, bohaterGracza);
                                rs.updateInt(5, idGry);
                                rs.insertRow();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Podaj imie gracza:");
                    String imieGracza = scanner.nextLine();
                    System.out.println("Podaj datę urodzenia gracza(format YYYY-MM-DD):");
                    String dataUrodzenia = scanner.nextLine();
                    System.out.println("Podaj typ uczestnika (organizator/uczestnik):");
                    String typUczestnika = scanner.nextLine();
                    try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                        ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.gracz");
                        rs.moveToInsertRow();
                        rs.updateString(1, imieGracza);
                        rs.updateString(2, dataUrodzenia);
                        rs.updateString(4, typUczestnika);
                        rs.insertRow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Dodano gracza!");
                }
                case 4 -> {
                    System.out.println("Podaj nazwe bohatera:");
                    String nazwaBohatera = scanner.nextLine();
                    System.out.println("Podaj startowe zdrowie bohatera:");
                    int maksZdrowie = scanner.nextInt();
                    try ( Statement stmt = mysqlConnect.connect().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                        ResultSet rs = stmt.executeQuery("SELECT * FROM umdatabase.bohaterowie");
                        rs.moveToInsertRow();
                        rs.updateString(1, nazwaBohatera);
                        rs.updateInt(2, maksZdrowie);
                        rs.insertRow();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Dodano bohatera!");
                }
                case 0 ->{
                    disp = true;
                    return;
                }

                default -> System.out.println("Niepoprawna opcja!");
            }
        }


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
                        scaner.nextLine();
                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra2 = stmt.executeUpdate("DELETE FROM umdatabase.zawodnicy WHERE Gra_ID="+id_gry);
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.gra WHERE ID="+id_gry);

                        if(ra == 0)
                        {
                            System.out.println("Brak gry o podanym ID");
                            break;
                        }

                        else
                            System.out.println("Gra o nr ID " + id_gry + " została usunieta\n");


                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 2 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        System.out.println("Podaj ID meczu: ");
                        int id_meczu = scaner.nextInt();
                        scaner.nextLine();

                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra3 = stmt.executeUpdate("DELETE FROM umdatabase.zawodnicy WHERE Gra_ID IN (SELECT ID FROM gra WHERE Mecz_ID = '"+id_meczu+"')");
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.gra WHERE Mecz_ID="+id_meczu);
                        int ra2 = stmt.executeUpdate("DELETE FROM umdatabase.mecz WHERE ID ="+id_meczu);

                        if(ra2 == 0)
                        {
                            System.out.println("Brak meczu o podanym ID");
                            break;
                        }
                        else System.out.println("Mecz o nr ID " + id_meczu + " oraz jego gry i zawodnicy zostali usunieci\n");

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    MysqlConnect mysqlConnect = new MysqlConnect();
                    try {
                        System.out.println("Podaj ID turnieju: ");
                        int id_turnieju = scaner.nextInt();
                        scaner.nextLine();
                        Statement stmt = mysqlConnect.connect().createStatement();
                        int ra = stmt.executeUpdate("DELETE FROM umdatabase.zawodnicy WHERE Gra_ID IN (SELECT ID FROM gra WHERE Mecz_ID IN (SELECT ID from mecz WHERE Turniej_ID ='"+id_turnieju+"'))");
                        ra = stmt.executeUpdate("DELETE FROM umdatabase.gra WHERE Mecz_ID IN (SELECT ID from mecz WHERE Turniej_ID ='"+id_turnieju+"')");
                        ra = stmt.executeUpdate("DELETE FROM umdatabase.mecz WHERE Turniej_ID =" + id_turnieju);
                        ra = stmt.executeUpdate("DELETE FROM umdatabase.turniej_bohaterowie WHERE Turniej_ID="+id_turnieju);
                        ra= stmt.executeUpdate("DELETE FROM umdatabase.turniej WHERE ID="+id_turnieju);

                        if(ra == 0)
                        {
                            System.out.println("Brak turnieju o podanym ID");
                            break;
                        }
                        else System.out.println("Turniej o nr ID " + id_turnieju + " został usuniety\n");


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