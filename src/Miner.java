import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Miner extends JComponent {
    FieldForMiner field = new FieldForMiner(FIELD_HEIGHT / CELL_WIGHT_HEIGHT, FIELD_WIGHT / CELL_WIGHT_HEIGHT);
    private static final int FIELD_WIGHT = 400;
    private static final int FIELD_HEIGHT = 600;

    private static final int CELL_WIGHT_HEIGHT = 40;


    public Miner() {
        JFrame window = new JFrame("Miner");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(FIELD_WIGHT + 17, FIELD_HEIGHT + 40);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field.generate();
        window.add(this);
    }

    public void drawGrid(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i <= FIELD_WIGHT / CELL_WIGHT_HEIGHT; i++) {
            graphics.drawLine(CELL_WIGHT_HEIGHT * i, 0, CELL_WIGHT_HEIGHT * i, FIELD_HEIGHT);
        }
        for (int i = 0; i <= FIELD_HEIGHT / CELL_WIGHT_HEIGHT; i++) {
            graphics.drawLine(0, CELL_WIGHT_HEIGHT * i, FIELD_WIGHT, CELL_WIGHT_HEIGHT * i);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        drawField(graphics);

    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getY();
            int y = mouseEvent.getX();
            int i = (int) ((float) x / CELL_WIGHT_HEIGHT);
            int j = (int) ((float) y / CELL_WIGHT_HEIGHT);
            if (field.getField()[i][j] != -1) {
                if (field.getMinesLocation()[i][j]) {
                    JOptionPane.showMessageDialog(this, "Бум!", "Увы!", JOptionPane.INFORMATION_MESSAGE);
                    field.generate();
                    repaint();
                } else {
                    field.checkAround(i, j);
                    if(field.checkForWin()){
                        repaint();
                        JOptionPane.showMessageDialog(this, "Победа!", "Молодец!", JOptionPane.INFORMATION_MESSAGE);
                        field.generate();
                        repaint();
                    }
                    repaint();
                }
            }
        }
        if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
            int x = mouseEvent.getY();
            int y = mouseEvent.getX();
            int i = (int) ((float) x / CELL_WIGHT_HEIGHT);
            int j = (int) ((float) y / CELL_WIGHT_HEIGHT);
            if (field.getField()[i][j] != -1) {
                field.getField()[i][j] = -1;
            } else {
                field.getField()[i][j] = -2;
            }
            repaint();
        }
    }

    private void drawField(Graphics graphics) {
        for (int i = 0; i < FIELD_HEIGHT / CELL_WIGHT_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIGHT / CELL_WIGHT_HEIGHT; j++) {
                if (field.getField()[i][j] == -1) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawOval((int) (j + 0.5) * CELL_WIGHT_HEIGHT + 2, (int) (i + 0.5) * CELL_WIGHT_HEIGHT + 2, 36, 36);
                } else if (field.getField()[i][j] > 0) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(Integer.toString(field.getField()[i][j]), (j) * CELL_WIGHT_HEIGHT + 11, (i + 1) * CELL_WIGHT_HEIGHT - 8);
                } else if (field.getField()[i][j] == 0) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(j * CELL_WIGHT_HEIGHT + 1, i * CELL_WIGHT_HEIGHT + 1, CELL_WIGHT_HEIGHT - 2, CELL_WIGHT_HEIGHT - 2);
                }
            }
        }
    }
}
