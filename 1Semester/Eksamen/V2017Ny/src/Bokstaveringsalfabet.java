import java.io.*;

public class Bokstaveringsalfabet {
    String navn;
    String[] alfabet;

    public Bokstaveringsalfabet(String navn, String alfabet, String skilletegn) {
        this.navn = navn;
        this.alfabet = alfabet.split(skilletegn);
    }
    public Bokstaveringsalfabet(String navn, String filnavn) {
        this.navn = navn;
        this.alfabet = lesAlfabetFraFil(filnavn);
    }
    public String toString() {
        String temp = "Ingen alfabet funnet";
        if(alfabet != null) {
            temp = "";
            for(String i : alfabet) temp += (i != null) ? i + " " : "";
        }
        return temp;
    }
    public boolean regNyttOrd(String ord) {
        if(alfabet != null) {
            for(String i : alfabet) if(i != null) if(i.substring(0,1).equals(ord.substring(0,1))) return false;
            String[] temp = new String[alfabet.length+1];
            //for(int i = 0; i < alfabet.length; i++) temp[i] = alfabet[i];
            System.arraycopy(alfabet,0, temp, 0, alfabet.length);
            temp[alfabet.length] = ord;
            alfabet = temp;
            return true;
        }
        return false;
    }
    public boolean endreOrd(String ord) {
        if(alfabet != null) {
            for(String i : alfabet) if(i != null) if(i.substring(0,1).equals(ord.substring(0,1))) {
                i = ord;
                return true;
            }
        }
        return false;
    }
    public String[] sorter() {
        boolean sortert = false;
        if(alfabet != null) {
            while(!sortert) {
                sortert = true;
                for(int i = 0; i < alfabet.length - 1; i++) {
                    if(alfabet[i] == null && alfabet[i + 1] != null) {
                        alfabet[i] = alfabet[i + 1];
                        alfabet[i + 1] = null;
                        sortert = false;
                    } else if((alfabet[i] != null && alfabet[i+1] != null) && alfabet[i].compareTo(alfabet[i + 1]) > 0) {
                        String temp = alfabet[i];
                        alfabet[i] = alfabet[i + 1];
                        alfabet[i + 1] = temp;
                        sortert = false;
                    }
                }
            }
        }
        return alfabet;
    }
    public String bokstavering(String ord) {
        String output = "";
        boolean found = false;
        if(alfabet != null) {
            for(int i = 0; i < ord.length(); i++) {
                found = false;
                for(String j : alfabet) if(j != null) if(j.substring(0,1).equals(ord.substring(i,i+1))) {
                    output += j + " ";
                    found = true;
                    break;
                }
                if(!found) output += "Ukjent ";
            }
        }
        return output;
    }
    public boolean skrivTilFil(String[] alfabet, String skilletegn, String filnavn) {
        if(alfabet != null) return false;
        try {
            FileWriter hentFil = new FileWriter(filnavn, false);
            BufferedWriter filUt = new BufferedWriter(hentFil);
            PrintWriter ut = new PrintWriter(filUt);
            ut.println(skilletegn);

            String temp = "";
            for(String i : alfabet) if(i != null) temp += i + skilletegn;
            ut.println(temp);

            ut.close();
            filUt.close();
            hentFil.close();
            return true;
        } catch(IOException e) {
            System.out.println("Fant ikke filen");
        } catch(Exception e) {
            System.out.println("Noe annet gikk galt, prøv igjen");
        }
        return false;
    }
    public String[] lesAlfabetFraFil(String filnavn) {
        String[] output;
        try {
            FileReader filIn = new FileReader(filnavn);
            BufferedReader in = new BufferedReader(filIn);
            String skilletegn = in.readLine();
            output = in.readLine().split(skilletegn);
            in.close();
            filIn.close();
            return output;
        } catch(IOException e) {
            System.out.println("Filen fantes ikke");
        } catch(Exception e) {
            System.out.println("Noe annet gikk galt, prøv igjen");
        }
        return null;
    }
}
