
// Kommentarer fungerer likt som i javascript med // og /*  *info*  */

//HeiVerden.java SØ 2017-08-21

import static javax.swing.JOptionPane.*;
	
class HeiVerden{
	public static void main (String[] args) {
		/*System.out.println("Hei verden!");
		
		showMessageDialog(null, "Hei verden!");*/
		
		double lengde = 3;
		double bredde = 4;
		
		String lengdeLest = showInputDialog("Lengde: ");
		String breddeLest = showInputDialog("Bredde: ");
		
		lengde = Double.parseDouble(lengdeLest);
		bredde = Double.parseDouble(breddeLest);
		
		int iLengde = Integer.parseInt(lengdeLest);
		int iBredde = Integer.parseInt(breddeLest);
		
		double areal = lengde * bredde;
		//int areal = iLengde * iBredde;
		
		showMessageDialog(null, "Arealet er: " + areal + " m2");
		
	}
}