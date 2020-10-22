package cs.util;

public class Matrix {

    /** ---------------------------------------------------------
     *      conversion type : matrix <--> String
     * ---------------------------------------------------------*/

    public static int[][] valueOf(String str, int matrixOrder) {
        //char[] chars = str.toCharArray();
        int[][] matrix = new int[matrixOrder][matrixOrder];
        for (int row = 0; row < matrixOrder; row++)
            for (int column = 0; column < matrixOrder; column++)
                matrix[row][column] = str.charAt(matrixOrder * row + column);
        return matrix;
    }

    public static String toString(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (int[] lineMatrix : matrix)
            for (int b : lineMatrix) builder.append((char) b);
        return builder.toString();
    }

    /** ---------------------------------------------------------
     *      self operations : Transposition & Permutation
     * ---------------------------------------------------------*/

    public static int[][] transposition(int[][] matrix) {
        int[][] transposed = new int[matrix.length][matrix.length];
        for (int row = 0; row < matrix.length; row++)
            for (int column = 0; column < matrix.length; column++)
                transposed[column][row] = matrix[row][column];
        return transposed;
    }

    public static int[][] permutation(int[][] matrix, int[] key) {
        int[][] permuted = new int[matrix.length][];
        int i = 0;
        for (int[] line : matrix) permuted[i++] = Vector.permutation(line, key);
        return permuted;
    }

    /** ---------------------------------------------------------
     *      matrix operations : product
     * ---------------------------------------------------------*/

    private static int[][] product(int n, int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                matrix[i][j] = Math.floorMod(n * matrix[i][j], 256);
        return matrix;
    }

    public static int[][] product(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length) return null;
        int[][] matrix = new int[matrix1.length][matrix1.length];
        matrix2 = transposition(matrix2);
        int i = 0;
        for (int[] x : matrix1) {
            int j = 0;
            for (int[] y : matrix2) matrix[i][j++] = Vector.scalar(x, y);
            i++;
        }
        return matrix;
    }

    /** ---------------------------------------------------------
     *      calculation : determinant using sub Matrix
     * ---------------------------------------------------------*/

    private static int[][] subMatrix(int[][] matrix, int row, int column) {
        int[][] newMatrix = new int[matrix.length - 1][];
        int newIndex = 0, i = 0;
        for (int[] line : matrix) if (i++ != row) newMatrix[newIndex++] = Vector.deleteElement(line, column);
        return newMatrix;
    }

    private static int determinantOf(int[][] matrix) {
        int det = 0;
        if (matrix.length == 2) det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        else for (int row = 0; row < matrix.length; row++)
            det += Math.pow(-1, row) * matrix[row][0] * determinantOf(subMatrix(matrix, row, 0));
        return Math.floorMod(det, 256);
    }

    /** ---------------------------------------------------------
     *      calculation : inverse matrix using co matrix
     * ---------------------------------------------------------*/

    private static int[][] coMatrix(int[][] matrix) {
        int[][] coMat = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                coMat[i][j] = Math.floorMod((int) Math.pow(-1, i+j) * determinantOf(subMatrix(matrix, i, j)), 256);
        return coMat;
    }

    private static int moduloInverse(int n) {
        n = n % 256;
        int inverse = 0;
        for (int x = 0; x < 256; x++)
            if ((n * x) % 256 == 1) {
                inverse = x;
                break;
            }
        return inverse;
    }

    public static int[][] inverseOf(int[][] matrix) {
        int inverseDet = moduloInverse(determinantOf(matrix));
        if (inverseDet == 0) return null;
        return product(inverseDet, transposition(coMatrix(matrix)));
    }
}
