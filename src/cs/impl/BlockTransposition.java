package cs.impl;

import cs.abs.CryptoSystemAbstract;
import cs.util.Matrix;
import cs.util.Vector;

public class BlockTransposition extends CryptoSystemAbstract {

    private static final int MATRIX_ORDER = 4;

    public BlockTransposition() {
        super();
    }

    @Override
    public String cipher(String text) {
        int[][] matrix = Matrix.valueOf(text, MATRIX_ORDER);
        matrix = Matrix.permutation(matrix, super.getArrayKey());
        matrix = Matrix.transposition(matrix);
        return Matrix.toString(matrix);
    }

    @Override
    public String decipher(String cipher) {
        int[][] matrix = Matrix.valueOf(cipher, MATRIX_ORDER);
        matrix = Matrix.transposition(matrix);
        matrix = Matrix.permutation(matrix, Vector.inverseIndex(super.getArrayKey()));
        return Matrix.toString(matrix);
    }
}
