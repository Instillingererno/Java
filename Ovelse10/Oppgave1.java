2//Oppgave 12.10.1 s.417 13-10-2017

import static javax.swing.JOptionPane.*;

class Student {
    private String navn; // entydig(!)
    private int antOppg; //Holder orden på hvor mange av de oppgavene studenten har levert inn, som er godkjent.

    public Student(String navn) {
        this.navn = navn;
    }
    public String getNavn() {
        return this.navn;
    }
    public int getAntOppg() {
        return this.antOppg;
    }
    public void okAntOppg(int okning) {
        this.antOppg += okning;
    }
    public String toString() {
        return this.navn + " har fullført: " + this.antOppg + " oppgaver!";
    }
}

class Oppgaveoversikt {
    private Student[] studenter; //tabellen opprettes i konstruktøreturn
    private int antStud = 0; //økes med 1 for hver ny student

    public void Oppgaveoversikt(Student[] input) {
        if(input != null) {
            this.antStud = input.length;
            this.studenter = new Student[this.antStud];
            for(int i = 0; i < this.antStud; i++) {
                this.studenter[i] = input[i];
                input = null;
            }
        }
    }
    public int getAntallStudenter() {
        return antStud;
    }
    public String getAntallOppgaverForStudent(String navn) {
        int student = -1;
        for(int i = 0; i < this.studenter.length; i++) {
            if(this.studenter[i].getNavn().equals(navn)) {
                student = i;
            }
        }
        if(student != -1) {
            return this.studenter[student].toString();
        } else {
            return "Ingen student ved det oppgitte navnet: " + navn;
        }
    }
    public void regNyStudent(Student input) {
        this.antStud++;

    }
    public void okOppgForStudent(String navn) {

    }
}

class Oppgave1 {
    public static void main(String[] args) {
        Student test1 = new Student("Ola Normann");
        String valg;
        while((valg = showInputDialog(null, test1.toString() + "\nøk fullførte oppgaver?")) != null) {
            try {
                test1.okAntOppg(Integer.parseInt(valg));
            } catch(NumberFormatException e) {
                showMessageDialog(null, "Kan ikke øke med en string");
            }
        }

    }
}
