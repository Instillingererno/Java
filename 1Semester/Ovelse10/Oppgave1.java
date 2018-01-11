//Oppgave 12.10.1 s.417 13-10-2017

import static javax.swing.JOptionPane.*;

class Student {
    private String navn; // entydig(!)
    private int antOppg; //Holder orden på hvor mange av de oppgavene studenten har levert inn, som er godkjent.

    public Student(String navn) {
        this.navn = navn;
        this.antOppg = 0;
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

    public Oppgaveoversikt(Student[] input) {
        if(input == null) {
            this.studenter = new Student[0];
        } else {
            this.antStud = input.length;
            this.studenter = new Student[input.length];
            for(int i = 0; i < input.length; i++) {
                this.studenter[i] = input[i];
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
    public void regNyStudent(String input) {
        Student[] temp = new Student[this.studenter.length + 1];
        for(int i = 0; i < this.antStud; i++) {
            temp[i] = this.studenter[i];
        }
        this.antStud++;
        temp[this.antStud - 1] = new Student(input);
        this.studenter = temp;
    }
    public void okOppgForStudent(String navn, int okning) {
        int student = -1;
        for(int i = 0; i < this.studenter.length; i++) {
            if(this.studenter[i].getNavn().equals(navn)) {
                student = i;
            }
        }
        if(student != -1) {
            this.studenter[student].okAntOppg(okning);
            showMessageDialog(null, "Godkjente oppgaver for " + navn + " er blitt økt med " + okning);
        } else {
            showMessageDialog(null, "Ingen student ved det oppgitte navnet: " + navn);
        }
    }
    public String toString() {
        String output = "";
        for(int i = 0; i < this.antStud; i++) {
            output += this.studenter[i].toString() + "\n";
        }
        return output;
    }
    public String[] getNavn() {
        String[] output = new String[this.studenter.length];
        for(int i = 0; i < output.length; i++) {
            output[i] = this.studenter[i].getNavn();
        }
        return output;
    }
    public int lesValg() {
        String[] meny = {"Reg ny student", "Øk oppgaver for en student"};
        int valg = showOptionDialog(null, this.toString(), "Student oversikt", YES_NO_OPTION, PLAIN_MESSAGE, null, meny, meny[0]);
        return valg;
    }
    public void endring(int input) {
        switch(input) {
            case 0:
                try {
                    String navn1 = showInputDialog("Navn til ny student?: ");
                    this.regNyStudent(navn1);
                }
                catch(Exception e) {
                    showMessageDialog(null, "Noe gikk galt, prøv igjen eller gi denne meldingen til noen som kan noe: " + e);
                }
                break;
            case 1:
                try {
                    String[] valg = this.getNavn();

                    String navn2 = showInputDialog(null, "Hvilken student å øke oppgaver til", "Student oppgave økning", QUESTION_MESSAGE, null, valg, valg[0]).toString();
                    int okning = Integer.parseInt(showInputDialog("Økning: "));
                    this.okOppgForStudent(navn2, okning);
                }
                catch(NumberFormatException e) {
                    showMessageDialog(null, "Skatteprosent kan ikke være tekst");
                }
                catch(Exception e) {
                    showMessageDialog(null, "Noe gikk galt, prøv igjen " + e);
                }
                break;
        }
    }
}

class Oppgave1 {
    public static void main(String[] args) {

        Student[] test = new Student[] {new Student("Test1"), new Student("Test2")};

        Oppgaveoversikt oversikt = new Oppgaveoversikt(test);

        int valg;
        while((valg = oversikt.lesValg()) != CLOSED_OPTION) {
            oversikt.endring(valg);
        }

    }
}
