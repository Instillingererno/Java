public class Idrettsgren {
    String navn;
    public Ovelse[] ovelser;

    public Idrettsgren(String navn, int antallOvelser) {
        this.navn = navn;
        this.ovelser = new Ovelse[antallOvelser];
    }
    public Idrettsgren(String navn, Ovelse[] ovelser) {
        this.navn = navn;
        this.ovelser = ovelser;
    }

    public String toString() {
        String temp = navn;
        temp += "\nMaks øvelser " + ovelser.length;
        if(ovelser != null) {
            for(Ovelse i : ovelser) {
                if(i != null) {
                    temp += "\n" + i.toString();
                }
            }
        } else {
            temp += "\nIngen øvelser enda";
        }
        return temp;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntallOvelser() {
        return ovelser.length;
    }

    public boolean regOvelse(Ovelse x) {
        for(Ovelse i : ovelser) {
            if(i != null) {
                if(i.compareTo(x)) return false;
            }
        }
        for(int i = 0; i < ovelser.length; i++) {
            if(ovelser[i] == null) {
                ovelser[i] = x;
                return true;
            }
        }
        return false;
    }
}
