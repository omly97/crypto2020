package cs.key;

import cs.util.Vector;

public class KeyGen {

    private final int[] FK1 = {7, 14, 8, 10, 4, 12, 1, 9, 0, 5, 15, 13, 2, 6, 3, 11};
    private final int[] FK2 = {10, 5, 9, 12, 3, 14, 13, 8, 0, 15, 6, 2, 11, 4, 1, 7};

    private final int[] L_SHIFT = Vector.leftShift(16);
    private final int[] R_SHIFT = Vector.rightShift(16);

    public KeyGen() {}

    public int[][] generateKeys(String key) {
        int[][] keys = new int[16][];
        int[] left = this.initialize(key.substring(0, 32));
        int[] right = this.initialize(key.substring(32, 64));
        for (int i = 0; i < keys.length; i++) {
            left = Vector.permutation(left, L_SHIFT);
            right = Vector.permutation(right, R_SHIFT);
            keys[i] = Vector.concat(left, right);
        }
        return keys;
    }

    private int[] initialize(String str) {
        int[] left = Vector.parseString(str.substring(0, 16));
        int[] right = Vector.parseString(str.substring(16, 32));
        left = Vector.permutation(left, FK1);
        right = Vector.permutation(right, FK2);
        return Vector.xor(left, right);
    }
}
