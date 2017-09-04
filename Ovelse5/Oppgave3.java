//Oppgave 6.10.3 s.207 29-08-2017

import java.util.Random;

class MinRandom {
    java.util.Random tall = new java.util.Random();

    public int nesteHeltall(int nedre, int ovre) { // Intervallet [nedre, ovre]
        if(ovre <= nedre || ovre - nedre == 1) { //Det er ingen heltall mellom nedre og ovre hvis differansen er 1
            throw new IllegalArgumentException("Den øvre grensen kan ikke være mindre enn den nedre grensen!");
        } else {
            return nedre + this.tall.nextInt(ovre - nedre) + 1; //+1 for å unngå å legge til 0
        }
    }

    public double nesteDesimaltall(double nedre, double ovre) { // Intervallet <nedre, ovre>

        if(ovre <= nedre) {
            throw new IllegalArgumentException("Den øvre grensen kan ikke være mindre enn den nedre grensen!");
        } else {
            return nedre + this.tall.nextDouble() * (ovre - nedre);
        }

    }

}

class Oppgave3 {

    public static void main(String[] args) {

        MinRandom test = new MinRandom();

        System.out.println("Tester nesteHeltall(1,10) forventer et heltall mellom 1 og 10");
        System.out.println(test.nesteHeltall(1,10));
        System.out.println("Tester nesteDesimaltall(2,10) forventer et tall potensielt med desimal mellom 2 og 10");
        System.out.println(test.nesteDesimaltall(2,10));
        System.out.println("Tester nesteHeltall(5,7) forventer 6");
        System.out.println(test.nesteHeltall(5,7));
        System.out.println("Tester nesteDesimaltall(0,1) forventer et desimaltall mellom 0 og 1");
        System.out.println(test.nesteDesimaltall(0,1));


    }

}
