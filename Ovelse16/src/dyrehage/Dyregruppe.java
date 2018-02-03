package dyrehage;

public abstract class Dyregruppe extends Dyr {
    private final String gruppenavn;
    private int antIndivider;

    public Dyregruppe(String norskNavn, String latNavn, String latFamilie,
                      int ankommetDato, String adresse, String gruppenavn, int antIndivider) {
        super(norskNavn, latNavn, latFamilie,
        ankommetDato, adresse);
        this.gruppenavn = gruppenavn;
        this.antIndivider = antIndivider;
    }

    public String getGruppenavn() {
        return gruppenavn;
    }
    public int getAntIndivider() {
        return antIndivider;
    }
    public void setAntIndivider(int antIndivider) {
        this.antIndivider = antIndivider;
    }

    public String toString() {
        return gruppenavn + " består av " + getNorskNavn() + " og har " + antIndivider + " individer";
    }

    @Override // Dette kan egentlig ikke gjøres siden metoden er definert som final, for å kunne overskrive den må metoder ikke være final
    public String getNorskNavn() {
        return "gruppe av " + super.getNorskNavn();
    }
}
