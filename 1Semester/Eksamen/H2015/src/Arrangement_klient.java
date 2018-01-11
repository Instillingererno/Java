import java.io.*;

import static javax.swing.JOptionPane.*;

public class Arrangement_klient {

    static String[] muligheter = {"Opprett nytt arrangement", "Registrer ny idrettsgren", "Avslutt"};

    static Arrangement arr = null;

    public static void main(String[] args) {
        final int OPPRETT_ARRANGEMENT = 0;
        final int REG_IDRETTSGREN = 1;
        final int AVSLUTT = 2;


        int valg = 0;

        while((valg = getValg()) != AVSLUTT) {
            switch(valg) {
                case OPPRETT_ARRANGEMENT:
                    if(arr == null) {
                        try{
                            arr = new Arrangement(showInputDialog("Skriv inn navn på arrangementet"), Integer.parseInt(showInputDialog("Skriv inn hvor mange iddrettsgrener som skal støttes")));
                        } catch(Exception e) {
                            showMessageDialog(null, "Noe gikk galt, ingenting er endret");
                        }
                    } else {
                        showMessageDialog(null, "Arrangement er allerede registrert, hvis du fortsetter blir all tidligere data slettet");
                        try {
                            arr = new Arrangement(showInputDialog("Skriv inn navn på arrangementet"), Integer.parseInt(showInputDialog("Skriv inn hvor mange iddrettsgrener som skal støttes")));
                        } catch(Exception e) {
                            showMessageDialog(null, "Noe gikk galt, ingenting er endret");
                        }
                    }
                    break;
                case REG_IDRETTSGREN:
                    Idrettsgren idrett;
                    boolean fortsett = true;
                    if(arr != null) {
                        try{
                            idrett = new Idrettsgren(showInputDialog("Skriv navn på idrett"), Integer.parseInt(showInputDialog("Skriv antall øvelser")));
                            if(arr.regIdrett(idrett)) {
                                showMessageDialog(null, "Idrett registert");
                            } else {
                                showMessageDialog(null, "Noe gikk galt, det kan være maks antall idretter er nådd, eller kanskje idretten fins fra før?");
                            }
                            for(int i = 0; i < idrett.getAntallOvelser(); i++) {
                                fortsett = true;
                                while(fortsett) {
                                    if(idrett.regOvelse(new Ovelse(showInputDialog("Skriv inn beskrivelse av øvelsen"), showInputDialog("Skriv inn kjønnet øvelsen gjelder")))) {
                                        showMessageDialog(null, "Øvelsen ble regisrert");
                                        fortsett = false;
                                    } else {
                                        showMessageDialog(null, "Noe gikk galt, skrev du inn en bokstav eller flere når du skulle skrevet inn ett tall?");
                                    }
                                }
                            }
                        } catch(Exception e) {
                            showMessageDialog(null, "Noe gikk galt, prøv igjen");
                        }
                    } else {
                        showMessageDialog(null, "Det er ingen tidligere arrangement, prøv å lage ny");
                    }
                    break;
                default: break;
            }
        }
    }
    public static int getValg() {
        String title = (arr != null) ? arr.toString() + "\n\n" : "";
        return showOptionDialog(null,
                "Velg\n\n" + title,
                "Eksamen juni 2017",
                YES_NO_OPTION,
                INFORMATION_MESSAGE,
                null,
                muligheter,
                muligheter[0]
        );
    }

    public static Arrangement lesFraFil(String filnavn) {
        Arrangement output = null;
        try {
            FileInputStream fileIn = new FileInputStream(filnavn);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            output = (Arrangement) in.readObject();
            in.close();
            fileIn.close();
        } catch(IOException e) {
            showMessageDialog(null, "Filen ble ikke funnet");
        } catch(ClassNotFoundException e) {
            showMessageDialog(null, "Filen inneholder feil klasse");
        }
        return output;
    }
}
