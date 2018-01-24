import java.time.LocalDate;

public class BasicMedlem extends BonusMedlem {
    public BasicMedlem(int medlNr, Personalia pers, LocalDate innmeldtDato) {
        super(medlNr, innmeldtDato, pers);
    }
}
