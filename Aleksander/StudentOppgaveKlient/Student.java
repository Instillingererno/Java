class Student {

 private String navn;
 private int antOppg;

 public Student (String navn) {
  this.navn = navn;
  antOppg = 0;
 } // Lukker konstruktøren

 public String getNavn () {
  return navn;
 } // Lukker metoden

 public int getAntOppg () {
  return antOppg;
 } // Lukker metoden

 public void økAntOppg (int økning) {
  if (økning < 0) {
	  throw new IllegalArgumentException ("Kan ikke være negativt...");
  } // Lukker if
  antOppg += økning;
 } // Lukker metoden

 public String toString () {
  return "Navn er: " + navn + ". Studenten har fått " + antOppg + " oppgaver godkjent";
 } // Lukker metoden
} // Lukker classen