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
        if(input != null) {
            this.studenter = input;
        } else {
            this.studenter = new Student[0];
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
    public int lesValg() {
        String[] meny = {"Reg ny student", "Øk oppgaver for en student"};
        int valg = showOptionDialog(null, this.toString(), "Student oversikt", YES_NO_OPTION, PLAIN_MESSAGE, null, meny, meny[0]);
        return valg;
    }
    public void endring(int input) {
        switch(input) {
            case 0:
                //try {
                    String navn1 = showInputDialog("Navn til ny student?: ");
                    this.regNyStudent(navn1);
                /*}
                catch(Exception e) {
                    showMessageDialog(null, "Noe gikk galt, prøv igjen eller gi denne meldingen til noen som kan noe: " + e);
                }*/
                break;
            case 1:
                try {
                    String navn2 = showInputDialog("Navnet til student å øke godkjente oppgaver til?: ");
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

        Student test1 = new Student("Test1");
        System.out.println(test1.toString());
        Student test2 = new Student("Test2");
        System.out.println(test2.toString());
        Student[] test3 = new Student[] {test1,test2};
        System.out.println("Det fungerte");
        Oppgaveoversikt test4 = new Oppgaveoversikt(test3);
        System.out.println(test4.toString());
        System.out.println("her skal test4.toString() være, er den det");
        String output = "";
        for(int i = 0; i < 2; i++) {
            output += test3[i].toString() + "\n";
        }
        System.out.println(output);

        Oppgaveoversikt oversikt = new Oppgaveoversikt(null);

        //System.out.println((new Student("roger")).toString());

        //System.out.println((new Oppgaveoversikt(new Student[] {new Student("roger"), new Student("woops"), new Student("test")})).toString());

        int valg;
        while((valg = oversikt.lesValg()) != CLOSED_OPTION) {
            oversikt.endring(valg);
        }

    }
}
