public class OppgaveOversikt_Array {
    private Student[] studenter = new Student[5];
    private int antStud = 0;

    public boolean regNyStudent(String navn) {
        for(Student i : studenter) if(i != null) if(i.getNavn().equals(navn)) return false;
        if(antStud == studenter.length) utvidTabell();
        studenter[antStud] = new Student(navn);
        antStud++;
        return true;
    }
    public int finnAntStud() {
        return antStud;
    }
    public int finnAntOppgaver(String navn) {
        for(Student i : studenter) if(i != null) if(i.getNavn().equals(navn)) return i.getAntOppg();
        return -1;
    }
    public boolean økAntOppg(String navn, int input) {
        for(Student i : studenter) if(i != null) if(i.getNavn().equals(navn)) {
            i.setAntOppg(i.getAntOppg() + input);
            return true;
        }
        return false;
    }
    public String[] finnAlleNavn() {
        String[] output = new String[antStud];
        for(int i = 0; i < antStud; i++) {
            output[i] = studenter[i].getNavn();
        }
        return output;
    }

    public String toString() {
        String output = "";
        for(int i = 0; i < antStud; i++) {
            output += studenter[i].toString() + "\n";
        }
        return output;
    }
    private void utvidTabell() {
        Student[] nyTab = new Student[studenter.length + 5];
        for(int i = 0; i < antStud; i++) nyTab[i] = studenter[i];
        studenter = nyTab;
    }
}
