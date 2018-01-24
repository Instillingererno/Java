import javax.swing.*;

import java.time.LocalDate;

import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main(String[] args) {
        String[] valgMuligheter = {"nytt medlem", "finn poeng", "legg til poeng", "oppdater medlemmer", "skriv ut", "avslutt"};
        Medlemsarkiv arkiv = new Medlemsarkiv();

        Personalia ole = new Personalia("Olsen", "Ole", "ole.olsen@dot.com", "ole");
        Personalia tove = new Personalia("Hansen", "Tove", "tove.hansen@dot.com", "tove");

        arkiv.nyMedlem(ole, LocalDate.now());
        arkiv.nyMedlem(tove, LocalDate.now());

        int valg;
        do {
            valg = JOptionPane.showOptionDialog(null, "Velg noe å gjøre", "Medlemsarkiv", YES_NO_OPTION, PLAIN_MESSAGE, null, valgMuligheter, valgMuligheter[5]);
            switch (valg) {
                case 0:
                    Personalia pers = getPersonalia();
                    if(pers != null) {
                        int medlNr = arkiv.nyMedlem(pers, LocalDate.now());
                        showMessageDialog(null, "Nytt medlem er registrer, medlems nummer er: \n" + medlNr);
                    }
                    break;
                case 1:
                    int medlNr = intInput("Skriv inn medlemsnr");
                    String passord;
                    if(medlNr != -1) {
                        if((passord = showInputDialog("passord")) != null) {
                            int svar = arkiv.finnPoeng(medlNr, passord);
                            if(svar != -1) {
                                showMessageDialog(null, "poeng for " + medlNr + "er:\n" + svar);
                            } else {
                                showMessageDialog(null, "Ingen poeng ble funnet for det medlemmet, kan passordet være feil?");
                            }
                        }
                    }
                    break;
                case 2:
                    medlNr = intInput("Skriv inn medlemsnr");
                    int poeng;
                    if(medlNr != -1) {
                        if((poeng = intInput("Skriv inn poeng")) != -1) {
                            if(arkiv.registrerPoeng(medlNr, poeng)) {
                                showMessageDialog(null, "poengene ble registrert");
                            } else {
                                showMessageDialog(null, "ingen medlem ble funnet");
                            }
                        }
                    }
                    break;
                case 3:
                    String gammel = arkiv.toString();
                    arkiv.sjekkMedlemmer();
                    showMessageDialog(null, "fra:\n" + gammel + "\n\ntil:\n" + arkiv.toString());
                    break;
                case 4:
                    showMessageDialog(null, arkiv.toString());
                    break;
            }
        } while (valg != 5);
    }

    private static int intInput(String tekst) {
        int output = -1;
        boolean fortsett = true;
        do {
            try {
                String input = showInputDialog(tekst);
                if(input != null) {
                    output = Integer.parseInt(input);
                }
                fortsett = false;
            } catch(NumberFormatException n) {
                showMessageDialog(null, "Det er ikke ett tall, prøv igjen");
            }
        } while (fortsett);
        return output;
    }

    private static Personalia getPersonalia() {
        String fornavn;
        String etternavn;
        String ePostadr;
        String passord;
        if((fornavn = showInputDialog("Fornavn")) != null) {
            if((etternavn = showInputDialog("Etternavn")) != null) {
                if((ePostadr = showInputDialog("Epost")) != null) {
                    if((passord = showInputDialog("Passord")) != null) {
                        return new Personalia(fornavn,etternavn,ePostadr,passord);
                    }
                }
            }
        }
        return null;
    }
}
