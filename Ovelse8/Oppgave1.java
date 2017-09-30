//Oppgave 8.16.1 s.283 30-09-2017
import javax.swing.*;

class NyString {
    private String data = "";
}

class Oppgave1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Test");
                frame.setSize(500,500);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
