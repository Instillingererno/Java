import java.io.Serializable;
import java.util.Arrays;

public class Mobil implements Serializable{
    String modell;
    Visittkort[] visittkortliste;
    int antVisittkort;
    int egetTlfnr;

    public Mobil(String modell, int antVisittkort, int egetTlfnr) {
        this.modell = modell;
        this.antVisittkort = antVisittkort;
        this.egetTlfnr = egetTlfnr;
        this.visittkortliste = new Visittkort[antVisittkort];
    }

    public Visittkort[] sorter() {
        int teller = 0;
        boolean sortert = false;
        if(visittkortliste != null) {
            // Dyp kopi av visittkort listen
            Visittkort[] kopi = new Visittkort[visittkortliste.length];
            for(Visittkort i : visittkortliste) {
                if(i != null) {
                    kopi[teller] = new Visittkort(i.getNavn(), i.getTlfmobil(), i.getTlfjobb(), i.getEpost());
                    teller++;
                }
            }
            // Sortering av kopi
            while(!sortert) {
                sortert = true;
                for(int i = 0; i < kopi.length-1; i++) {
                    if(kopi[i] != null) {
                        if(kopi[i+1] != null) {
                            switch (kopi[i].getNavn().compareTo(kopi[i+1].getNavn())) {
                                case 1:
                                    Visittkort temp = kopi[i];
                                    kopi[i] = kopi[i+1];
                                    kopi[i+1] = temp;
                                    sortert = false;
                                    break;
                                default: break;
                            }
                        } else {
                            kopi[i+1] = kopi[i];
                            kopi[i] = null;
                            sortert = false;
                        }
                    }
                }
            }
            return kopi;
        }
        return null;
    }

    public String toString() {
        String temp = "";
        Visittkort[] kopi = sorter();
        for(Visittkort i : kopi) temp += (i != null) ? "\n" + i.toString() + "\n-------------------------" : "";
        return modell + ", " + egetTlfnr +
                "\nVisittkortliste: " +
                temp;
    }

    public boolean regVisittkort(Visittkort x) {
        if(visittkortliste == null) return false;
        for(Visittkort i : visittkortliste) if(i != null) if(i.getNavn().equals(x.getNavn())) return false;
        for(int i = 0; i < visittkortliste.length; i++) {
            if(visittkortliste[i] == null) {
                visittkortliste[i] = new Visittkort(x.getNavn(), x.getTlfmobil(), x.getTlfjobb(), x.getEpost());
                System.out.println(Arrays.toString(visittkortliste));
                return true;
            }
        }
        return false;
    }
}
