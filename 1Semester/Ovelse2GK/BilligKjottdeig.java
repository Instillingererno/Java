//Oppgave 2.13.4 s.113 21-08-2017

import static javax.swing.JOptionPane.*;

class BilligKjottdeig {
	
	public static void main (String[] args) {
		
		double prisA = 35.9;
		double vektA = 450;
		double effektA = vektA / prisA;
		
		double prisB = 39.5;
		double vektB = 500;
		double effektB = vektB / prisB;
		
		if (effektA > effektB) {
			showMessageDialog(null, "Kjøttdeigen fra merke A er billigst per enhet vekt");
		}else{
			showMessageDialog(null, "Kjøttdeigen fra merke B er billigst per enhet vekt");
		}
		
	}
	
}