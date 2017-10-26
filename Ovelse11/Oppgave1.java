//Oppgave 16.13.3 s.549 25-10-2017

/*  Lag et program som vedlikeholder en konto ut fra følgende spesifikasjoner:

    Forrige saldo ligger på filen saldo.txt. Transaksjonene for siste periode ligger på en fil.
    Filen inneholder en linje pr.transaksjon. Hver linje inneholder en bokstav som sier hvilken type transaksjon, og et beløp.

    transaksjonskoder:
    U for uttak,
    I for innskudd.

    Etter at transaksjonsfilen er behandlet, må den nye saldoen legges på filen saldo.txt

    Enkelt-transaksjoner som fører til at koentoen går i minus, er i orden.
    Dersom netto-resultatet til slutt er slik at saldoen er negatic, skal imidlertid hele transaksjonsfilen forkastes.
    Ingen transaksjoner skal registreres i dette tilfellet.

    Eksempel: Startsaldo: kr 5460.7 Transaksjonsfilen har følgende innhold:
    U 450
    I 567.80
    I 4000.00
    U 500
*/

import java.io.*;
import static javax.swing.JOptionPane.*;
import java.util.Scanner;


class Filbehandler {
    String filnavn = "";
    boolean nylinje;
    FileReader leseFil;
    BufferedReader leser;
    FileWriter skriveTilFil;
    PrintWriter skriver;
    public Filbehandler(String filnavn, boolean nylinje) throws IOException {
        this.filnavn = filnavn;
        this.nylinje = nylinje;
    }
    public String lesFil() throws IOException {
        this.leseFil = new FileReader(this.filnavn);
        this.leser = new BufferedReader(this.leseFil);
        String stream = this.leser.readLine();
        String output = "";
        while(stream != null) { //null betyr filslutt
            output += (stream + "\n");
            stream = this.leser.readLine();
        }
        this.leser.close();
        return output;
    }
    public void skrivTilFil(String input) throws IOException {
        this.skriveTilFil = new FileWriter(this.filnavn, this.nylinje);
        this.skriver = new PrintWriter(new BufferedWriter(this.skriveTilFil));
        this.skriver.println(input);
        this.skriver.close();
    }
}
class SaldoBehandler extends Filbehandler {
    double saldo = 0;
    TransaksjonBehandler transaksjoner;
    public SaldoBehandler(String filnavn, boolean nylinje, TransaksjonBehandler input) throws IOException {
        super(filnavn, nylinje);
        this.saldo = Double.parseDouble(this.lesFil());
        this.transaksjoner = input;
        this.utforOperasjoner();
    }
    public void utforOperasjoner() throws IOException {
        double temp = this.saldo;
        for(int i = 0; i < this.transaksjoner.operasjoner.length; i++) {
            this.saldo += this.transaksjoner.operasjoner[i];
        }
        if(this.sjekk()) {
            showMessageDialog(null, "Operasjonene ble vellykket, ny saldo er " + this.saldo);
            this.skrivTilFil(Double.toString(this.saldo));
        } else {
            showMessageDialog(null, "Saldoen ble under null etter alle operajsonene som ikke tillates, saldo blir tilbakestilt til " + temp);
        }
    }
    public boolean sjekk() {
        if(this.saldo > 0) {
            return true;
        } else {
            return false;
        }
    }
}
class TransaksjonBehandler extends Filbehandler {
    String transaksjoner = "";
    double[] operasjoner;
    public TransaksjonBehandler(String filnavn, boolean nylinje) throws IOException {
        super(filnavn, nylinje);
        this.transaksjoner = this.lesFil();
        this.behandler();
    }
    public void behandler() {
        String[] temp = this.transaksjoner.split("\n");
        this.operasjoner = new double[temp.length];
        for(int i = 0; i < temp.length; i++) {
            if(temp[i].charAt(0) == 'I') {
                this.operasjoner[i] = Double.parseDouble(temp[i].substring(2));
            } else if(temp[i].charAt(0) == 'U') {
                this.operasjoner[i] = -1 * Double.parseDouble(temp[i].substring(2));
            } else {
                showMessageDialog(null, "Ugyldig operasjon");
            }
        }
    }
}

class Oppgave1 {
    public static void main(String[] args) throws IOException {

        //System.out.println(new File(".").getAbsoluteFile());

        SaldoBehandler saldo = new SaldoBehandler("saldo.txt", false, new TransaksjonBehandler("transaksjoner.txt", false));

    }
}
