package dyrehage;

import java.time.LocalDate;
import java.time.Year;

public abstract class Individ extends Dyr implements SkandinaviskeRovdyr {
    private final String navn;
    private final int fDato;
    private final boolean hanndyr;
    private final boolean farlig;

    public Individ(String norskNavn, String latNavn, String latFamilie,
                   int ankommetDato, String adresse, String navn, int fDato, boolean hanndyr, boolean farlig) {
        super(norskNavn, latNavn, latFamilie,
        ankommetDato, adresse);
        this.navn = navn;
        this.fDato = fDato;
        this.hanndyr = hanndyr;
        this.farlig = farlig;
    }

    public String getNavn() {
        return navn;
    }
    public int getFdato() {
        return fDato;
    }
    public boolean getHanndyr() {
        return hanndyr;
    }
    public boolean getFarlig() {
        return farlig;
    }

    @Override
    public String toString() {
        return navn + " er en " + getNorskNavn() + " og var født " + fDato;
    }

    public String skrivUtInfo() {
        return getNavn() + " er " + getAlder() + " år gammel; med kull " + getAntKull();
    }

    public int getAlder() {
        return LocalDate.now().getYear() - fDato;
    }

    public void flytt(String nyAdresse) {
        super.setAdresse(nyAdresse);
    }
}
