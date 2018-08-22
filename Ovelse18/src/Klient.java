import database.Database;
import etc.Bok;

import java.sql.SQLException;

public class Klient {
    public static void main(String[] args) {
        try {
            Database db = new Database();
            //db.init();

            System.out.println(db.regNyBok(new Bok("12345", "Test1", "Test Testesen")));
            System.out.println(db.regNyBok(new Bok("54321", "Test2", "Testesen Test")));

            System.out.println(db.regNyttEksemplar("12345"));
            System.out.println(db.regNyttEksemplar("12345"));
            System.out.println(db.regNyttEksemplar("12345"));
            System.out.println(db.regNyttEksemplar("12345"));
            System.out.println(db.regNyttEksemplar("12345"));

            System.out.println(db.lanUtEksemplar("12345", "Laner", 2));
            System.out.println(db.lanUtEksemplar("54321", "ASDg", 1));

            //FEIL
            System.out.println(db.regNyBok(new Bok("12345", "asdigjn", "asdsdf")));

            db.lukkForbindelse();

        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
}
