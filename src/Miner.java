import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

public class Miner extends JComponent {
    FieldForMiner field;
    private static int FIELD_WIGHT = 400;
    private static int FIELD_HEIGHT = 400;
    private static boolean ifMenu = true;
    private static int countOfMines = 0;


    public Miner() {
        JFrame window = new JFrame("Miner");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(Settings.WINDOW_WIGHT, Settings.WINDOW_HEIGHT);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        window.add(this);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        graphics.clearRect(0, 0, getWidth(), getHeight());
        if (ifMenu) {
            try {
                drawMenu(graphics);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            drawGrid(graphics);
            drawField(graphics);
            drawStats(graphics);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int y = mouseEvent.getY();
            int x = mouseEvent.getX();
            if (ifMenu) {
                int wightAdd = (Settings.WINDOW_WIGHT - 400) / 2; //300
                int heightAdd = (Settings.WINDOW_HEIGHT - 400) / 2; //400
                if (x > (95 + wightAdd) && x < (305 + wightAdd)) {
                    if (y > (120 + heightAdd) && y < (160 + heightAdd)) {
                        FIELD_WIGHT = 400;
                        FIELD_HEIGHT = 400;
                        ifMenu = false;
                        field = new FieldForMiner(FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT, FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT);
                        field.generate();
                        repaint();
                    } else if (y > (200 + heightAdd) && y < (240 + heightAdd)) {
                        FIELD_WIGHT = 400;
                        FIELD_HEIGHT = 600;
                        ifMenu = false;
                        field = new FieldForMiner(FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT, FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT);
                        field.generate();
                        repaint();
                    } else if (y > (280 + heightAdd) && y < (320 + heightAdd)) {
                        FIELD_WIGHT = 600;
                        FIELD_HEIGHT = 800;
                        ifMenu = false;
                        field = new FieldForMiner(FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT, FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT);
                        field.generate();
                        repaint();
                    }
                }
            } else {
                int i = (y - ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
                int j = (x - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
                if (field.getField()[i][j] != -1) {
                    if (field.getMinesLocation()[i][j]) {
                        JOptionPane.showMessageDialog(this, "Бум!", "Увы!", JOptionPane.INFORMATION_MESSAGE);
                        countOfMines = 0;
                        ifMenu = true;
                        repaint();
                    } else {
                        field.checkAround(i, j);
                        if (field.checkForWin()) {
                            repaint();
                            JOptionPane.showMessageDialog(this, "Победа!", "Молодец!", JOptionPane.INFORMATION_MESSAGE);
                            setWinsCount(getWinsCount() + 1);
                            countOfMines = 0;
                            ifMenu = true;
                            repaint();
                        }
                        repaint();
                    }
                }
            }
        }
        if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseEvent.BUTTON3 && !ifMenu) {
            int i = (mouseEvent.getY() - ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
            int j = (mouseEvent.getX() - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
            if (field.getField()[i][j] != -1) {
                field.getField()[i][j] = -1;
                countOfMines++;
            } else {
                field.getField()[i][j] = -2;
                countOfMines--;
            }
            repaint();
        }

    }

    private void drawMenu(Graphics graphics) throws FileNotFoundException {
        int wightAdd = (Settings.WINDOW_WIGHT - 400) / 2; //300
        int heightAdd = (Settings.WINDOW_HEIGHT - 400) / 2; //400
        graphics.setColor(Color.BLACK);

        graphics.drawRect(80 + wightAdd, 40 + heightAdd, 240, 40);
        graphics.drawString("Сапёр", 150 + wightAdd, 70 + heightAdd);

        graphics.drawRect(95 + wightAdd, 120 + heightAdd, 210, 40);
        graphics.drawString("Лёгкий", 145 + wightAdd, 150 + heightAdd);

        graphics.drawRect(95 + wightAdd, 200 + heightAdd, 210, 40);
        graphics.drawString("Средний", 135 + wightAdd, 230 + heightAdd);

        graphics.drawRect(95 + wightAdd, 280 + heightAdd, 210, 40);
        graphics.drawString("Тяжелый", 135 + wightAdd, 310 + heightAdd);

        graphics.drawRect(95 + wightAdd, 360 + heightAdd, 210, 40);
        graphics.drawString("Побед: " + Integer.toString(getWinsCount()), 135 + wightAdd, 390 + heightAdd);
    }

    private void drawGrid(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int wightAdd = (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2;
        int heightAdd = (Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2;
        for (int i = 0; i <= FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT; i++) {
            graphics.drawLine(Settings.CELL_WIGHT_HEIGHT * i + wightAdd, heightAdd, Settings.CELL_WIGHT_HEIGHT * i + wightAdd, FIELD_HEIGHT + heightAdd);
        }
        for (int i = 0; i <= FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT; i++) {
            graphics.drawLine(wightAdd, Settings.CELL_WIGHT_HEIGHT * i + heightAdd, FIELD_WIGHT + wightAdd, Settings.CELL_WIGHT_HEIGHT * i + heightAdd);
        }
    }

    private void drawField(Graphics graphics) {
        int wightAdd = (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2;
        int heightAdd = (Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2;
        for (int i = 0; i < FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT; j++) {
                if (field.getField()[i][j] == -1) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawOval((int) (j + 0.5) * Settings.CELL_WIGHT_HEIGHT + 2 + wightAdd, (int) (i + 0.5) * Settings.CELL_WIGHT_HEIGHT + 2 + heightAdd, 36, 36);
                } else if (field.getField()[i][j] > 0) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(Integer.toString(field.getField()[i][j]), j * Settings.CELL_WIGHT_HEIGHT + 11 + wightAdd, (i + 1) * Settings.CELL_WIGHT_HEIGHT - 8 + heightAdd);
                } else if (field.getField()[i][j] == 0) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(j * Settings.CELL_WIGHT_HEIGHT + 2 + wightAdd, i * Settings.CELL_WIGHT_HEIGHT + 2 + heightAdd, Settings.CELL_WIGHT_HEIGHT - 3, Settings.CELL_WIGHT_HEIGHT - 3);
                }
            }
        }
    }

    private void drawStats(Graphics graphics) {
        int x = (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2;
        int y = ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2) - 60;
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, 300, 40);
        graphics.drawString("Мин осталось: " + Integer.toString(field.getMinedCells() - countOfMines), x + 5, y + 35);
    }

    public int getWinsCount() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("resourses/resourses.txt"));
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setWinsCount(int count) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter("resourses/resourses.txt", false));
            out.write(Integer.toString(count));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}