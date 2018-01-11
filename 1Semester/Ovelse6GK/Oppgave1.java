//Oppgave 7.8.1 s.229 29-08-2017

import java.util.Random;

class Oppgave1 {

    public static void main(String[] args) {

        java.util.Random random = new java.util.Random();

        int[] tall = new int[10];

        for(int i = 0; i < 1000; i++) {

            tall[random.nextInt(10)]++;

        }

        for(int j = 0; j < tall.length; j++) {

            String stjerne = "";

            //System.out.println(tall[j]);
            double antallStjerner = (((double) tall[j] / 10) + 0.5);
            for(int i = 0; i < (int) antallStjerner; i++) {
                stjerne += "*";
            }
            System.out.println(j + "    " + tall[j] + "    " + stjerne);

        }
    }
}
