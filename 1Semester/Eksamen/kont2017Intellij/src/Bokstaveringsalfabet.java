import java.io.*;

class Bokstaveringsalfabet {
    String navn;
    String[] alfabet;

    public Bokstaveringsalfabet(String navn, String alfabet, String seperator) {
        this.navn = navn;
        this.alfabet = alfabet.split(seperator);
    }
    public Bokstaveringsalfabet(String navn, String filnavn) throws IOException {
        this.navn = navn;
        String[] temp = lesAlfabetFraFil(filnavn).split("\n");
        this.alfabet = temp[1].split(temp[0]);
    }
    public String toString() {
        String temp = "";
        for(String i : alfabet) temp += i + " ";
        return temp;
    }
    public boolean regNyOrd(String ord) { // Returnerer true hvis ordet ble lagt til
        for(String i : alfabet) { //Sjekker i alfabet om ordet allerede eksisterer
            if(i.toUpperCase().equals(ord.toUpperCase())) {
                return false;
            }
        }

        String[] temp = new String[alfabet.length + 1];

        for(int i = 0; i < alfabet.length; i++) {
            temp[i] = alfabet[i];
        }

        temp[temp.length- 1 ] = ord;
        return true;
    }
    public boolean endreOrd(String endring) {
        for(String i : alfabet) {
            if(endring.substring(0,1).equals(i.substring(0,1))) {
                i = endring;
                return true;
            }
        }
        return false;
    }
    public String[] sorter() {
        boolean sortert = false;
        while(!sortert) {
            sortert = true;
            for(int i = 0; i < alfabet.length-1; i++) {
                if(alfabet[i].substring(0,1).compareTo(alfabet[i+1].substring(0,1)) > 0) {
                    swap(alfabet, i, i+1);
                    sortert = false;
                }
            }
        }
        return alfabet;
    }
    private void swap(String[] i, int x, int y) {
        String temp = i[x];
        i[x] = i[y];
        i[y] = temp;
    }
    public String konverter(String input) {
        String temp = "";
        boolean funnet = false;
        for(int i = 0; i < input.length(); i++) {
            funnet = false;
            for(String j : alfabet) {
                if(input.substring(i,i+1).equals(j.substring(0,1))) {
                    temp += (i < input.length()-1) ? j + " " : j;
                    funnet = true;
                }
            }
            if(!funnet) temp += "Ukjent ";
        }
        return temp;
    }
    public String alfabetTilFil(String filnavn, String skilleTegn) throws IOException {
        String temp = "";
        PrintWriter skriver = new PrintWriter(new BufferedWriter(new FileWriter(filnavn, true)));
        skriver.println(skilleTegn);
        for(String i : alfabet) temp += i + skilleTegn;
        skriver.println(temp);
        skriver.close();
        return temp;
    }
    public String lesAlfabetFraFil(String filnavn) throws IOException {
        String temp = "";
        BufferedReader leser = new BufferedReader(new FileReader(filnavn));

        temp += leser.readLine() + "\n";
        temp += leser.readLine();

        leser.close();

        return temp;

    }
}























