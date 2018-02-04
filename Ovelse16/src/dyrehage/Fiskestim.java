package dyrehage;

public class Fiskestim extends Dyregruppe {
    private final double gjennomsnittligLengde;
    private final boolean kanDeleAkvarium;

    public Fiskestim(String norskNavn, String latNavn, String latFamilie,
                     int ankommetDato, String adresse, String gruppenavn, int antIndivider, double gjennomsnittligLengde, boolean kanDeleAkvarium) {
        super(norskNavn, latNavn, latFamilie,
        ankommetDato, adresse, gruppenavn, antIndivider);
        this.gjennomsnittligLengde = gjennomsnittligLengde;
        this.kanDeleAkvarium = kanDeleAkvarium;
    }

    public double getGjennomsnittligLengde() {
        return gjennomsnittligLengde;
    }
    public boolean getKanDeleAkvarium() {
        return kanDeleAkvarium;
    }

    @Override
    public String toString() {
        return getGruppenavn() + " er en fiskestim med " + getAntIndivider() + " fisker";
    }
}
