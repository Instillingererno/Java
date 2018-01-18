public class Student {
    private final String navn;
    private int antOppg;

    public Student(String navn) {
        this.navn = navn;
        this.antOppg = 0;
    }

    public String getNavn() {
        return navn;
    }
    public int getAntOppg() {
        return antOppg;
    }
    public void setAntOppg(int antOppg) {
        if(antOppg < 0) {
            throw new IllegalArgumentException();
        } else {
            this.antOppg = antOppg;
        }
    }
    public String toString() {
        return navn + " har fullført " + antOppg + " oppgaver";
    }
}
