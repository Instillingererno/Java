package dyrehage;

public class Rovdyrfabrikk {
    public SkandinaviskeRovdyr nyBinne(int ankommetDato, String adresse, String navn, int fDato) {
        return new Hunnindivid("Brunbjørn", "Ursus arctos", "Ursidae", ankommetDato, adresse, navn, fDato, false, true);
    }
    public SkandinaviskeRovdyr nyHannbjørn(int ankommetDato, String adresse, String navn, int fDato) {
        return new Hannindivid("Brunbjørn", "Ursus arctos", "Ursidae", ankommetDato, adresse, navn, fDato, true, true);
    }
    public SkandinaviskeRovdyr nyUlvetispe(int ankommetDato, String adresse, String navn, int fDato) {
        return new Hunnindivid("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, false, true);
    }
    public SkandinaviskeRovdyr nyUlvehann(int ankommetDato, String adresse, String navn, int fDato) {
        return new Hannindivid("Ulv", "Canis lupus", "Canidae", ankommetDato, adresse, navn, fDato, true, true);
    }
}
