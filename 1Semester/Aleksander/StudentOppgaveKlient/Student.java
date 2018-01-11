class Student {

 private String navn;
 private int antOppg;

 public Student (String navn) {
  this.navn = navn;
  antOppg = 0;
 } // Lukker konstrukt�ren

 public String getNavn () {
  return navn;
 } // Lukker metoden

 public int getAntOppg () {
  return antOppg;
 } // Lukker metoden

 public void �kAntOppg (int �kning) {
  if (�kning < 0) {
	  throw new IllegalArgumentException ("Kan ikke v�re negativt...");
  } // Lukker if
  antOppg += �kning;
 } // Lukker metoden

 public String toString () {
  return "Navn er: " + navn + ". Studenten har f�tt " + antOppg + " oppgaver godkjent";
 } // Lukker metoden
} // Lukker classen