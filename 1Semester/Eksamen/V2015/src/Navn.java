public class Navn {
    final private String fornavn;
    final private String etternavn;

    public Navn(String fornavn, String etternavn) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    // Get metoder
    public String getFornavn() {
        return fornavn;
    }
    public String getEtternavn() {
        return etternavn;
    }

    public String toString() {
        return etternavn + ", " + fornavn;
    }

    public boolean equals(Navn x) {
        if(fornavn.equals(x.getFornavn()) && etternavn.equals(x.getEtternavn())) {
            return true;
        } else {
            return false;
        }
    }
    public int compareTo(Navn x) {
        int compareTo = this.toString().compareTo(x.toString());
        if(compareTo < 0) {
            return -1;
        } else if(compareTo == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}
