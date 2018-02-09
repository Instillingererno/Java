package Ovelse18;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final Connection tilkobling;
    private final String database = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/sveinuov";
    private final String brukernavn = "sveinuov";
    private final String passord = "YtDstNqL";

    public Database() throws SQLException {
        new Driver();
        tilkobling = DriverManager.getConnection(database, brukernavn, passord);
    }

    public void lukkTilkobling() {
        try {
            if(tilkobling != null) tilkobling.close();
            else throw new Exception("Tilkobling finnes ikke, burde den?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] agrs) throws SQLException {
        new Database();
    }

}
