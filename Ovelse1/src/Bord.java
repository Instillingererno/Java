public class Bord {
    String[] gjest;

    public Bord(int antallBord) {
        this.gjest = new String[antallBord];
    }

    public int getLedige() {
        int teller = 0;
        for(String i : gjest) {
            if(i == null) {
                teller++;
            }
        }
        return teller;
    }

    public int getOpptatt() {
        int teller = 0;
        for(String i : gjest) {
            if(i != null) {
                teller++;
            }
        }
        return teller;
    }

    public void setLedig(int[] bordnummer) {
        for(int i : bordnummer) {
            gjest[i] = null;
        }
    }

    public 
}
