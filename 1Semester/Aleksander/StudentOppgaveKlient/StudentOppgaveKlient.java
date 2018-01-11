import static javax.swing.JOptionPane.*;
import javax.swing.*;

class StudentOppgaveKlient {
	public static void main(String [] args) {

		Student[] EnGjeng = new Student[1];

		String regStudentLest = showInputDialog ("Skriv inn navnet på en student som skal registreres:");
		EnGjeng[0] = new Student (regStudentLest);

		Oppgaveoversikt klasse1 = new Oppgaveoversikt (EnGjeng);

		String[] ALTERNATIVER = {"Registrer ny student", "Bestemt Student - Ant Oppg",
		"Bestemt Student - Øk Oppg", "Skriv ut og Avslutt"};

		int REG_NY = 0;
		int FINN_OPPG = 1;
		int ØK_OPPG = 2;
		int SKRIV_UT = 3;

		while (true) {
			int valg = showOptionDialog (null, "Gjør et valg, nisse!", "Studenter", DEFAULT_OPTION,
			PLAIN_MESSAGE, null, ALTERNATIVER, ALTERNATIVER[0]);

			switch (valg) {
				case 0:
					String navn = showInputDialog ("Skriv inn navn på student");
					klasse1.regNyStud (navn);
					break;

				case 1:
					navn = showInputDialog ("Skriv inn navn på student");
					showMessageDialog (null, navn + " har gjort " + klasse1.getAntOppg (navn) + " oppgaver");
					break;

				case 2:
					navn = showInputDialog ("Skriv inn navn på student");
					String antOppgLest = showInputDialog ("Skriv inn antall oppgaver som skal registreres:");
					int antOppg = Integer.parseInt (antOppgLest);
					klasse1.økAntOppg (navn, antOppg);
					break;

				case 3:
					System.out.println (klasse1.toString());
					return;

				default:
				break;
			} // Lukker switch
		} // Lukker loop
	} // Lukker main
} // Lukker class