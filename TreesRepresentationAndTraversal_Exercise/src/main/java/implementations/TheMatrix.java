package implementations;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char toBeReplaced;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.toBeReplaced = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
        fillMatrix(startRow, startCol);
    }

    private void fillMatrix(int row, int col) {

        if (isOutOfBounds(row, col) || this.matrix[row][col] != toBeReplaced) {
            return;
        }

        this.matrix[row][col] = fillChar;

        System.out.println(this.toOutputString());
        System.out.println();

        fillMatrix(row - 1, col);
        fillMatrix(row, col - 1);
        fillMatrix(row + 1, col);
        fillMatrix(row, col + 1);
    }

    private boolean isOutOfBounds(int row, int col) {
        return (row < 0 || row >= matrix.length) || (col < 0 || col >= matrix[row].length);
    }

    public String toOutputString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
