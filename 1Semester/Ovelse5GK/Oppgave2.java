//Oppgave 6.10.2 s.207 28-08-2017

import java.lang.Math.*;

class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int den) {
        if(den == 0) {
            throw new IllegalArgumentException("Det går ikke an å dele på 0");
        } else {
            this.numerator = num;
            this.denominator = den;
        }
    }
    public Fraction(int num) {
        this.numerator = num;
        this.denominator = 1;
    }
    public void getSum(int num, int den) {
        this.numerator = this.numerator * den + num * this.denominator;
        this.denominator = this.denominator * den;
        this.shorten();
    }
    public void getDiff(int num, int den) {
        this.numerator = this.numerator * den - num * this.denominator;
        this.denominator = this.denominator * den;
        this.shorten();
    }
    public void getProd(int num, int den) {
        this.numerator = this.numerator * num;
        this.denominator = this.denominator * den;
        this.shorten();
    }
    public void getDiv(int num, int den) {
        this.numerator = this.numerator * den;
        this.denominator = this.denominator * num;
        this.shorten();
    }
    private void shorten() {
        int i = 0;
        int max = 2;
        while(i < max) {
            if(Math.abs(this.numerator) > Math.abs(this.denominator)) {
                max = Math.abs(this.denominator);
            } else {
                max = Math.abs(this.numerator);
            }
            if(max >= 2) {
                for(i = 2; i <= max; i++) {
                    if(this.numerator % i == 0 && this.denominator % i == 0) {
                        this.numerator = this.numerator / i;
                        this.denominator = this.denominator / i;
                        break;
                    }
                }
            }
        }
    }
    public void printFraction() {
        System.out.println(this.numerator + "/" + this.denominator);
    }

};

class Oppgave2 {

    public static void main(String[] args) {

        System.out.println("Klasse test");
        System.out.println("Lager et objekt ved navn 'test' med verdiene 5 i teller og 7 i nevner");
        Fraction test = new Fraction(1,2);
        System.out.println("Tester summere funksjonen ved å kjøre test.getSum(2,3) som adderer 5/7 og 2/3");
        System.out.println("Forventet svar er: 29/21");
        test.getSum(3,4);
        test.printFraction();
        System.out.println("Tester subtrahere funksjonen ved å kjøre test.getDiff(2,8), som subtraherer 29/21 med 2/8");
        System.out.println("Forventet svar er 190/168, som forkortet blir 95/84");
        test.getDiff(2,8);
        test.printFraction();
        System.out.println("Tester multiplisere funksjonen ved å kjøre test.getProd(3,2), som multipliserer 95/84 med 3/2");
        System.out.println("Forventet svar er 285/168, som forkortet blir 95/56");
        test.getProd(3,2);
        test.printFraction();
        System.out.println("Tester divisjon funksjonen ved å kjre test.getDiv(5,6), som dividerer 95/56 med 5/6");
        System.out.println("Forventet svar er 570/280, som forkortet blir 57/28");
        test.getDiv(5,6);
        test.printFraction();

    }

}
