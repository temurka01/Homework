import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

public class Miner extends JComponent {
    private static final FieldForMiner field = new FieldForMiner();
    private static int FIELD_WIGHT;
    private static int FIELD_HEIGHT;
    private static boolean ifMenu = true;
    private static boolean ifLevelChoose = false;
    private static boolean ifGameOver = false;
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
        } else if (ifLevelChoose) {
            drawLevelChoose(graphics);
        } else {
            drawGrid(graphics);
            drawField(graphics);
            drawStats(graphics);
            drawExitAndRestartButton(graphics);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseEvent.BUTTON1) { // лкм
            int y = mouseEvent.getY();
            int x = mouseEvent.getX();
            if (ifMenu) { // в меню
                int wightAdd = (Settings.WINDOW_WIGHT - 400) / 2;
                int heightAdd = (Settings.WINDOW_HEIGHT - 400) / 2;
                if (x > (95 + wightAdd) && x < (305 + wightAdd)) {
                    if (y > (120 + heightAdd) && y < (160 + heightAdd)) {
                        toLevelChoose(Settings.DIFFICULTY_EASY);
                    } else if (y > (200 + heightAdd) && y < (240 + heightAdd)) {
                        toLevelChoose(Settings.DIFFICULTY_MEDIUM);
                    } else if (y > (280 + heightAdd) && y < (320 + heightAdd)) {
                        toLevelChoose(Settings.DIFFICULTY_HARD);
                    }
                }
            } else if (ifLevelChoose) { // в меню выбора размера поля
                int wightAdd = (Settings.WINDOW_WIGHT - 200) / 2;
                int heightAdd = (Settings.WINDOW_HEIGHT - 440) / 2;
                if (x > wightAdd && x < (200 + wightAdd)) {
                    if (y > (80 + heightAdd) && y < (120 + heightAdd)) { // 10 x 10
                        toLevel(400, 400);
                    } else if (y > (160 + heightAdd) && y < (200 + heightAdd)) { // 12 x 14
                        toLevel(480, 560);
                    } else if (y > (240 + heightAdd) && y < (280 + heightAdd)) { // 14 x 16
                        toLevel(560, 640);
                    } else if (y > (320 + heightAdd) && y < (360 + heightAdd)) { // 16 x 18
                        toLevel(640, 720);
                    } else if (y > (400 + heightAdd) && y < (440 + heightAdd)) { // 18 x 20
                        toLevel(720, 800);
                    }
                }
            } else { //  в игре
                if (y > (((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2) - 60) && y < ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2)) {
                    if(x > (Settings.WINDOW_WIGHT - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2) - 40) && x < (Settings.WINDOW_WIGHT - (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2)){
                        ifGameOver = false;
                        ifMenu = true;
                        repaint();
                    } else if(x > (Settings.WINDOW_WIGHT - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2) - 100) && x < ((Settings.WINDOW_WIGHT - (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2) - 60)){
                        field.restart();
                        ifGameOver = false;
                        repaint();
                    }
                } else {
                    int i = (y - ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
                    int j = (x - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2)) / Settings.CELL_WIGHT_HEIGHT;
                    if (field.getField()[i][j] != -1) {
                        if (field.getField()[i][j] == -3) {
                            endOfGame("Взрыв, хотите перезапустить игру?");
                        } else {
                            field.checkAround(i, j);
                            if (field.checkForWin()) {
                                repaint();
                                setWinsCount(getWinsCount() + 1);
                                endOfGame("Победа, хотите перезапустить игру?");
                            }
                            repaint();
                        }
                    }
                }
            }
        }
        if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED && mouseEvent.getButton() == MouseEvent.BUTTON3 && !ifMenu) { // пкм для установки "флажков"
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
        graphics.drawString("Побед: " + getWinsCount(), 135 + wightAdd, 390 + heightAdd);
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
                    setNumberColor(graphics, field.getField()[i][j]);
                    graphics.drawString(Integer.toString(field.getField()[i][j]), j * Settings.CELL_WIGHT_HEIGHT + 11 + wightAdd, (i + 1) * Settings.CELL_WIGHT_HEIGHT - 8 + heightAdd);
                } else if (field.getField()[i][j] == 0) {
                    graphics.setColor(Color.GRAY);
                    graphics.fillRect(j * Settings.CELL_WIGHT_HEIGHT + 2 + wightAdd, i * Settings.CELL_WIGHT_HEIGHT + 2 + heightAdd, Settings.CELL_WIGHT_HEIGHT - 3, Settings.CELL_WIGHT_HEIGHT - 3);
                } else if (field.getField()[i][j] == -3 && ifGameOver) {
                    graphics.setColor(Color.BLACK);
                    graphics.fillOval((int) (j + 0.5) * Settings.CELL_WIGHT_HEIGHT + 2 + wightAdd, (int) (i + 0.5) * Settings.CELL_WIGHT_HEIGHT + 2 + heightAdd, 36, 36);
                }
            }
        }
    }

    private void drawStats(Graphics graphics) {
        int x = (Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2;
        int y = ((Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2) - 60;
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, 260, 40);
        graphics.drawString("Мин осталось: " + (field.getMinedCells() - countOfMines), x + 5, y + 35);
    }

    private void drawExitAndRestartButton(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int wightAdd = Settings.WINDOW_WIGHT - ((Settings.WINDOW_WIGHT - FIELD_WIGHT) / 2);
        int heightAdd = (Settings.WINDOW_HEIGHT - FIELD_HEIGHT) / 2;

        graphics.drawRect(wightAdd - 40, heightAdd - 60, 40, 40); // exit
        graphics.drawLine(wightAdd - 36,heightAdd - 56,wightAdd - 4,heightAdd - 24);
        graphics.drawLine(wightAdd - 36,heightAdd - 24,wightAdd - 4,heightAdd - 56);

        graphics.drawRect(wightAdd - 100, heightAdd - 60, 40, 40); // restart
        graphics.drawOval(wightAdd - 96, heightAdd - 56, 32, 32);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(wightAdd - 92, heightAdd - 56, 24, 20);
        graphics.setColor(Color.BLACK);
        graphics.drawLine(wightAdd - 96, heightAdd - 52, wightAdd - 90, heightAdd - 52);
        graphics.drawLine(wightAdd - 90, heightAdd - 52, wightAdd - 90, heightAdd - 46);
    }

    private void drawLevelChoose(Graphics graphics) {
        int wightAdd = (Settings.WINDOW_WIGHT - 200) / 2;
        int heightAdd = (Settings.WINDOW_HEIGHT - 440) / 2;
        graphics.setColor(Color.BLACK);

        graphics.drawRect(wightAdd, heightAdd, 200, 40);
        graphics.drawString("Размер поля:", 10 + wightAdd, 30 + heightAdd);

        graphics.drawRect(wightAdd, 80 + heightAdd, 200, 40);
        graphics.drawString("10 x 10", 48 + wightAdd, 110 + heightAdd);

        graphics.drawRect(wightAdd, 160 + heightAdd, 200, 40);
        graphics.drawString("12 x 14", 48 + wightAdd, 190 + heightAdd);

        graphics.drawRect(wightAdd, 240 + heightAdd, 200, 40);
        graphics.drawString("14 x 16", 48 + wightAdd, 270 + heightAdd);

        graphics.drawRect(wightAdd, 320 + heightAdd, 200, 40);
        graphics.drawString("16 x 18", 48 + wightAdd, 350 + heightAdd);

        graphics.drawRect(wightAdd, 400 + heightAdd, 200, 40);
        graphics.drawString("18 x 20", 48 + wightAdd, 430 + heightAdd);

    }

    private void setNumberColor(Graphics graphics, int number) {
        switch (number) {
            case 1: {
                graphics.setColor(Color.BLUE);
                break;
            }
            case 2: {
                graphics.setColor(Color.GREEN);
                break;
            }
            case 3: {
                graphics.setColor(Color.RED);
                break;
            }
            case 4: {
                graphics.setColor(new Color(16, 44, 68));
                break;
            }
            case 5: {
                graphics.setColor(new Color(107, 61, 36));
                break;
            }
            case 6: {
                graphics.setColor(new Color(39, 128, 123));
                break;
            }
            case 7: {
                graphics.setColor(Color.BLACK);
                break;
            }
            case 8: {
                graphics.setColor(Color.LIGHT_GRAY);
                break;
            }
        }
    }

    private void endOfGame(String str) { // для разгрузки processMouseEvent
        int result = JOptionPane.showOptionDialog(null, str, "  ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, Settings.options, Settings.options[0]);

        switch (result) {
            case JOptionPane.YES_OPTION:
                ifMenu = true;
                countOfMines = 0;
                break;
            case JOptionPane.NO_OPTION:
                field.generate(FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT, FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT);
                countOfMines = 0;
                break;
            case JOptionPane.CLOSED_OPTION:
                ifGameOver = true;
                break;
        }
        repaint();
    }

    private void toLevel(int wight, int height) { // для разгрузки processMouseEvent
        FIELD_WIGHT = wight;
        FIELD_HEIGHT = height;
        field.generate(FIELD_HEIGHT / Settings.CELL_WIGHT_HEIGHT, FIELD_WIGHT / Settings.CELL_WIGHT_HEIGHT);
        ifLevelChoose = false;
        repaint();
    }

    private void toLevelChoose(int probability) { // для разгрузки processMouseEvent
        field.setChance(probability);
        ifMenu = false;
        ifLevelChoose = true;
        repaint();
    }

    public int getWinsCount() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("resources/resources.txt"));
            return Integer.parseInt(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setWinsCount(int count) {
        BufferedWriter out;
        try {
            out = new BufferedWriter(new FileWriter("resources/resources.txt", false));
            out.write(Integer.toString(count));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}