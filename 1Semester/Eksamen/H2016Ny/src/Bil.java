import java.io.*;

public class Bil implements Serializable {
    private int regnr;
    private Motor motor;
    private Hjul[] hjul = new Hjul[4]; // Antar alle bilene trenger fire hjul

    public Bil(int regnr, Motor motor, Hjul hjul) {
        this.regnr = regnr;
        this.motor = new Motor(motor.getMotornr(), motor.getGirkasse(), motor.getMotorType());
        for(Hjul i : this.hjul) i = hjul;
    }

    // Get metoder
    public int getRegnr() {
        return regnr;
    }
    public Motor getMotor() {
        return new Motor(motor.getMotornr(), motor.getGirkasse(), motor.getMotorType());
    }
    public Hjul[] getHjul() {
        return hjul;
    }

    public String toString() {
        return "Reg-nr: " + regnr +
                "\nMotor: " + motor.toString() +
                "\nHjul0: " + hjul[0].toString() +
                "\nHjul1: " + hjul[1].toString() +
                "\nHjul2: " + hjul[2].toString() +
                "\nHjul3: " + hjul[3].toString();
    }

    public boolean equals(Bil x) {
        if(this == x || regnr == x.getRegnr() && motor.getMotornr() == x.getMotor().getMotornr()) return true;
        return false;
    }
}
