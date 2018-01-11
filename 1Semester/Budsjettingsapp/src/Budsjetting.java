import java.io.*;
import java.time.LocalDate;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class Budsjetting {
    Year[] years;
    LocalDate today = LocalDate.now();

    public Budsjetting() {
        this.years = new Year[1];
        this.years[0] = new Year(this.today.getYear());
    }

    public boolean readAr(String filnavn) {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filnavn);
            in = new ObjectInputStream(fis);
            Budsjetting midl = (Budsjetting) in.readObject();
            years = new Year[midl.years.length];
            for(int i = 0; i < midl.years.length; i++) {
                years[i] = midl.years[i];
            }
            in.close();
            fis.close();
            midl = null;
            return true;
        } catch(FileNotFoundException f) {
            showMessageDialog(null, "Filen ble ikke funnet");
            f.printStackTrace();
        } catch(ClassNotFoundException c) {
            showMessageDialog(null, "Filen inneholder ikke det som var forventet");
            c.printStackTrace();
        } catch(IOException i) {
            showMessageDialog(null, "Noe med I/O gikk galt");
            i.printStackTrace();
        } catch(Exception e) {
            showMessageDialog(null, "Noe annet gikk galt gi denne koden til noen som kanskje vet hva det betyr: " + e.toString());
            e.printStackTrace();
        }
        return false;
    }
    public boolean writeAr(String filnavn) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filnavn);
            out = new ObjectOutputStream(fos);
            out.writeObject(this);
            out.close();
            fos.close();
            return true;
        } catch(IOException i) {
            showMessageDialog(null, "Noe gikk galt med IO, gi denne beskeden til noen som kan java: " + i.toString());
            i.printStackTrace();
        } catch(Exception e) {
            showMessageDialog(null, "Noe gikk galt, gi denne beskeden til noen som kan java: " + e.toString());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a new element to the years array in Budsjetting
     * @param input Takes a object og the class Year as input
     */
    public void regYear(Year input) {
        int year = getIntInput();
        if(year != -1) {
            Year[] temp = new Year[years.length+1];
            System.arraycopy(years, 0, temp, 0, years.length);
            temp[temp.length-1] = new Year(year);
            years = temp;
        }
    }

    /**
     * Method to get year (int) from user, returned int must be over 0, since it is assumed the user hasn't lived that long
     * @return Returns the input number if user doesn't cancel, if the user does cancel returns null
     */
    private int getIntInput() {
        while(true) {
            String input = showInputDialog("Skriv inn årstallet å registrere: ");
            if(input != null && input.length() > 0) {
                try {
                    int value = Integer.parseInt(input);
                    if(value > 0) {
                        return value;
                    } else {
                        showMessageDialog(null, "Tallet må være over 0");
                    }
                } catch(Exception e) {
                    showMessageDialog(null, "Det er ikke ett tall");
                }
            }
            else {
                return -1;
            }
        }
    }
}
