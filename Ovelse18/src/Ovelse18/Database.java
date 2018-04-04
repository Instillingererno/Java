package Ovelse18;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class Database {
    private final Connection tilkobling;
    private Statement setning;
    private final String database = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/sveinuov";
    private final String brukernavn = "sveinuov";
    private final String passord = "YtDstNqL";

    public Database() throws SQLException {
        tilkobling = DriverManager.getConnection(database, brukernavn, passord);
        startForbindelse();
    }

    public void startForbindelse() {
        try {
            new Driver();
            setning = tilkobling.createStatement();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void lukkForbindelse() {
        try {
            if(tilkobling != null) tilkobling.close();
            else throw new Exception("Tilkobling finnes ikke, burde den?");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean regNyBok(Bok nyBok) {
        /*
            Denne metoden skal registrere en ny tittel og samtidig eksemplar nummer 1 av denne tittelen.

            SQL-setninger:

            insert into boktittel(isbn, forfatter, tittel) values(<isbn>, <forfatter>, <tittel>)
            insert into eksemplar(isbn, eks_nr) values(<isbn>, 1);

            Metoden skal returnere false dersom bok med dette ISBN er registrert fra før. Da skal ingen oppdatering skje.
         */

        if(isbnEksisterer(nyBok.getIsbn())) {
            return false;
        } else {
            try {
                tilkobling.setAutoCommit(false);

                String insert1 = "INSERT INTO boktittel(isbn, forfatter, tittel) VALUES('" + nyBok.getIsbn() + "','" + nyBok.getForfatter() + "','" + nyBok.getTittel() + "');";
                String insert2 = "INSERT INTO eksemplar(isbn, eks_nr) VALUES('" + nyBok.getIsbn() + "', 1);";

                if(setning.executeUpdate(insert1) != 0) {
                    if(setning.executeUpdate(insert2) != 0) {
                        tilkobling.commit();
                        return true;
                    } else {
                        tilkobling.rollback();
                        return false;
                    }
                } else {
                    tilkobling.rollback();
                    return false;
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    tilkobling.setAutoCommit(true);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return false;
    }

    public boolean isbnEksisterer(String isbn) {
        try {
            ResultSet resultSet = setning.executeQuery("SELECT * FROM boktittel WHERE isbn = '" + isbn + "';");
            boolean eksisterer = false;
            while(resultSet.next()) {
                eksisterer = true;
            }
            if(eksisterer) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public int regNyttEksemplar(String isbn) {
        if(isbnEksisterer(isbn)) {
            int nyttNr = 1;
            boolean ok = false;
            ResultSet res = null;
            PreparedStatement selectSetning;
            PreparedStatement insertSetning;

            while(!ok) {
                String selectSQL = "SELECT MAX(eks_nr) AS maks FROM eksemplar WHERE isbn = '" + isbn + "';";
                try {
                    selectSetning = tilkobling.prepareStatement(selectSQL);
                    res = selectSetning.executeQuery();

                    res.next();
                    nyttNr = res.getInt("maks") + 1;
                    String insertSQL = "INSERT INTO eksemplar(isbn, eksnr) VALUES (?,?);";
                    insertSetning = tilkobling.prepareStatement(insertSQL);
                    insertSetning.setString(1, isbn);
                    insertSetning.setInt(2, nyttNr);
                    insertSetning.executeUpdate();
                    ok = true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return nyttNr;
            }
        }
        return 0;
    }

    public boolean lånUtEksemplar(String isbn, String navn, int eksnr) {
        String updSetning = "UPDATE eksemplar SET laant_av = '" + navn + "' WHERE isbn = '" + isbn + "' AND eks_nr = '" + eksnr + "';";
        try {
            if(setning.executeUpdate(updSetning) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] agrs) throws SQLException {
        Database database = new Database();
        database.regNyBok(new Bok("asdas", "asdasd", "asdasd22"));
        database.regNyBok(new Bok("0-07-241163-5", "asdasd", "asdasd22"));
        database.lånUtEksemplar("asdasd", "asdasd", 2);
        database.lånUtEksemplar("0-07-241163-5", "asdasd", 20);
        database.lånUtEksemplar("0-07-241163-5", "Ingunn Sund", 2);
        System.out.println(database.regNyttEksemplar("0-596-00123-1"));
        System.out.println(database.regNyBok(new Bok("0-596-00123-2", "Test shiiet", "Ingunn Sund")));
        database.lukkForbindelse();
    }

}
