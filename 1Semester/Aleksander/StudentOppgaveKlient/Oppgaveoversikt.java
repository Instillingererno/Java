class Oppgaveoversikt {
	private Student[] studenter;
	private int antStud = 0;

	public Oppgaveoversikt (Student [] studenter) {
		int antFolk = studenter.length;
		this.studenter = new Student[antFolk];
		for (int i = 0; i < antFolk; i++) {
			this.studenter[i] = studenter[i];
		} // Lukker for loop
		antStud += antFolk;
	} // Lukker metoden

	public int getAntallStudenter() {
		return antStud;
	} // Lukker metoden

	public boolean regNyStud (String navn) {
		if (navn == null) {
			throw new IllegalArgumentException ("Kan ikke være navneløs");
		} // Lukker if
		if (antStud == studenter.length) {
			utvidTabell();
		} // Lukker if
		studenter[antStud] = new Student (navn);
		antStud++;
		return true;
	} // Lukker metoden

	private void utvidTabell () {
		Student[] nyTab = new Student[studenter.length + 2];
		for (int i = 0; i < antStud; i++) {
			nyTab[i] = studenter[i];
		} // Lukker for loop
		studenter = nyTab;
	} // Lukker metoden

	public int getAntOppg (String navn) {
		for (int i = 0; i < antStud; i++) {
			if (studenter[i].getNavn().equals (navn)) {
				return studenter[i].getAntOppg();
			} // Lukker if
		} // Lukker for loop
		return -1;
	} // Lukker metoden

	public void økAntOppg (String navn, int tall) {
		for (int i = 0; i < antStud; i++) {
			if (studenter[i].getNavn().equals (navn)) {
				studenter[i].økAntOppg (tall);
			} // Lukker if
		} // Lukker for
	} // Lukker metoden

	public String getAlleNavn () {
		String utskrift = "";
		for (int i = 0; i < antStud; i++) {
			utskrift += studenter[i].toString() + "\n";
		} // Lukker for loop
		return utskrift;
	} // Lukker metoden

	public String toString () {
		String res = "";
		res += "Antall studenter registrert: " + antStud + "\n";
		res += getAlleNavn();
		return res;
	} // Lukker metoden
} // Lukker classen
