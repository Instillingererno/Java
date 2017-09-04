//Oppgave 5.9.3 s.178 28-08-2017

import java.util.Random;

class Spiller {
    private String navn;
    private int poeng = 0;
    private int kast;
    java.util.Random terning = new java.util.Random();

    public Spiller(String navn) {
        this.navn = navn;
    }
    public int getPoengSum() {
        return this.poeng;
    }
    public void kastTerningen() {
        this.kast = terning.nextInt(6) + 1;
        if(this.kast == 1) {
            this.poeng = 0;
        } else {
            this.poeng += this.kast;
        }
    }
    public boolean erFerdig() {
        if(this.poeng > 100) {
            System.out.println("Spiller " + this.navn + " vant!");
            return true;
        } else {
            return false;
        }
    }
}

class Oppgave3 {

    public static void main(String[] args) {

        Spiller a = new Spiller("a");
        Spiller b = new Spiller("b");

        int runde = 0;

        while(!a.erFerdig() && !b.erFerdig()) {
            runde++;

            a.kastTerningen();
            b.kastTerningen();

            System.out.println("---------------------------------");
            System.out.println("Runde nr. " + runde);
            System.out.println("a har poengsum " + a.getPoengSum());
            System.out.println("b har poengsum " + b.getPoengSum());
        }

    }

}
