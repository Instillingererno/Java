package database;

import etc.Bok;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    private Connection conn;
    private String brukernavn = "root"; //sveinuov
    private String passord = "platon"; //YtDstNqL
    private String url = "jdbc:mysql://localhost:3306/ovelse18?useSSL=false"; //jdbc:mysql://mysql.stud.iie.ntnu.no:3306/sveinuov
    private String driver = "com.mysql.jdbc.Driver";
    private String initScript = "database/init.sql";

    public Database() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,brukernavn,passord);
            System.out.println("Connected");
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    public void init() {
        ArrayList<String> queries = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(initScript)).useDelimiter(";");
        } catch (FileNotFoundException f) {
            f.printStackTrace();
            return;
        }
        while(scanner.hasNext()) {
            String query = scanner.next();
            if(query.length() > 0) {
                queries.add(query);
            }
        }
        scanner.close();
        PreparedStatement statement = null;
        try {
            for(String i : queries) if(i != null) {
                statement = conn.prepareStatement(i);
                statement.executeQuery();
                statement.close();
            }
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }

    public boolean regNyBok(Bok nyBok) throws SQLException {
        String insBok = "INSERT INTO boktittel(isbn, forfatter, tittel) VALUES(?,?,?);";
        String insEks = "INSERT INTO eksemplar(isbn, eks_nr) VALUES(?, 1);";
        String sjekk = "SELECT isbn FROM boktittel WHERE isbn = " + nyBok.getIsbn();

        Statement sjekkStatement = conn.createStatement();
        ResultSet isbnSet = sjekkStatement.executeQuery(sjekk);
        while(isbnSet.next()) {
            if(isbnSet.getString("isbn").equals(nyBok.getIsbn())) return false;
        }
        sjekkStatement.close();

        PreparedStatement insBokStatement = conn.prepareStatement(insBok);
        insBokStatement.setString(1, nyBok.getIsbn());
        insBokStatement.setString(2, nyBok.getForfatter());
        insBokStatement.setString(3, nyBok.getTittel());

        PreparedStatement insEksStatement = conn.prepareStatement(insEks);
        insEksStatement.setString(1, nyBok.getIsbn());

        try {
            conn.setAutoCommit(false);
            if(insBokStatement.executeUpdate() != 0) {
                if(insEksStatement.executeUpdate() != 0) {
                    conn.commit();
                    return true;
                } else conn.rollback();
            } else conn.rollback();
        } catch (SQLException s) {
            s.printStackTrace();
        } finally {
            insEksStatement.close();
            insBokStatement.close();
            conn.setAutoCommit(true);
        }
        return false;
    }

    public int regNyttEksemplar(String isbn) throws SQLException {
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        conn.setAutoCommit(false);
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT isbn, MAX(eks_nr) AS maks FROM eksemplar GROUP BY isbn");
        while(resultSet.next()) {
            if(resultSet.getString("isbn").equals(isbn)) {
                try {
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO eksemplar(isbn, eks_nr) VALUES(?,?)");
                    insert.setString(1, resultSet.getString("isbn"));
                    insert.setInt(2, resultSet.getInt("maks")+1);
                    if(insert.executeUpdate() != 0) {
                        conn.commit();
                    } else conn.rollback();
                    insert.close();
                    return resultSet.getInt("maks")+1;
                } catch (SQLException s) {
                    s.printStackTrace();
                    conn.rollback();
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        }
        return 0;
    }

    public boolean lanUtEksemplar(String isbn, String navn, int eksnr) throws SQLException {
        PreparedStatement update = conn.prepareStatement("UPDATE eksemplar SET laant_av = ? WHERE isbn = ? AND eks_nr = ?");
        update.setString(1, navn);
        update.setString(2, isbn);
        update.setInt(3, eksnr);
        return update.executeUpdate() != 0;
    }

    public void lukkForbindelse() throws SQLException {
        conn.close();
    }
}
