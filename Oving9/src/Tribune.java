public abstract class Tribune {
    private final String tribunenavn;
    private final int kapasitet;
    private final int pris;

    public Tribune(String tribunenavn, int kapasitet, int pris) {
        this.tribunenavn = tribunenavn;
        this.kapasitet = kapasitet;
        this.pris = pris;
    }
    public int getPris() {
        return pris;
    }
    public int getKapasitet() {
        return kapasitet;
    }
    public String getTribunenavn() {
        return tribunenavn;
    }

    abstract public int finnAntallSolgteBilletter();
    abstract public int finnInntekt();
    abstract public Billett[] kjopBillett(int antallBilletter);
    abstract public Billett[] kjopBillett(String[] navn);

}

class Staa extends Tribune {
    private int antSolgteBilletter;

    public Staa(String tribunenavn, int kapasitet, int pris) {
        super(tribunenavn, kapasitet, pris);
        this.antSolgteBilletter = 0;
    }

    @Override
    public int finnAntallSolgteBilletter() {
        return antSolgteBilletter;
    }
    @Override
    public int finnInntekt() {
        return antSolgteBilletter * getPris();
    }
    @Override
    public Billett[] kjopBillett(int antallBilletter) {
        if((antallBilletter + antSolgteBilletter) > getKapasitet()) {
            return null;
        } else {
            antSolgteBilletter += antallBilletter;
            Billett[] output = new Billett[antallBilletter];
            for(Billett i : output) {
                i = new StaaplassBillett(getTribunenavn(), getPris());
            }
            return output;
        }
    }
    @Override
    public Billett[] kjopBillett(String[] navn) {
        return kjopBillett(navn.length);
    }
}

class Sitte extends Tribune {
    private int[] antOpptatt; //tabellstørrelse: antall rader

    public Sitte(String tribunenavn, int kapasitet, int pris, int antallRader, int plasserPrRad) {
        super(tribunenavn, (plasserPrRad * antallRader), pris);
        this.antOpptatt = new int[antallRader];
    }

    public int[] getAntOpptatt() {
        return antOpptatt;
    }

    @Override
    public int finnAntallSolgteBilletter() {
        int solgteBilletter = 0;
        for (int i : antOpptatt) {
            solgteBilletter += i;
        }
        return solgteBilletter;
    }
    @Override
    public int finnInntekt() {
        return finnAntallSolgteBilletter() * getPris();
    }
    @Override
    public Billett[] kjopBillett(int antallBilletter) {
        int plasserPrRad = getKapasitet() / antOpptatt.length;
        for (int i = 0; i < antOpptatt.length; i++)
            if ((antallBilletter + antOpptatt[i]) <= plasserPrRad) {
                Billett[] output = new Billett[antallBilletter];
                for (Billett j : output) {
                    j = new SitteplassBillett(getTribunenavn(), getPris(), i, antOpptatt[i]);
                    antOpptatt[i]++;
                }
                return output;
            }
        return null;
    }
    @Override
    public Billett[] kjopBillett(String[] navn) {
        return kjopBillett(navn.length);
    }
}

class VIP extends Sitte {
    private String[][] tilskuer; //tabellstørrelse: antall rader * antall plasser pr rad

    public VIP(String tribunenavn, int kapasitet, int pris, int antallRader, int plasserPrRad) {
        super(tribunenavn, kapasitet, pris, antallRader, plasserPrRad);
        this.tilskuer = new String[antallRader][plasserPrRad];
    }

    @Override
    public Billett[] kjopBillett(int antallBilletter) {
        return null;
    }
    @Override
    public Billett[] kjopBillett(String[] navn) {
        int teller = 0;
        int plasserPrRad = getKapasitet() / getAntOpptatt().length;
        for (int i = 0; i < getAntOpptatt().length; i++)
            if ((navn.length + getAntOpptatt()[i]) <= plasserPrRad) {
                Billett[] output = new Billett[navn.length];
                for (Billett j : output) {
                    j = new SitteplassBillett(getTribunenavn(), getPris(), i, getAntOpptatt()[i]);
                    tilskuer[i][getAntOpptatt()[i]] = navn[teller];
                    getAntOpptatt()[i]++;
                    teller++;
                }
                return output;
            }
        return null;
    }
}

