//Oppgave 9.11.2 s.314 22-09-2017

import java.util.Arrays;
import java.util.stream.*;
import static javax.swing.JOptionPane.*;

class Temperaturer {
    public double[][] temps = {
        {1,2,3},
        {4,5,6},
        {7,8,9}
    };
    public Temperaturer(double[][] input) {
        if(input != null) {
            this.temps = input;
        }
    }
    public double[] gjennomsnittForHverDag() {
        double[] svar = new double[temps.length];
        for(int i = 0; i < temps.length; i++) {
            int acc = 0;
            for(int j = 0; j < this.temps[i].length; j++) {
                acc += this.temps[i][j];
            }
            svar[i] = acc / this.temps[i].length;
        }
        return svar;
    }
    public double[] gjennomsnittForHverTime(int dag) {
        double[] svar = new double[this.temps[dag].length];
        for(int i = 0; i < this.temps[dag].length; i++) {
            svar[i] = this.temps[dag][i];
        }
        return svar;
    }
    public double gjennomsnittForHeleManeden() {
        double[] temp = this.gjennomsnittForHverDag();
        double acc = 0;
        for(int i = 0; i < temp.length; i++) {
            acc += temp[i];
        }
        return acc / temp.length;
    }
    public int gruppertGjennomsnitt(int gruppe) {
        int lavGrense, hoyGrense;
        int acc = 0;
        double[] temp = this.gjennomsnittForHverDag();
        switch(gruppe) {
            case 1:
                lavGrense = -375;
                hoyGrense = -5;
                break;
            case 2:
                lavGrense = -5;
                hoyGrense = 0;
                break;
            case 3:
                lavGrense = 0;
                hoyGrense = 5;
                break;
            case 4:
                lavGrense = 5;
                hoyGrense = 10;
                break;
            case 5:
                lavGrense = 10;
                hoyGrense = Integer.MAX_VALUE;
                break;
            default:
                return -1;
        }
        for(int i = 0; i < temp.length; i++) {
            if(temp[i] > lavGrense && temp[i] < hoyGrense) {
                acc++;
            }
        }
        return acc;
    }
}
class Oppgave1 {
    public static void main(String[] args) {
        Temperaturer janujuni = new Temperaturer(null);
        int[] intDag = IntStream.range(1,janujuni.temps.length + 1).toArray();
        String[] stringDag = Arrays.toString(intDag).split("[\\[\\]]")[1].split(", ");
        int valgDag = showOptionDialog(null, "Velg dag å regne time gjennomsnitt av", "Velg dag", DEFAULT_OPTION, PLAIN_MESSAGE, null, stringDag, stringDag[0]);

        String output = "Gjennomsnitt for hver dag i måneden: " + janujuni.gjennomsnittForHverDag() +
        "\nGjennomsnitt for hver time i dag: ";
    }
}
