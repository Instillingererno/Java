//Oppgave 2.13.2 s.83 21-08-2017

import static javax.swing.JOptionPane.*;

class TilSek {
	public static void main (String[] args) {
		
		double timer = Double.parseDouble(showInputDialog("Timer: "));
		double minutt = Double.parseDouble(showInputDialog("Minutt: "));
		double sekund = Double.parseDouble(showInputDialog("Sekund: "));
		
		double totalSek = sekund + minutt * 60 + timer * 60 * 60;
		
		showMessageDialog(null, "Totalt antall sekunder for oppgitte timer, minutter og sekunder er: " + totalSek);
		 
		
	}

}