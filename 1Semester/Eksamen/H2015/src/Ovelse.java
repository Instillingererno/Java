public class Ovelse {
    String beskrivelse;
    String kjonn;

    public Ovelse(String beskrivelse, String kjonn) {
        this.beskrivelse = beskrivelse;
        this.kjonn = kjonn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }
    public void getBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String getKjønn() {
        return kjonn;
    }
    public void setKjønn(String kjønn) {
        this.kjonn = kjønn;
    }

    public String toString() {
        return beskrivelse + " " + kjonn;
    }

    public boolean compareTo(Object x) {
        if(!(x instanceof Ovelse)) return false;
        if(this == x) return true;
        Ovelse temp = (Ovelse) x;
        if(this.beskrivelse.equals(temp.getBeskrivelse()) && this.kjonn.equals(temp.getKjønn())) {
            return true;
        } else {
            return false;
        }
    }
}
