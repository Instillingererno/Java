import java.util.ArrayList;

public class OppgaveOversikt {
    private ArrayList<Student> studenter = new ArrayList<>();

    public boolean regNyStudent(String navn) {
        for(Student i : studenter) if(i != null) if(i.getNavn().equals(navn)) return false;
        studenter.add(new Student(navn));
        return true;
    }
    public int finnAntStud() {
        return studenter.size();
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
        String[] output = new String[studenter.size()];
        for(int i = 0; i < studenter.size(); i++) {
            output[i] = studenter.get(i).getNavn();
        }
        return output;
    }

    public String toString() {
        String output = "";
        for(int i = 0; i < studenter.size(); i++) {
            output += studenter.get(i).toString() + "\n";
        }
        return output;
    }
}
