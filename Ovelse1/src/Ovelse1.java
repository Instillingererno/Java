import java.util.Arrays;

import static javax.swing.JOptionPane.*;

public class Ovelse1 {
    public static void main(String[] args) {
        /*
        Spør brueren om restaurantnavn, etableringsår, og antall bord.
        opprett et Restaurant-objekt
         */
        final int reserverBord = 0;
        final int finnBordnr = 1;
        final int frigiBord = 2;
        final int avslutt = 3;
        final String[] valgMuligheter = {"Reserver bord", "Finn bordnr", "Frigi bord", "Avslutt"};


        String restaurantNavn = showInputDialog("Hva er navnet på restauranten");
        int etableringsår = getIntInput("Når ble restauranten etablert (årstall, f.eks '2000')");
        int antallBord = getIntInput("Hvor mange bord har restauranten");

        Restaurant nyRestaurant = new Restaurant(restaurantNavn, etableringsår, antallBord);

        System.out.println("Endrer navnet på restauranten fra: " + nyRestaurant.getNavn() + " til: " + "**" + nyRestaurant.getNavn().trim() + "**");
        nyRestaurant.setNavn("**" + nyRestaurant.getNavn().trim() + "**");


        int valg;
        //int valg = showOptionDialog(null, "Velg noe å gjøre", "Restaurant", DEFAULT_OPTION, QUESTION_MESSAGE, null, valgMuligheter, valgMuligheter[3]);
        do{
            String melding = nyRestaurant.getNavn() +
                    "\n" + nyRestaurant.getAlder() + " år med matglede" +
                    "\n" +
                    "\nAntall bord ledige: " + nyRestaurant.bord.getLedige() + " av " + (nyRestaurant.bord.getLedige() + nyRestaurant.bord.getOpptatt()) + " totalt" +
                    "\n\nVelg noe å gjøre";

            valg = showOptionDialog(null, melding, "Restaurant", DEFAULT_OPTION, QUESTION_MESSAGE, null, valgMuligheter, valgMuligheter[3]); //vis liste over mulige valg

            switch(valg) {
                case reserverBord: // reservere et antall bord på et bestemt navn
                    //...les inn navn og antall bord, og kall metode...
                    String navn = showInputDialog("Skriv inn navn å reservere bord på");
                    int[] bord = getIntArrayInput("Skriv inn bordnummer å reservere, bruke komma for å reservere flere bord om gangen \nfeks. '0, 1, 2'");
                    if(bord != null) {
                        if(nyRestaurant.bord.reserver(bord, navn)) showMessageDialog(null, "Reserversjonen til " + navn + " på bordene " + Arrays.toString(bord) + " gikk fint");
                    } else {
                        showMessageDialog(null, "Noe gikk galt, kanskje var ett av bordene reserver fra før");
                    }
                    break;
                case finnBordnr: // finne alle bordene som er reservert på et bestemt navn
                    //...les inn navn, og kall metode...
                    navn = showInputDialog("Skriv inn navn å søke på");
                    showMessageDialog(null, Arrays.toString(nyRestaurant.bord.getReservert(navn)));
                    break;
                case frigiBord: // frigi en rekke bord, bordnummer er gitt
                    //... les inn aktuelle bordnummer og kall metode...
                    bord = getIntArrayInput("Skriv inn bordnummerene som skal frigis, på måten '0, 1, 2'");
                    nyRestaurant.bord.setLedig(bord);
                    break;
                case avslutt:
                    break;
            }
        } while(valg != avslutt);
    }

    private static int getIntInput(String melding) {
        int input = 0;
        while(true) {
            try {
                String stringInput = showInputDialog(melding);
                if(stringInput != null) {
                    input = Integer.parseInt(stringInput);
                    return input;
                } else {
                    return -1;
                }
            } catch(Exception e) {
                showMessageDialog(null, "Noe gikk galt, prøvte du å skrive inn noe annet en et heltall? Prøv igjen");
            }
        }
    }
    private static int[] getIntArrayInput(String melding) {
        while(true) {
            try {
                String input = showInputDialog(melding);
                if(input != null) {
                    String[] mellomlanding = input.split(",");
                    System.out.println(Arrays.toString(mellomlanding));
                    int[] output = new int[mellomlanding.length];
                    for(int i = 0; i < mellomlanding.length; i++) {
                        output[i] = Integer.parseInt(mellomlanding[i].trim());
                    }
                    return output;
                } else {
                    return null;
                }
            } catch(Exception e) {
                showMessageDialog(null, "Noe gikk galt, prøvte du å skrive inn bokstaver i stedet for tall? Prøv igjen!");
            }
        }
    }
}

