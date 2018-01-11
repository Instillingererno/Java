//Oppgave 4.14.1 s.143 21-08-2017

import static javax.swing.JOptionPane.*;

class GangeTabell {	
	public static void main (String[] args) {
		int minste = Integer.parseInt(showInputDialog("Minste: "));
		int hoyeste = Integer.parseInt(showInputDialog("Høyeste: "));
		
		for(int i = minste; i <= hoyeste; i++) {
		
			System.out.println(i + "-gangen");
			
			for(int j = 1; j <= 10; j++) {
				System.out.println(i + " x " + j + " = " + i * j);
			}
		}
	}
} 