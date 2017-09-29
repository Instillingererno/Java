//Oppgave 10.9.1 s.345 29-09-2017
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Vindu extends JFrame {
    public Vindu(String tittel) {
        setTitle(tittel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Tegning tegningen = new Tegning();
        add(tegningen);
    }
}

class Tegning extends JPanel {
    public void paintComponent(Graphics tegneflate) {
        super.paintComponent(tegneflate);
        setBackground(Color.ORANGE);
        tegneflate.drawString("Hei hei", 50, 50);
        tegneflate.drawOval(100, 100, getWidth()-200, getHeight()-200); //x, y, width, height

    }
}

class Oppgave2 {
    public static void main(String[] args) {
        Vindu etVindu = new Vindu("Enkel grafikk");
        etVindu.setVisible(true);
    }
}
