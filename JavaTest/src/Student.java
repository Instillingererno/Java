class Student {
    private String studNr;
    private String navn;
    public Student(String studNr, String navn) {
        this.studNr = studNr;
        this.navn = navn;
    }
    public String getStudNr() {
        return studNr;
    }
    public String getNavn() {
        return navn;
    }
    public void setStudNr(String studNr) {
        this.studNr = studNr;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }
    public String toString() {
        return studNr + ": " + navn;
    }
}
class Sorter {
    static void sorterStud(Student stud1, Student stud2) {
        if (stud1.getNavn().compareTo(stud2.getNavn()) > 0) {
            Student temp = stud1;
            stud1 = stud2;
            stud2 = temp;
        }
    }
}
class Oppgave {
    public static void main(String[] main) {
        Student s1 = new Student("1234", "Ole Hansen");
        Student s2 = new Student("7888", "Anne Vik");
        Sorter.sorterStud(s1, s2);
        System.out.println(s1);
        System.out.println(s2);
    }
}