import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    int[][] field;
    boolean isXTurn;

    public TicTacToe() {
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = FIELD_EMPTY;
            }
        }
    }

    public void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLUE);
        for (int i = 0; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            if (field[i][j] == FIELD_EMPTY) {
                field[i][j] = isXTurn ? FIELD_X : FIELD_O;
                isXTurn = !isXTurn;
                repaint();
                int res = checkState();
                if (res != 0) {
                    if (res == FIELD_O * 3) {
                        JOptionPane.showMessageDialog(this, "Нолики выиграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    } else if (res == FIELD_X * 3) {
                        JOptionPane.showMessageDialog(this, "Крестики выиграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Ничья!", "Ничья!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    initGame();
                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        drawXO(graphics);
    }

    public void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawLine(x + 5, y + 5, x + dw - 5, y + dh - 5);
        graphics.drawLine(x + 5, y + dh - 5, x + dw - 5, y + 5);
    }

    public void drawO(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        graphics.drawOval(x + 5 * dw / 100, y+ 5 * dh / 100, dw * 9 / 10, dh * 9 / 10);
    }

    public void drawXO(Graphics graphics) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == FIELD_O) {
                    drawO(i, j, graphics);
                }
            }
        }
    }

    public int checkState() {
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag1 += field[i][i];
            diag2 += field[i][2 - i];
        }
        if (diag1 == FIELD_O * 3 || diag1 == FIELD_X * 3) {
            return diag1;
        }
        if (diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3) {
            return diag2;
        }
        int checkI, checkJ;
        boolean hasEmpty = false;
        for (int i = 0; i < 3; i++) {
            checkI = 0;
            checkJ = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                checkI += field[i][j];
                checkJ += field[j][i];
                if (checkI == FIELD_O * 3 || checkI == FIELD_X * 3) {
                    return checkI;
                }
                if (checkJ == FIELD_O * 3 || checkJ == FIELD_X * 3) {
                    return checkJ;
                }
            }
        }
        if (hasEmpty) return 0;
        else return -1;
    }
}
