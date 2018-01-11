public class Visittkort {
    final Navn navn;
    int tlfmobil;
    int tlfjobb;
    String epost;

    public Visittkort(Navn navn, int tlfmobil, int tlfjobb, String epost) {
        this.navn = navn;
        this.tlfmobil = tlfmobil;
        this.tlfjobb = tlfjobb;
        this.epost = epost;
    }

    // Get metoder
    public Navn getNavn() {
        return navn;
    }
    public int getTlfmobil() {
        return tlfmobil;
    }
    public int getTlfjobb() {
        return tlfjobb;
    }
    public String getEpost() {
        return epost;
    }

    // Set metoder
    public void setTlfmobil(int tlfmobil) {
        this.tlfmobil = tlfmobil;
    }
    public void setTlfjobb(int tlfjobb) {
        this.tlfjobb = tlfjobb;
    }
    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String toString() {
        return navn.toString() +
                "\nMobiltlf: " + tlfmobil +
                "\nTlf jobb: " + tlfjobb +
                "\nEpost: " + epost;
    }


}
