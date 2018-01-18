public class MellomSteg {
    private String tidFra;
    private String tidTil;
    private String kunde;
    public MellomSteg(Reservasjon res) {
        this.tidFra = res.fraTid.toString();
        this.tidTil = res.getTilTid().toString();
        this.kunde = res.getKunde().toString();
    }

    public String getKunde() {
        return kunde;
    }

    public String getTidFra() {
        return tidFra;
    }

    public String getTidTil() {
        return tidTil;
    }
}
