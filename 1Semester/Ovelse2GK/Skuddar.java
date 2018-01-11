//Oppgave 3.12.3 s.113 21-08-2017
//Husk å lage aktivitetsdiagram og!!

import static javax.swing.JOptionPane.*;

class SkuddAr {
	
	public static void main (String[] args) {
		
		while(true) {
 		int ar = Integer.parseInt(showInputDialog("Årstall: "));
		boolean skuddar = false;
		
		if(ar % 100 == 0 && ar % 400 == 0 || ar % 4 == 0 && ar % 100 != 0) {
			skuddar = true;
		}else{
			skuddar = false;
		}
		
		if(skuddar == true) {
			showMessageDialog(null, "Året er et skuddår");
		} else {
			showMessageDialog(null, "Året er ikke et skuddår");
		}
		}
		
	}
	
}