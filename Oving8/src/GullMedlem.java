import java.time.LocalDate;

public class GullMedlem extends BonusMedlem {
    public GullMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato, int startPoeng) {
        super(medlNr, innmeldtDato, pers);
        super.registrerPoeng(startPoeng);
    }

    @Override
    public void registrerPoeng(int poeng) {
        super.registrerPoeng((int) (poeng * 1.5));
    }
}
