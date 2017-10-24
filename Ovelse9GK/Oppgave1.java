//Oppgave 11.10.1 s.386 09-10-2017

import java.time.Year;
import static javax.swing.JOptionPane.*;

class Person {
    private final String fornavn;
    private final String etternavn;
    private final int fodselsar;
    Person(String fnavn, String enavn, int fodsar) {
        this.fornavn = fnavn.trim();
        this.etternavn = enavn.trim();
        this.fodselsar = fodsar;
    }
    public String getFornavn() {
        return this.fornavn;
    }
    public String getEtternavn() {
        return this.etternavn;
    }
    public int getFodselsar() {
        return this.fodselsar;
    }
}

class ArbTaker {
    Person personalia;
    String arbtakernr;
    int ansettelsesar;
    double manedslonn;
    byte skatteprosent = 40;
    ArbTaker(Person person, String arbnr, int ansar, int manedslonn) {
        this.personalia = person;
        this.arbtakernr = arbnr;
        this.ansettelsesar = ansar;
        this.manedslonn = manedslonn;
    }
    public Person getPersonalia() {
        return personalia;
    }
    public String getArbtakernr() {
        return arbtakernr;
    }
    public int getAnsettelsesar() {
        return ansettelsesar;
    }
    public double getManedslonn() {
        return manedslonn;
    }
    public byte getSkatteprosent() {
        return skatteprosent;
    }
    public void setManedslonn(double lonn) {
        this.manedslonn = lonn;
    }
    public void setSkatteProsent(byte input) {
        this.skatteprosent = input;
    }
    public String SkatteTrekkManed() {
        return this.personalia.getFornavn() + " betaler " + Double.toString(this.manedslonn * this.skatteprosent / 100);
    }
    public String BruttoLonn() {
        return this.personalia.getFornavn() + " har brutto lønn på: " + Double.toString(this.manedslonn * 12);
    }
    public String SkatteTrekkAr() {
        return this.personalia.getFornavn() + " betaler i året: " + Double.toString((this.manedslonn * this.skatteprosent / 100) * 10.5);
    }
    public String Navn() {
        return this.personalia.getEtternavn() + ", " + this.personalia.getFornavn();
    }
    public int Alder() {
        return Year.now().getValue() - this.personalia.getFodselsar();
    }
    public int AntallArIBedrift() {
        return Year.now().getValue() - this.ansettelsesar;
    }
    public boolean ArIBedriftMot(int input) {
        return (this.AntallArIBedrift() > input) ? true : false;
    }
    public String toString() {
        int aar = 5; //Dis one
        String output = "Fornavn: " + this.personalia.getFornavn() +
                        "\nEtternavn: " + this.personalia.getEtternavn() +
                        "\nFødselsår: " + this.personalia.getFodselsar() +
                        "\nSkatte trekk per måned: " + this.SkatteTrekkManed() +
                        "\nBrutto lønn: " + this.BruttoLonn() +
                        "\nSkatte trekk per år: " + this.SkatteTrekkAr() +
                        "\nNavn: " + this.Navn() +
                        "\nAlder: " + this.Alder() +
                        "\nAntall år i bedriften: " + this.AntallArIBedrift() +
                        "\nAr i bedrift mot " + aar + ": " + this.ArIBedriftMot(aar);
        return output;
    }
    public int lesValg() {
        String[] meny = {"Endre månedslønn", "Endre skatteprosent"};
        String testString = this.toString() + "\nGjør endringer?";
        int valg = showOptionDialog(null, testString, "Gjør endringer?", YES_NO_OPTION, PLAIN_MESSAGE, null, meny, meny[0]);
        return valg;
    }
    public void endring(int input) {
        switch(input) {
            case 0:
                try {
                    double nyLonn = Double.parseDouble(showInputDialog("Endre månedslønn til: "));
                    this.setManedslonn(nyLonn);
                }
                catch(NumberFormatException e) {
                    showMessageDialog(null, "Månedslønn kan ikke være tekst, prøv igjen");
                }
                break;
            case 1:
                try {
                    byte nySkatt = Byte.parseByte(showInputDialog("Endre skatteprosent til: "));
                    this.setSkatteProsent(nySkatt);
                }
                catch(NumberFormatException e) {
                    showMessageDialog(null, "Skatteprosent kan ikke være tekst");
                }
                break;
        }
    }
}
class Oppgave1 {
    public static void main(String[] args) {
        Person pers1 = new Person("Ola", "Nordman", 1980);
        ArbTaker arbtaker1 = new ArbTaker(pers1, "007", 2010, 100000);
        showMessageDialog(null, arbtaker1.toString());
        int valg;
        while((valg = arbtaker1.lesValg()) != CLOSED_OPTION) {
            arbtaker1.endring(valg);
        }
    }
}
