import java.time.LocalDate;
import java.time.Period;

public abstract class BonusMedlem {
    private final int medlNr;
    private final LocalDate innmeldtDato;
    private int poeng = 0;
    private final Personalia pers;

    public BonusMedlem(int medlNr, LocalDate innmeldtDato, Personalia pers) {
        this.medlNr = medlNr;
        this.innmeldtDato = innmeldtDato;
        this.pers = pers;
    }

    public int getMedlnr() {
        return medlNr;
    }
    public Personalia getPers() {
        return pers;
    }
    public LocalDate getInnmeldtDato() {
        return innmeldtDato;
    }
    public int getPoeng() {
        return poeng;
    }

    public int finnKvalPoeng(LocalDate dato) {
        int dagerMellom = Period.between(innmeldtDato, dato).getYears();
        if(dagerMellom >= 1) {
            return 0;
        } else {
            return getPoeng();
        }

    }
    public boolean okPassord(String passord) {
        return pers.okPassord(passord);
    }
    public void registrerPoeng(int poeng) {
        this.poeng += poeng;
    }
}
