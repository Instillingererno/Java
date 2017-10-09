//Oppgave 8.16.1 s.283 30-09-2017
import static javax.swing.JOptionPane.*;

class NyString {
    private String data = "";
    NyString(String data) {
        this.data = data;
    }
    public String Forkorting() {
        String[] ord = this.data.split(" ");
        String temp = "";
        for(int i = 0; i < ord.length; i++) {
            temp += ord[i].charAt(0);
        }
        return temp;
    }
    public String Fjerning(String input) {
        char data = input.charAt(0);
        String temp = this.data;
        while(temp.indexOf(data) != -1) {
            temp = temp.substring(0,temp.indexOf(data)) + temp.substring(temp.indexOf(data) + 1);
        }
        return temp;
    }
}

class Oppgave1 {
    public static void main(String[] args) {
        while(true) {
            String valg0 = showInputDialog("Tekst å utføre operasjoner på:");
            NyString test = new NyString(valg0);
            String valg1 = showInputDialog("Bokstav å fjerne fra tekst: ");
            String output = "Forkorting " + test.Forkorting() + "\nFjerning " + test.Fjerning(valg1);
            showMessageDialog(null, output);
        }
    }
}
