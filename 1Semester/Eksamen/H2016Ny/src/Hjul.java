import java.io.*;

public class Hjul implements Serializable {
    private final String merke;
    private final String dimensjon;
    private final String dekkType;

    public Hjul(String merke, String dimensjon, String dekkType) {
        this.merke = merke;
        this.dimensjon = dimensjon;
        this.dekkType = dekkType;
    }

    // Get metoder
    public String getMerke() {
        return merke;
    }
    public String dimensjon() {
        return dimensjon;
    }
    public String dekkType() {
        return dekkType;
    }

    public String toString() {
        return "Merke: " + merke + ", Dimensjon: " + dimensjon + ", Dekktype: " + dekkType;
    }
}
