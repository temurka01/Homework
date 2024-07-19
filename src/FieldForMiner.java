import java.util.Random;

public class FieldForMiner {
    private final boolean[][] minesLocation;
    private final int[][] field;
    private final int height;
    private final int wight;
    private int notMinedCells;
    private int minedCells;

    public FieldForMiner(int height, int wight) {
        this.height = height;
        this.wight = wight;
        this.minesLocation = new boolean[height][wight];
        this.field = new int[height][wight];
        this.generate();
    }

    public void generate() {
        Random random = new Random();
        boolean t;
        notMinedCells = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wight; j++) {
                t = random.nextInt(100) > 90;
                minesLocation[i][j] = t;
                if (!t) notMinedCells++;
                field[i][j] = -2;
            }
        }
        minedCells = height * wight - notMinedCells;
    }

    public void checkAround(int x, int y) {//очень страшная, но очень легкая функция
        int countOfMines = 0;
        if (x == 0 || y == 0) {
            if (x == 0 && y == 0) {
                if (minesLocation[0][1]) countOfMines++;
                if (minesLocation[1][0]) countOfMines++;
                if (minesLocation[1][1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[0][1] == -2) checkAround(0, 1);
                    if (field[1][0] == -2) checkAround(1, 0);
                    if (field[1][1] == -2) checkAround(1, 1);
                }
            } else if (x == 0 && y == wight - 1) {
                if (minesLocation[0][wight - 2]) countOfMines++;
                if (minesLocation[1][wight - 1]) countOfMines++;
                if (minesLocation[1][wight - 2]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[0][wight - 2] == -2) checkAround(0, wight - 2);
                    if (field[1][wight - 1] == -2) checkAround(1, wight - 1);
                    if (field[1][wight - 2] == -2) checkAround(1, wight - 2);
                }
            } else if (x == height - 1 && y == 0) {
                if (minesLocation[height - 2][0]) countOfMines++;
                if (minesLocation[height - 1][1]) countOfMines++;
                if (minesLocation[height - 2][1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[height - 2][0] == -2) checkAround(height - 2, 0);
                    if (field[height - 1][1] == -2) checkAround(height - 1, 1);
                    if (field[height - 2][1] == -2) checkAround(height - 2, 1);
                }
            } else if (x == 0) {
                if (minesLocation[0][y - 1]) countOfMines++;
                if (minesLocation[0][y + 1]) countOfMines++;
                if (minesLocation[1][y - 1]) countOfMines++;
                if (minesLocation[1][y]) countOfMines++;
                if (minesLocation[1][y + 1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[0][y - 1] == -2) checkAround(0, y - 1);
                    if (field[0][y + 1] == -2) checkAround(0, y + 1);
                    if (field[1][y - 1] == -2) checkAround(1, y - 1);
                    if (field[1][y] == -2) checkAround(1, y);
                    if (field[1][y + 1] == -2) checkAround(1, y + 1);
                }
            } else {
                if (minesLocation[x - 1][0]) countOfMines++;
                if (minesLocation[x + 1][0]) countOfMines++;
                if (minesLocation[x - 1][1]) countOfMines++;
                if (minesLocation[x][1]) countOfMines++;
                if (minesLocation[x + 1][1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[x - 1][0] == -2) checkAround(x - 1, 0);
                    if (field[x + 1][0] == -2) checkAround(x + 1, 0);
                    if (field[x - 1][1] == -2) checkAround(x - 1, 1);
                    if (field[x][1] == -2) checkAround(x, 1);
                    if (field[x + 1][1] == -2) checkAround(x + 1, 1);
                }
            }
        } else if (x == height - 1 || y == wight - 1) {
            if (x == height - 1 && y == wight - 1) {
                if (minesLocation[height - 1][wight - 2]) countOfMines++;
                if (minesLocation[height - 2][wight - 2]) countOfMines++;
                if (minesLocation[height - 2][wight - 1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[height - 1][wight - 2] == -2) checkAround(height - 1, wight - 2);
                    if (field[height - 2][wight - 2] == -2) checkAround(height - 2, wight - 2);
                    if (field[height - 2][wight - 1] == -2) checkAround(height - 2, wight - 1);
                }
            } else if (y == wight - 1) {
                if (minesLocation[x - 1][wight - 1]) countOfMines++;
                if (minesLocation[x - 1][wight - 2]) countOfMines++;
                if (minesLocation[x][wight - 2]) countOfMines++;
                if (minesLocation[x + 1][wight - 2]) countOfMines++;
                if (minesLocation[x + 1][wight - 1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[x - 1][wight - 1] == -2) checkAround(x - 1, wight - 1);
                    if (field[x - 1][wight - 2] == -2) checkAround(x - 1, wight - 2);
                    if (field[x][wight - 2] == -2) checkAround(x, wight - 2);
                    if (field[x + 1][wight - 2] == -2) checkAround(x + 1, wight - 2);
                    if (field[x + 1][wight - 1] == -2) checkAround(x + 1, wight - 1);
                }
            } else {
                if (minesLocation[height - 1][y - 1]) countOfMines++;
                if (minesLocation[height - 2][y - 1]) countOfMines++;
                if (minesLocation[height - 2][y]) countOfMines++;
                if (minesLocation[height - 2][y + 1]) countOfMines++;
                if (minesLocation[height - 1][y + 1]) countOfMines++;
                field[x][y] = countOfMines;
                if (countOfMines == 0) {
                    if (field[height - 1][y - 1] == -2) checkAround(height - 1, y - 1);
                    if (field[height - 2][y - 1] == -2) checkAround(height - 2, y - 1);
                    if (field[height - 2][y] == -2) checkAround(height - 2, y);
                    if (field[height - 2][y + 1] == -2) checkAround(height - 2, y + 1);
                    if (field[height - 1][y + 1] == -2) checkAround(height - 1, y + 1);
                }
            }
        } else {
            if (minesLocation[x - 1][y - 1]) countOfMines++;
            if (minesLocation[x - 1][y]) countOfMines++;
            if (minesLocation[x - 1][y + 1]) countOfMines++;
            if (minesLocation[x][y - 1]) countOfMines++;
            if (minesLocation[x][y + 1]) countOfMines++;
            if (minesLocation[x + 1][y - 1]) countOfMines++;
            if (minesLocation[x + 1][y]) countOfMines++;
            if (minesLocation[x + 1][y + 1]) countOfMines++;
            field[x][y] = countOfMines;
            if (countOfMines == 0) {
                if (field[x - 1][y - 1] == -2) checkAround(x - 1, y - 1);
                if (field[x - 1][y] == -2) checkAround(x - 1, y);
                if (field[x - 1][y + 1] == -2) checkAround(x - 1, y + 1);
                if (field[x][y - 1] == -2) checkAround(x, y - 1);
                if (field[x][y + 1] == -2) checkAround(x, y + 1);
                if (field[x + 1][y - 1] == -2) checkAround(x + 1, y - 1);
                if (field[x + 1][y] == -2) checkAround(x + 1, y);
                if (field[x + 1][y + 1] == -2) checkAround(x + 1, y + 1);
            }
        }
    }

    public boolean checkForWin() {
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < wight; j++) {
                if (field[i][j] >= 0) count++;
            }
        }
        return count == notMinedCells;
    }

    public boolean[][] getMinesLocation() {
        return minesLocation;
    }

    public int[][] getField() {
        return field;
    }

    public int getMinedCells() {
        return minedCells;
    }
}