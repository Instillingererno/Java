import java.util.ArrayList;

public class Rom {
    private Integer romNr;
    private Integer kapasitet;
    private ArrayList<Reservasjon> reservasjoner = new ArrayList<>();

    public Rom(int romNr, int kapasitet) {
        this.romNr = romNr;
        this.kapasitet = kapasitet;
    }

    public int getRomNr() {
        return romNr;
    }

    public int getKapasitet() { return kapasitet; }

    public ArrayList<Reservasjon> getReservasjoner() {
        return reservasjoner;
    }

    public static void main(String[] args) {
        Kunde k = new Kunde("Anne Hansen", "12345678");
        Rom r1 = new Rom(101, 10);
        Tidspunkt t1 = new Tidspunkt(201801121230L); // 12:30 12/01/2018
        Tidspunkt t2 = new Tidspunkt(201801121400L); // 14:00 12/01/2018
        Tidspunkt t3 = new Tidspunkt(201801141230L); // 12:30 14/01/2018
        Tidspunkt t4 = new Tidspunkt(201801141400L); // 14:00 14/01/2018

        System.out.println("Antall tester: 2");

        r1.getReservasjoner().add(new Reservasjon(t1, t3, k));
        if(r1.getReservasjoner().get(0).overlapp(t2,t4)) {
            System.out.println("Overlapp: Test 1 vellykket");
        }
        r1.getReservasjoner().add(new Reservasjon(t2,t4, k));
        if(r1.getReservasjoner().get(1).toString().equals("Kunde: Anne Hansen, tlf: 12345678, fra 12-01-2018 kl 1400, til 14-01-2018 kl 1400")) {
            System.out.println("Overlapp: Test 2 vellykket");
        }

    }
}
