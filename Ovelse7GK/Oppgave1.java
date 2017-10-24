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
    public void setDag(int linje, double[] data) {
        this.temps[linje] = data;
    }
    public String toString() {

        String tempvalue = "";
        for(int i = 0; i < this.temps.length; i++) {
            for(int j = 0; j < this.temps[i].length; j++) {
                tempvalue += "'" + this.temps[i][j] + "', ";
            }
            tempvalue += "\n";
        }
        return tempvalue;
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
            case 0:
                lavGrense = -375;
                hoyGrense = -5;
                break;
            case 1:
                lavGrense = -5;
                hoyGrense = 0;
                break;
            case 2:
                lavGrense = 0;
                hoyGrense = 5;
                break;
            case 3:
                lavGrense = 5;
                hoyGrense = 10;
                break;
            case 4:
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
        String[] dag = Arrays.toString(IntStream.range(1,janujuni.temps.length + 1).toArray()).split("[\\[\\]]")[1].split(", ");
        int valgDag = showOptionDialog(null, "Velg dag å regne time gjennomsnitt av", "Velg dag", DEFAULT_OPTION, PLAIN_MESSAGE, null, dag, dag[0]);
        String[] intervall = {"< 5","-5 - 0","0 - 5","5 - 10","> 10"};
        int valgIntervall = showOptionDialog(null, "Velg intervall å finne antall dager innenfor", "Velg intervall", DEFAULT_OPTION, PLAIN_MESSAGE, null, intervall, intervall[0]);

        String output = janujuni.toString() +
        "\nGjennomsnitt for hver dag i måneden: " + Arrays.toString(janujuni.gjennomsnittForHverDag()) +
        "\nGjennomsnitt for hver time i valgt dag: " + Arrays.toString(janujuni.gjennomsnittForHverTime(valgDag)) +
        "\nGjennomsnitt for hele måneden: " + janujuni.gjennomsnittForHeleManeden() +
        "\nAntall døgn med temperatur i det valgte intervallet " + janujuni.gruppertGjennomsnitt(valgIntervall);

        showMessageDialog(null, output);
    }
}
