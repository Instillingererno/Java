import java.io.Serializable;

public class Month implements Serializable {
    int monthNr;
    Day[] days;
    public Month(int monthNr, int days) {
        this.monthNr = monthNr;
        this.days = new Day[days];
    }
}
