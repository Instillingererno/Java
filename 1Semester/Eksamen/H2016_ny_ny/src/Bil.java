import java.io.*;

public class Bil implements Serializable {
    private int regnr;
    private Motor motor;
    private Hjul[] hjul = new Hjul[4];

    public Bil(int regnr, Motor motor, Hjul hjul) {
        this.regnr = regnr;
        this.motor = new Motor(motor.getMotornr(), motor.getGirkasse(), motor.getMotorType());
        for(Hjul i : this.hjul) i = hjul;
    }

    public int getRegnr() {
        return regnr;
    }
    public Motor getMotor() {
        return new Motor(motor.getMotornr(), motor.getGirkasse(), motor.getMotorType());
    }
    public Hjul[] getHjul() {
        return hjul;
    }
    public Hjul getHjul(int i) {
        return hjul[i];
    }

    public String toString() {
        String temp = "";
        for(Hjul i : hjul) temp += i.toString();
        return "Regnr: " + regnr + "" +
                "\nMotor: " + motor.toString() +
                "\nHjul: " + temp;
    }

    public boolean equals(Bil x) {
        if(this == x) return true;
        if(this.regnr == x.regnr && this.motor.getMotornr() == x.getMotor().getMotornr()) return true;
        return false;
    }
}
