package dyrehage;

public class Fugleflokk extends Dyregruppe {
    private final double gjennomsnittligVekt;
    private final boolean svømmer;

    public Fugleflokk(String norskNavn, String latNavn, String latFamilie,
                      int ankommetDato, String adresse, String gruppenavn, int antIndivider, double gjennomsnittligVekt, boolean svømmer) {
        super(norskNavn, latNavn, latFamilie,
        ankommetDato, adresse, gruppenavn, antIndivider);
        this.gjennomsnittligVekt = gjennomsnittligVekt;
        this.svømmer = svømmer;
    }

    public double getGjennomsnittligVekt() {
        return gjennomsnittligVekt;
    }
    public boolean getSvømmer() {
        return svømmer;
    }

    @Override
    public String toString() {
        return getGruppenavn() + " er en fugleflokk med " +getAntIndivider() + " fugler";
    }
}
