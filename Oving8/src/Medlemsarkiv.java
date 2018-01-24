import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Medlemsarkiv {
    private ArrayList<BonusMedlem> medlemmer = new ArrayList<>();

    public int nyMedlem(Personalia pers, LocalDate innmeldt) {
        int medlNr = finnLedigNr();
        medlemmer.add(new BasicMedlem(medlNr, pers, innmeldt));
        return medlNr;
    }
    public int finnPoeng(int medlNr, String passord) {
        for(BonusMedlem i : medlemmer) if(i.getMedlnr() == medlNr && i.okPassord(passord)) return i.getPoeng();
        return -1;
    }
    public boolean registrerPoeng(int medlNr, int poeng) {
        for(BonusMedlem i : medlemmer) if(i.getMedlnr() == medlNr) {
            i.registrerPoeng(poeng);
            return true;
        }
        return false;
    }
    public void sjekkMedlemmer() {
        for(int j = 0; j < medlemmer.size(); j++) {
            BonusMedlem i = medlemmer.get(j);
            int poeng = i.finnKvalPoeng(LocalDate.now());
            if(poeng >= 7500 && !(i instanceof GullMedlem)) {
                medlemmer.set(j,new GullMedlem(i.getMedlnr(), i.getPers(), i.getInnmeldtDato(), i.getPoeng()));
            } else if(poeng >= 2500 && i instanceof BasicMedlem) {
                medlemmer.set(j,new SoelvMedlem(i.getMedlnr(), i.getPers(), i.getInnmeldtDato(), i.getPoeng()));
            }
        }
    }
    private int finnLedigNr() {
        Random random = new Random();
        int medlNr;
        while(true) {
            boolean fortsett = false;
            medlNr = random.nextInt();
            for(BonusMedlem i : medlemmer) if(i.getMedlnr() == medlNr) fortsett = true;
            if(medlNr <= 0 || medlNr >= 100) fortsett = true;
            if(!fortsett) return medlNr;
        }
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for(BonusMedlem i : medlemmer) {
            output.append(
                    "\n" + i.getMedlnr() + ": " +
                            i.getPers().getFornavn() + " " + i.getPers().getEtternavn() + ": " +
                            i.getClass() + "; poeng: " + i.getPoeng()
            );
        }
        return output.toString();
    }
}
