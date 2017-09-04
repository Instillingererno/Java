//Oppgave 5.9.2 s.177 28-08-2017

import java.util.Scanner;

class Valuta {

    private final String navn;
    private double verdi;

    public Valuta(String navn, double verdi) {

        this.navn = navn;
        this.verdi = verdi;

    }

    public double Verdi(double mengde) {

        return mengde / this.verdi;

    }

}

class Oppgave2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Valuta USD = new Valuta("USD", 0.129011);
        Valuta SEK = new Valuta("SEK", 1.02800886);
        Valuta AMD = new Valuta("AMD", 61.7041);
        Valuta EUR = new Valuta("EUR", 0.108001);

        System.out.println("Velg valuta");
        System.out.println("1: USD");
        System.out.println("2: SEK");
        System.out.println("3: AMD");
        System.out.println("4: EUR");
        System.out.println("5: Avslutt");

        int valg = scanner.nextInt();
        if(valg == 5) {
            System.exit(0);
        }

        System.out.println("Skriv inn mengde av valgt valuta for å gjøre det om til norske kroner.");

        int antall = scanner.nextInt();

        switch (valg) {
            case 1:
                System.out.println(USD.Verdi(antall));
                break;
            case 2:
                System.out.println(SEK.Verdi(antall));
                break;
            case 3:
                System.out.println(AMD.Verdi(antall));
                break;
            case 4:
                System.out.println(EUR.Verdi(antall));
                break;
        }

    }

}
