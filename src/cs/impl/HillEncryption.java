package cs.impl;

import cs.abs.CryptoSystemAbstract;
import cs.util.Matrix;

public class HillEncryption extends CryptoSystemAbstract {

    private static final int MATRIX_ORDER = 4;

    public HillEncryption() {
        super();
    }

    @Override
    public String cipher(String text) {
        int[][] textMatrix = Matrix.valueOf(text, MATRIX_ORDER);
        int[][] keyMatrix = Matrix.valueOf(super.getStringKey(), MATRIX_ORDER);
        int[][] cipherMatrix = Matrix.product(textMatrix, keyMatrix);
        if (cipherMatrix != null) return Matrix.toString(cipherMatrix);
        else return null;
    }

    @Override
    public String decipher(String cipher) {
        int[][] cipherMatrix = Matrix.valueOf(cipher, MATRIX_ORDER);
        int[][] keyMatrix = Matrix.inverseOf(Matrix.valueOf(super.getStringKey(), MATRIX_ORDER));
        if (keyMatrix != null) {
            int[][] textMatrix = Matrix.product(cipherMatrix, keyMatrix);
            if (textMatrix != null) return Matrix.toString(textMatrix);
            else return null;
        }
        else return null;
    }
}
