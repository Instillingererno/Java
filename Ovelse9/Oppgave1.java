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
    public void setManedslonn(int lonn) {
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
}

class ValgTre {
    String[] meny = {"Endre månedslønn", "Endre skatteprosent"};
    public int lesValg() {
        int valg = showOptionDialog(null, Oppgave1.toString() + "\nGjør endringer?", YES_NO_OPTION, PLAIN_MESSAGE, null, meny, meny[0]);
        return valg;
    }
    
}

class Oppgave1 {
    public static void main(String[] args) {
        Person pers1 = new Person("Ola", "Nordman", 1980);
        ArbTaker arbtaker1 = new ArbTaker(pers1, "007", 2010, 100000);
        showMessageDialog(null, toString());
        int valg;
        while((valg = ))
    }

    public String toString() {
        int aar = 5; //Dis one
        String output = "Fornavn: " + pers1.getFornavn() +
                        "\nEtternavn: " + pers1.getEtternavn() +
                        "\nFødselsår: " + pers1.getFodselsar() +
                        "\nSkatte trekk per måned: " + arbtaker1.SkatteTrekkManed() +
                        "\nBrutto lønn: " + arbtaker1.BruttoLonn() +
                        "\nSkatte trekk per år: " + arbtaker1.SkatteTrekkAr() +
                        "\nNavn: " + arbtaker1.Navn() +
                        "\nAlder: " + arbtaker1.Alder() +
                        "\nAntall år i bedriften: " + arbtaker1.AntallArIBedrift() +
                        "\nAr i bedrift mot " + aar + ": " + arbtaker1.ArIBedriftMot(aar);
        return output;
    }
}
