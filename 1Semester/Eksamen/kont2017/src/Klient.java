import static javax.swing.JOptionPane.*;

class Klient {
	public static void main (String[] args){

	String[] muligheter = {"Nytt alfabet","Legg til ord", "Endre ord", "Skriv alfabet til fil", "Bokstaver ord","Sorter alfabetet", "Avslutt"};
		final int NYTT_ALFABET = 0;
		final int LEGG_TIL_ORD = 1;
		final int ENDRE_ORD = 2;
		final int SKRIV_TIL_FIL = 3;
		final int BOKSTAVER_ORD = 4;
		final int SORTER = 5;
		final int AVSLUTT = 6;

		int valg = showOptionDialog(null, "Velg", "Eksamen juni 2017", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter, muligheter[0]);

	Bokstaveringsalfabet ba = null;

		while (valg != AVSLUTT){
			switch (valg){
				case NYTT_ALFABET:
					// Bruker skal kunne velge metode (enten fra fil eller skrive inn ord direkte)
					String[] muligheter2 = {"Les fra fil", "Skriv inn manuelt"};
					int valg2 = showOptionDialog(null, "Velg hvordan alfabetet skal legges inn", "Eksamen juni 2017", YES_NO_OPTION, INFORMATION_MESSAGE, null, muligheter2, muligheter[0]);
					try{
						switch(valg2) {
						case 0:
							ba = new Bokstaveringsalfabet(showInputDialog(null,"Skriv inn navn på alfabetet"), showInputDialog(null,"Skriv inn filnavn"));
							showMessageDialog(null, "Alfabetet ble opprettet");
							break;
						case 1:
							ba = new Bokstaveringsalfabet(showInputDialog(null,"Skriv inn navn på alfabetet"), showInputDialog(null,"Skriv inn alfabetet; feks kan det se slik ut: Alfa-Beta-"), showInputDialog(null,"Skriv inn skilletegnet du brukte til alfabetet, hvis du brukte det oppgitte formatet skriv '-' her"));
							showMessageDialog(null, "Alfabetet ble opprettet");
							break;
						default: break;
						}
					} catch(Exception e) {
						showMessageDialog(null, "Noe gikk galt, prøv igjen");
					}

					break;
				case LEGG_TIL_ORD:
					break;
				case ENDRE_ORD:
					try{
						ba.endreOrd(showInputDialog(null, "Skriv inn ordet du vil endre til"));
					} catch(Exception e) {
						showMessageDialog(null, "Noe gikk galt, prøv igjen");
					}
					break;
				case SKRIV_TIL_FIL: // ikke en del av oppgaven
					break;
				case BOKSTAVER_ORD:
					try{
						String x = showInputDialog(null, "Skriv inn ord å endre");
						showMessageDialog(null, ba.konverter(x));
					} catch(Exception e) {
						showMessageDialog(null, "Noe gikk galt");
					}
					break;
				case SORTER:
					break;
				default: break;
			}
			valg = showOptionDialog(null, "Velg", "Eksamen juni 2017", YES_NO_OPTION,
					INFORMATION_MESSAGE, null, muligheter, muligheter[0]);
		}
	}
}