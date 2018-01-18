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

    public int[] getReservert(String navn) {
        int teller = 0;
        for(String i : gjest) if(i != null) if(i.equals(navn)) teller++;

        System.out.println(teller);
        int[] output = new int[teller];
        teller = 0;
        for(int i = 0; i < gjest.length; i++) {
            if(gjest[i] != null) {
                if(gjest[i].equals(navn)) {
                    output[teller] = i;
                    teller++;
                }
            }
        }
        return output;
    }

    public void setLedig(int[] bordnummer) {
        for(int i : bordnummer) {
            if(!(i >= gjest.length)) gjest[i] = null;
        }
    }

    public boolean reserver(int[] bordnummer, String navn) {
        for(int i : bordnummer) if(gjest[i] != null) return false;
        for(int i : bordnummer) gjest[i] = navn;
        return true;
    }
}
