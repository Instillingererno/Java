import java.io.Serializable;
//transient for å unngå serializable
public class Year implements Serializable {
    int yearNr;
    Month[] months;
    public Year(int yearNr) {
        this.yearNr = yearNr;
        months = new Month[12];
    }
    public void regMonth(int monthNr, Month month) {
        if(monthNr != -1) {
            months[monthNr] = month;
        }
    }
}
