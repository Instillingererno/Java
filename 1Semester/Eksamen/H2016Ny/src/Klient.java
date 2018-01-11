import java.io.*;

public class Klient {
    public static void main(String[] args) {

    }
    public static Utleiefirma lesFraFil(String filnavn) {
        Utleiefirma output = null;
        try{
            FileInputStream filIn = new FileInputStream(filnavn);
            ObjectInputStream in = new ObjectInputStream(filIn);
            output = (Utleiefirma) in.readObject();
            in.close();
            filIn.close();
        } catch(IOException e) {
            System.out.println("Fant ikke filen");
        } catch(ClassNotFoundException e) {
            System.out.println("Filen ble funnet men inneholdt ikke det som var forventet");
        } catch(Exception e) {
            System.out.println("Noe skjedde, prøv igjen senere");
        }
        return output;
    }
}
