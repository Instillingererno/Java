import static javax.swing.JOptionPane.*;

public class Klient {
    public static void main (String[] args){
        String[] muligheter = {"Nytt alfabet","Legg til ord", "Bokstaver ord", "Skriv alfabet til fil", "Skriv alfabetet på skjerm","Sorter alfabetet", "Avslutt"};
        final int NYTT_ALFABET = 0;
        final int LEGG_TIL_ORD = 1;
        final int BOKSTAVER_ORD = 2;
        final int SKRIV_TIL_FIL = 3;
        final int LIST_ALFATBET = 4;
        final int SORTER = 5;
        final int AVSLUTT = 6;
        int valg = showOptionDialog(null, "Velg", "Eksamen juni 2017", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
        Bokstaveringsalfabet ba = null;

        while (valg != AVSLUTT){
            switch (valg){
                case NYTT_ALFABET:
                    String[] muligheter2 = {"Opprett fra fil", "Opprett manuelt"};
                    int valg2 = showOptionDialog(null, "Velg hvordan du vil opprette alfabetet", "Eksamen 2017", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter2, muligheter2[0]);
                    switch(valg2) {
                        case 0:
                            ba = new Bokstaveringsalfabet(showInputDialog("Skriv inn navnet til alfabetet"), showInputDialog("Skriv inn filnavnet"));
                            break;
                        case 1:
                            ba = new Bokstaveringsalfabet(showInputDialog("Skriv inn navnet til alfabetet"), showInputDialog("Skriv inn alfabetet.\nFor eksempel bruk dette formatet\nAlfa-Bravo-Delta"), showInputDialog("Skriv skilletegn du brukte"));
                            break;
                    }
                    break;
                case LEGG_TIL_ORD:
                    break;
                case BOKSTAVER_ORD:
                    if(ba != null) {
                        String temp = ba.bokstavering(showInputDialog("Skriv inn ord å bokstavere"));
                        showMessageDialog(null,temp);
                    } else {
                        showMessageDialog(null,"Det er ingen alfabet å bruke");
                    }
                    break;
                case SKRIV_TIL_FIL: // ikke en del av oppgaven
                    break;
                case LIST_ALFATBET:
                    break;
                case SORTER:
                    break;
                default: break;
            }
            valg = showOptionDialog(null, "Velg", "Eksamen juni 2017", YES_NO_OPTION,
                    INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
        }
    }
}
