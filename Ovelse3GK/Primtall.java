//Oppgave 4.14.5 s.144 21-08-2017

import static javax.swing.JOptionPane.*;

class Primtall {
	
	public static void main (String[] args) {
		
		boolean fortsett = true;
		boolean primtall = true;
		
		String input = "";
		int tall = 0;
		
		while(fortsett == true) {
			
			primtall = true;
			
			input = showInputDialog("Skriv tall å sjekke: ");
			tall = Integer.parseInt(input);
			for(int i = tall-1; i > 1; i--) {
				if(tall % i == 0) {
					primtall = false;
				}
			}
			if(tall == 1) {
				primtall = false;
			}
			if (primtall == true) {
				showMessageDialog(null, "Tallet er et primtall");
			} else {
				showMessageDialog(null, "Tallet er ikke et primtall");
			}
		}	
	}
}

