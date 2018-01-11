import java.io.*;

public class UtleieFirma implements Serializable {
    private String navn;
    private Bil[] biler;

    public UtleieFirma(String navn, int antallBiler) {
        this.navn = navn;
        this.biler = new Bil[antallBiler];
    }

    public boolean regBil(Bil x) {
        for(Bil i : biler) if(i != null) if(i.equals(x)) return false; //Går gjennom hele Bil arrayen og skjekker om det er noen like biler
        for(Bil i : biler) if(i == null) {
            i = x;
            return true;
        }  // Finner første tomme plass
        return false;
    }

    public Bil[] sorter() {
        boolean sortert = false;
        while(!sortert) {
            sortert = true;
            for(int i = 0; i < biler.length; i++) {
                if(biler[i] == null) {
                    if(biler[i + 1] != null) {
                        biler[i] = biler[i+1];
                        biler[i+1] = null;
                        sortert = false;
                    }
                } else if(biler[i+1] != null) {
                    if(biler[i].getRegnr() > biler[i+1].getRegnr()) {
                        Bil temp = biler[i];
                        biler[i] = biler[i+1];
                        biler[i+1] = temp;
                        sortert = false;
                    }
                }
            }
        }
        return biler;
    }
}
