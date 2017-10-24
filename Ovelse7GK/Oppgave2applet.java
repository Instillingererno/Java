//Oppgave 10.9.1 appletversjon s.345 29-09-2017
import javax.swing.*;
import java.awt.*;

public class Oppgave2applet extends JApplet {
    public void init() {
        add(new Tegning());
    }
}

class Tegning extends JPanel {
    public void paintComponent(Graphics tegneflate) {
        super.paintComponent(tegneflate);
        setBackground(Color.ORANGE);
        tegneflate.drawOval(100, 100, getWidth()-200, getHeight()-200); //x, y, width, height
        tegneflate.setColor(Color.YELLOW);
        tegneflate.fillOval(100, 100, getWidth()-200, getHeight()-200);
        tegneflate.setColor(Color.BLACK);
        tegneflate.fillOval(getWidth()-210, 200, 30, 30);
        tegneflate.fillOval(170, 200, 30, 30);
        tegneflate.drawArc(150, 160, getWidth()-200-getWidth()/5, getHeight()-200-getHeight()/5, -30, -120);

    }
}
