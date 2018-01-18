import java.util.ArrayList;

public class Senter {
    ArrayList<Rom> rom = new ArrayList<>();

    public boolean reserverRom(Tidspunkt fra, Tidspunkt til, int antallpersoner, String navn, String tlfnr) {
        for(Rom i : rom) if(i.getKapasitet() >= antallpersoner) {
            boolean overlapp = false;
            for(Reservasjon j : i.getReservasjoner()) {
                if(j.overlapp(fra, til)) {
                    overlapp = true;
                }
            }
            if(!overlapp) {
                i.getReservasjoner().add(new Reservasjon(fra, til, new Kunde(navn, tlfnr)));
                return true;
            }
        }
        return false;
    }

    public boolean regNyttRom(int romnummer, int kapasitet) {
        for(Rom i : rom) if(i.getRomNr() == romnummer) return false;
        rom.add(new Rom(romnummer, kapasitet));
        return true;
    }

    public ArrayList<Rom> getRom() {
        return rom;
    }

    public int getAntallRom() {
        return rom.size();
    }

    public Rom getRomFraIndeks(int indeks) {
        if(indeks < rom.size()) return rom.get(indeks);
        else return null;
    }

    public Rom getRomFraRomNr(int romNr) {
        for(Rom i : rom) if(i.getRomNr() == romNr) return i;
        return null;
    }

    public static void main(String[] args) {
        Senter ntnu = new Senter();
        System.out.println("Lager nytt senter");
        System.out.println("Registrerer nytt rom: romnr 101, kap 10");
        ntnu.regNyttRom(101,10);
        System.out.println("Registrerer nytt rom: romnr 102, kap 20");
        ntnu.regNyttRom(102,20);
        System.out.println("Registrerer nytt rom: romnr 103, kap 30");
        ntnu.regNyttRom(103,30);
        System.out.println("Registrerer nytt rom: romnr 104, kap 40");
        ntnu.regNyttRom(104,40);
        System.out.println("Prøver å lage ny reservering");
        ntnu.reserverRom(new Tidspunkt(201802171500L), new Tidspunkt(201802171700L), 5, "ASD", "123");
        System.out.println("Prøver å lage ny reservering");
        ntnu.reserverRom(new Tidspunkt(201802171500L), new Tidspunkt(201802171700L), 15, "ASD", "123");
        System.out.println("Prøver å lage ny reservering");
        ntnu.reserverRom(new Tidspunkt(201802171500L), new Tidspunkt(201802171700L), 25, "ASD", "123");

        String output = "";
        for(Rom i : ntnu.getRom()) {
            output += i.toString() + "\n";
            for(Reservasjon j : i.getReservasjoner()) {
                output += "     " + j.toString() + "\n";
            }
        }
        System.out.println(output);
    }
}
