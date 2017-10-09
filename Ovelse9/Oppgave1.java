//Oppgave 11.10.1 s.386 09-10-2017

class Person {
    private final String fornavn;
    private final String etternavn;
    private final short fodselsar;
    Person(String fnavn, String enavn, short fodsar) {
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
    public short getFodselsar() {
        return this.fodselsar;
    }
}

class ArbTaker {
    Person personalia;
    short arbtakernr;
    short ansettelsesar;
    int manedslonn;
    byte skatteprosent;
    ArbTaker(Person person, short arbnr, short ansar, int manedslonn, byte skatpro) {
        this.personalia = person;
        this.arbtakernr = arbnr;
        this.ansettelsesar = ansar;
        this.manedslonn = manedslonn;
        this.skatteprosent = skatpro;
    }
    public getPersonalia() {
        return personalia;
    }
    public getArbtakernr() {
        return arbtakernr;
    }
    public getAnsettelsesar() {
        return ansettelsesar;
    }
    public getManedslonn() {
        return manedslonn;
    }
    public getSkatteprosent() {
        return skatteprosent;
    }
    public setManedslonn(int lonn) {
        this.manedslonn = lonn;
    }
    public SkatteTrekkManed() {
        //ay
    }
    public BruttoLonn() {
        //ay
    }
    public SkatteTrekkAr() {
        //ay
    }
    public Navn() {
        //ay
    }
    public Alder() {
        //ay
    }
    public AntallArIBedrift() {
        //ay
    }
    public ArIBedriftMot(short input) {
        //ay
    }
}
