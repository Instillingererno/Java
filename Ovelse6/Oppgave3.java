//Oppgave 7.8.3 s.231 30-08-2017

class Tekstanalyse {

    private int[] antallTegn = new int[30];

    public Tekstanalyse(String tekst) {

        for(int i = 0; i < tekst.length(); i++) {

            char tegn = tekst.charAt(i);
            int verdi = tegn;
            if((verdi >= 65 && verdi <= 90) || (verdi >= 97 && verdi <= 122)) { //Sjekker for a-z og A-Z
                if(verdi <= 90) { //Gjelder A-Z
                    this.antallTegn[(verdi + 32) - 97] += 1;
                } else { //Gjelder a-z
                    this.antallTegn[verdi - 97] += 1;
                }
            } else if (verdi == 230 || verdi == 198) { //Sjekker for æ og Æ
                this.antallTegn[26] += 1;
            } else if (verdi == 248 || verdi == 216) { //Sjekker for ø og Ø
                this.antallTegn[27] += 1;
            } else if (verdi == 229 || verdi == 197) { //Sjekker for å og Å
                this.antallTegn[28] += 1;
            } else { //Alt annet
                this.antallTegn[29] += 1;
            }

        }

    }

    public int getTotalBokstaver() {

        int antall = 0;
        for(int i = 0; i < 29; i++) {
            antall += this.antallTegn[i];
        }

        return antall;

    }

    public int getAntallForskjelligeBokstaver() {

        int antall = 0;
        for(int i = 0; i < 29; i++) {
            if (this.antallTegn[i] > 0) {
                antall += 1;
            }
        }

        return antall;

    }

    public double getProsentAvIkkeBokstaver() {
        double teller = this.antallTegn[29];
        double nevner = this.getTotalBokstaver() + this.antallTegn[29];
        return (teller / nevner * 100);
    }

    public int getAntallForekomst(char tekst) {

        int verdi = tekst;
        if((verdi >= 65 && verdi <= 90) || (verdi >= 97 && verdi <= 122)) { //Sjekker for a-z og A-Z
            if(verdi <= 90) { //Gjelder A-Z
                return this.antallTegn[(verdi + 32) - 97];
            } else { //Gjelder a-z
                return this.antallTegn[verdi - 97];
            }
        } else if (verdi == 230 || verdi == 198) { //Sjekker for æ og Æ
            return this.antallTegn[26];
        } else if (verdi == 248 || verdi == 216) { //Sjekker for ø og Ø
            return this.antallTegn[27];
        } else if (verdi == 229 || verdi == 197) { //Sjekker for å og Å
            return this.antallTegn[28];
        } else { //Alt annet
            return this.antallTegn[29];
        }

    }

    public String getHoyeste() {
        


    }

}

class Oppgave3 {

    public static void main(String[] args) {

        Tekstanalyse test = new Tekstanalyse("Dette er en test");

        System.out.println(test.getTotalBokstaver());
        System.out.println(test.getAntallForskjelligeBokstaver());
        System.out.println(test.getProsentAvIkkeBokstaver());
        System.out.println(test.getAntallForekomst('a'));
    }

}
