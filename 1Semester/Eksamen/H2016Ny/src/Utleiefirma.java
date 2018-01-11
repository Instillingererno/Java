import java.io.*;

public class Utleiefirma implements Serializable {
    private String navn;
    private Bil[] biler;

    public Utleiefirma(String navn, int biler) {
        this.navn = navn;
        this.biler = new Bil[biler];
    }

    public boolean regBil(Bil x) {
        if(biler != null) {
            for(Bil i : biler) if(i != null) if(this.equals(x)) return false;
            for(int i = 0; i < biler.length; i++) {
                if(biler[i] == null) {
                    biler[i] = x;
                    return true;
                }
            }
        }
        return false;
    }

    public Bil[] sorter() {
        boolean sortert = false;
        if(biler != null) {
            while(!sortert) {
                sortert = true;
                for(int i = 0; i < biler.length-1; i++) {
                    if(biler[i] == null && biler[i+1] != null) {
                        biler[i] = biler[i+1];
                        biler[i+1] = null;
                        sortert = false;
                    } else if((biler[i] != null && biler[i+1] != null) && biler[i].getRegnr() < biler[i+1].getRegnr()) {
                        Bil temp = biler[i];
                        biler[i] = biler[i+1];
                        biler[i+1] = temp;
                        sortert = false;
                    }
                }
            }
            return biler;
        }
        return null;
    }
}
