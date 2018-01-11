import java.time.Year;

public class Restaurant {
    String navn;
    int etabl;
    Bord bord;

    public Restaurant(String navn, int etabl, int antallBord) {
        this.navn = navn;
        this.etabl = etabl;
        this.bord = new Bord(antallBord);
    }

    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getEtabl() {
        return etabl;
    }

    public int getAlder() {
        return (Year.now().getValue() - etabl);
    }

}
