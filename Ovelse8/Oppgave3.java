//Oppgave 8.16.3 s.283 09-10-2017

import static javax.swing.JOptionPane.*;
import java.util.regex.Pattern;
import java.util.Arrays;

class behandler {
    String data = "";
    String tegn = ".,!'-?";
    behandler(String data) {
        this.data = data;
    }
    public int antallOrd() {
        return this.data.split(" ").length;
    }
    public double gjennomsnittOrdlengde() {
        String temp = this.data;
        for(int i = 0; i < this.tegn.length(); i++) {
            while(temp.indexOf(this.tegn.charAt(i)) != -1) {
                temp = temp.substring(0,temp.indexOf(this.tegn.charAt(i))) + temp.substring(temp.indexOf(this.tegn.charAt(i)) + 1);
            }
        }
        String[] tempArray = temp.split(" ");
        double output = 0;
        for(int i = 0; i < tempArray.length; i++) {
            output += tempArray[i].length();
        }
        return output / tempArray.length;
    }
    public double gjennomsnittOrdPerPeriode() {
        String tempString = this.data;
        String[] tempArray = tempString.split("[.,!'-?]");
        double tempInt = 0;
        for(int i = 0; i < tempArray.length; i++) {
            int modifier = 0;
            String[] temp = tempArray[i].split(" ");
            for(int j = 0; j < temp.length; j++) {
                if(temp[j].length() > 1) {
                    tempInt += 1;
                }
            }
        }
        return tempInt / tempArray.length;
    }
    public String ordSkifte(String ord, String skift) {
        String[] tempArray = this.data.split(" ");
        String output = "";
        for(int i = 0; i < tempArray.length; i++) {
            if(tempArray[i].length() == ord.length() && tempArray[i].equals(ord)) {
                tempArray[i] = skift;
            } else if(tempArray[i].substring(0,tempArray[i].length()-1).equals(ord) && this.tegn.contains(tempArray[i].substring(tempArray[i].length()-1))) {
                tempArray[i] = skift + tempArray[i].substring(tempArray[i].length()-1);
            }
            output += tempArray[i] + " ";
        }
        return output;
    }
    public String toString() {
        return this.data;
    }
    public String toStringStoreBokstaver() {
        String tempString = this.data;
        return tempString.toUpperCase();
    }
}
class Oppgave3 {
    public static void main(String[] args) {
        behandler tekst = new behandler(showInputDialog("Skriv tekst å drive tekstbehandleren på:"));
        String ord = showInputDialog("Ord å bytte fra:");
        String skift = showInputDialog("Ord å bytte til:");
        String output = "Antall ord: " + tekst.antallOrd() +
                        "\nGjennomsnittlig ordlengde: " + tekst.gjennomsnittOrdlengde() +
                        "\nGjennomsnittlig antall ord per periode: " + tekst.gjennomsnittOrdPerPeriode() +
                        "\nSkiftet ut ord: " + tekst.ordSkifte(ord, skift) +
                        "\nReturne teksten: " + tekst.toString() +
                        "\nReturnere store bokstaver: " + tekst.toStringStoreBokstaver();
        showMessageDialog(null, output);
    }
}
