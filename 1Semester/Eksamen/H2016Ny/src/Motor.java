import java.io.*;

public class Motor implements Serializable {
    private final int motornr;
    private final String girkasse;
    private final String motorType;

    public Motor(int motornr, String girkasse, String motorType) {
        this.motornr = motornr;
        this.girkasse = girkasse;
        this.motorType = motorType;
    }

    // Get metoder
    public int getMotornr() {
        return motornr;
    }
    public String getGirkasse() {
        return girkasse;
    }
    public String getMotorType() {
        return motorType;
    }

    public String toString() {
        return "Motornr: " + motornr + ", Girkasse: " + girkasse + ", Motortype: " + motorType;
    }
}
