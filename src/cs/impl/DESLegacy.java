package cs.impl;

import cs.abs.CryptoSystemAbstract;
import cs.key.KeyGen;
import cs.util.Text;
import cs.util.Vector;

public class DESLegacy extends CryptoSystemAbstract {

    private final KeyGen keyGen = new KeyGen();
    private final int[] PERMUTATION = {
            58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44 ,36, 28, 20, 12, 4,
            62, 54, 46, 38, 30, 22, 14, 6, 0, 56, 48, 40, 32, 24, 16, 8,
            57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3,
            61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
    private final int[] PI = {
            1, 18, 3, 20, 6, 22, 9, 24, 11, 26, 13, 28, 15, 30, 31, 0,
            16, 29, 14, 27, 12, 25, 10, 23, 8, 21, 7, 19, 5, 17, 2, 4 };

    public DESLegacy() {
        super();
    }

    @Override
    public String cipher(String text) {
        text = Text.permutation(text, this.PERMUTATION);
        int[] left = Vector.parseString(text.substring(0, 32));
        int[] right = Vector.parseString(text.substring(32, 64));
        int[][] keys = this.keyGen.generateKeys(super.getStringKey());
        for (int[] key : keys) {
            int[][] blocks = roundCipher(left, right, key);
            left = blocks[0];
            right = blocks[1];
        }
        String cipher = Text.parseVector(left) + Text.parseVector(right);
        cipher = Text.permutation(cipher, Vector.inverseIndex(this.PERMUTATION));
        return cipher;
    }

    @Override
    public String decipher(String cipher) {
        cipher = Text.permutation(cipher, this.PERMUTATION);
        int[] left = Vector.parseString(cipher.substring(0, 32));
        int[] right = Vector.parseString(cipher.substring(32, 64));
        int[][] keys = keyGen.generateKeys(super.getStringKey());
        for (int i = keys.length - 1; i >= 0; i--) {
            int[][] blocks = roundDecipher(left, right, keys[i]);
            left = blocks[0];
            right = blocks[1];
        }
        String text = Text.parseVector(left) + Text.parseVector(right);
        text = Text.permutation(text, Vector.inverseIndex(this.PERMUTATION));
        return text;
    }

    /** ---------------------------------------------------------
     *      Feistel Round Cipher
     * ---------------------------------------------------------*/

    private int[][] roundCipher(int[] left, int[] right, int[] key) {
        int[][] blocks = new int[2][];
        blocks[0] = right;
        blocks[1] = this.feistelFunction(left, right, key);
        return blocks;
    }

    private int[] feistelFunction(int[] left, int[] right, int[] key) {
        int[] block = Vector.xor(right, key);
        block = Vector.permutation(block, this.PI);
        block = Vector.xor(left, block);
        return block;
    }

    /** ---------------------------------------------------------
     *      Feistel Round Decipher
     * ---------------------------------------------------------*/

    private int[][] roundDecipher(int[] left, int[] right, int[] key) {
        int[][] blocks = new int[2][];
        blocks[0] = this.feistelInverseFunction(left, right, key);
        blocks[1] = left;
        return blocks;
    }

    private int[] feistelInverseFunction(int[] left, int[] right, int[] key) {
        int[] block = Vector.xor(left, key);
        block = Vector.permutation(block, this.PI);
        block = Vector.xor(right, block);
        return block;
    }
}
