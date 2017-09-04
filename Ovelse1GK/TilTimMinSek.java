//Oppgave 2.13.3 s.83 21-08-2017

import static javax.swing.JOptionPane.*;

class TilTimMinSek {
	public static void main (String[] args) {
		
		int sekundLest = Integer.parseInt(showInputDialog("Sekunder: "));
		
		int timer = sekundLest / (60 * 60);
		int minutt = sekundLest / 60 - timer * 60;
		int sekund = sekundLest - minutt * 60 - timer * 60 * 60;
		
		showMessageDialog(null, "Timer: " + timer + " Minutt: " + minutt + " Sekund: " + sekund);
	}
}