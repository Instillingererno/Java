//Oppgave 2.13.1 s.83 21-08-2017

import static javax.swing.JOptionPane.*;

class TommerTilCent {
	public static void main(String[] args) {
		String tommerLest = showInputDialog("Tommer: ");

		double tommer = Double.parseDouble(tommerLest);

		double centimeter = tommer * 2.54;

		showMessageDialog(null, "Centimeter: " + centimeter);

	}
}
