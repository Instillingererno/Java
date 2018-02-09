package kode20mySqlutenPassord;
/**
 * Transaksjonstest.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * Programmet utfører en databasetransaksjon som består av to SQL-setninger.
 */

import java.sql.*;
import mittBibliotek.Opprydder;
class Transaksjonstest {
  public static void main(String[] args) throws Exception {
    //String databasedriver = "org.apache.derby.jdbc.ClientDriver";
    String databasedriver = "com.mysql.jdbc.Driver";
    Class.forName(databasedriver);
    String databasenavn
              //= "jdbc:derby://localhost:1527/personer;user=vprg;password=vprg";
                = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/xxxxxxx?user=xxxxxxx&password=xxxxxxx";
    Connection forbindelse =  null;
    Statement setning = null;
    ResultSet res = null;
    try {
      forbindelse =  DriverManager.getConnection(databasenavn);
      setning = forbindelse.createStatement();

      forbindelse.setAutoCommit(false);   // transaksjon starter
      res = setning.executeQuery("select * from konto");
      System.out.println("Innhold i databasen før endring: ");
      while (res.next()) {
        System.out.println(res.getString("kontonr") + ", "
          + res.getString("navn") + ", " + res.getBigDecimal("saldo"));
      }
      res.close();

      /* Overfører 1000 kroner fra konto 123456 til konto 678909 */
      setning.executeUpdate(
        "update konto set saldo = saldo - 1000 where kontonr = '123456'");
      setning.executeUpdate(
        "update konto set saldo = saldo + 1000 where kontonr = '678909'");

      /* Skriver ut alle dataene */
      res = setning.executeQuery("select * from konto");
      System.out.println("\nInnhold i databasen etter endring: ");
      while (res.next()) {
        System.out.println(res.getString("kontonr") + ", "
          + res.getString("navn") + ", " + res.getBigDecimal("saldo"));
      }
      res.close();
      forbindelse.commit();   // alt ok, endringer lagres i databasen

    } catch (SQLException e) {  // feil oppstått, alle endringer angres
      Opprydder.rullTilbake(forbindelse);
      Opprydder.skrivMelding(e, "Ruller tilbake");
    } finally {  // opprydding uansett utfall av transaksjonen
      Opprydder.settAutoCommit(forbindelse);
      Opprydder.lukkResSet(res);
      Opprydder.lukkSetning(setning);
      Opprydder.lukkForbindelse(forbindelse);
    }
  }
}

/* Utskrift fra programmet:

Innhold i databasen før endring:
123456, Ole Ås, 1000
345678, Anne Grethe Ås, 20000
678909, Jonny Hansen, 10000

Innhold i databasen etter endring:
123456, Ole Ås, 1000
345678, Anne Grethe Ås, 20000
678909, Jonny Hansen, 10000

*/