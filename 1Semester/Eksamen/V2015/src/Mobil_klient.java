import java.io.*;

import static javax.swing.JOptionPane.*;

public class Mobil_klient {
    static String[] muligheter = {"Opprett ny mobil", "Registrer nytt visittkort", "Skriv ut info", "Avslutt"};
    static final int OPPRETT_MOBIL = 0;
    static final int REG_VISITTKORT = 1;
    static final int SKRIV_INFO = 2;
    static final int AVSLUTT = 3;
    static int valg = 0;

    static Mobil mobil;

    public static void main(String[] args) {
        while(valg != AVSLUTT) {
            lesValg();
            switch(valg) {
                case OPPRETT_MOBIL:
                    if(mobil == null) { // Mobil eksisterer ikke fra før av
                        regNyMobil();
                    } else { // Mobil eksisterer fra før av
                        int fortsett = showConfirmDialog(null, "Mobil er registrert fra før, vil du fortsette?");
                        switch (fortsett) {
                            case OK_OPTION:
                                regNyMobil();
                                break;
                            default: break;
                        }
                    }
                    break;
                case REG_VISITTKORT:
                    if(mobil != null) {
                        String fornavn = showInputDialog("Skriv fornavn");
                        String etternavn = showInputDialog("Skriv etternavn");
                        int tlfmobil = -1;
                        int tlfjobb = -1;
                        while(tlfmobil == -1) {
                            try {
                                tlfmobil = Integer.parseInt(showInputDialog("Skriv mobilnr"));
                            } catch (Exception e) {
                                showMessageDialog(null, "Noe gikk galt, prøvte du å skrive bokstaver i stedet for tall?");
                                tlfmobil = -1;
                            }
                        }
                        while(tlfjobb == -1) {
                            try {
                                tlfjobb = Integer.parseInt(showInputDialog("Skriv jobbnr"));
                            } catch (Exception e) {
                                showMessageDialog(null, "Noe gikk galt, prøvte du å skrive bokstaver i stedet for tall?");
                                tlfjobb = -1;
                            }
                        }
                        String epost = showInputDialog("Skriv epost");
                        if(mobil.regVisittkort(new Visittkort(new Navn(fornavn, etternavn), tlfmobil, tlfjobb, epost))) {
                            showMessageDialog(null, "Visittkort ble registrert");
                        } else {
                            showMessageDialog(null, "Noe gikk galt, prøv igjen");
                        }
                    }
                    break;
                case SKRIV_INFO:
                    if(mobil != null) {
                        showMessageDialog(null,mobil.toString());
                    }
                    break;
                default: break;
            }
        }
    }

    public static void lesValg() {
        valg = showOptionDialog(null,
                "Gjør ett valg",
                "Eksamen 2015",
                YES_NO_OPTION,
                INFORMATION_MESSAGE,
                null,
                muligheter,
                muligheter[0]
        );
    }

    public static void regNyMobil() {
        String modell = showInputDialog("Skriv inn modellen");
        int antVisittkort = -1;
        int egetTlfnr = -1;
        while(antVisittkort == -1) {
            try {
                antVisittkort = Integer.parseInt(showInputDialog("Skriv inn antall visittkort mobilen skal kunne støtte"));
            } catch(Exception e) {
                showMessageDialog(null, "Noe gikk galt, kan du ha skrevet inn bokstaver i stedet for tall?");
                antVisittkort = -1;
            }
        }
        while(egetTlfnr == -1) {
            try {
                egetTlfnr = Integer.parseInt(showInputDialog("Skriv inn mobil nummer"));
            } catch(Exception e) {
                showMessageDialog(null, "Noe gikk galt, kan du ha skrevet inn bokstaver i stedet for tall?");
                egetTlfnr = -1;
            }
        }
        try {
            mobil = new Mobil(modell, antVisittkort, egetTlfnr);
            showMessageDialog(null, "Mobilen ble registrert");
        } catch(Exception e) {
            showMessageDialog(null, "Noe gikk galt, prøv igjen");
            e.printStackTrace();
        }
    }

    public static Mobil lesFraFil(String filnavn) throws IOException {
        try {
            FileInputStream fileIn = new FileInputStream(filnavn);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Mobil mobil = (Mobil) in.readObject();
            in.close();
            fileIn.close();
            return mobil;
        } catch(FileNotFoundException e) {
            System.out.println("Fil ikke funnet");
        } catch(ClassNotFoundException e) {
            System.out.println("Klassen i filen stemmer ikke overens med Mobil");
        }
        return null;
    }
    public static boolean skrivTilFil(Mobil m, String filnavn) {
        boolean ok = false;
        if(m != null) {
            try{
                FileOutputStream filUt = new FileOutputStream(filnavn);
                ObjectOutputStream ut = new ObjectOutputStream(filUt);
                ut.writeObject(m);
                ut.close();
                filUt.close();
                ok = true;
            } catch(IOException e) {
                System.out.println("Kunne ikke lage fil");
            }
        }
        return ok;
    }
}
