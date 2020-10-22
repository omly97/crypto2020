package cs.util;

public class Vector {

    /** ---------------------------------------------------------
     *      conversion : ascii string && parsing integer text
     * ---------------------------------------------------------*/

    public static int[] valueOf(String str) {
        int[] vector = new int[str.length()];
        for (int i = 0; i < vector.length; i++) vector[i] = str.charAt(i);
        return vector;
    }

    public static int[] parseString(String str) {
        int[] vector = new int[str.length()];
        for (int i = 0; i < vector.length; i++)
            vector[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        return vector;
    }

    /** ---------------------------------------------------------
     *      self operations : permutation & delete element
     * ---------------------------------------------------------*/

    public static int[] permutation(int[] vector, int[] key) {
        int[] permuted = new int[vector.length];
        for (int i = 0; i < vector.length; i++) permuted[i] = vector[key[i]];
        return permuted;
    }

    static int[] deleteElement(int[] vector, int index) {
        int[] array = new int[vector.length - 1];
        int newIndex = 0;
        for (int i = 0; i < vector.length; i++) if (i != index) array[newIndex++] = vector[i];
        return array;
    }

    /** ---------------------------------------------------------
     *      cryptography : permutation keys
     * ---------------------------------------------------------*/

    public static int[] inverseIndex(int[] vector) {
        int[] array = new int[vector.length];
        for (int index = 0; index < vector.length; index++)
            for (int i = 0; i < vector.length; i++)
                if (vector[i] == index) {
                    array[index] = i;
                    break;
                }
        return array;
    }

    public static int[] rightShift(int length) {
        int[] key = new int[length];
        key[0] = length - 1;
        for (int i = 1; i < key.length; i++) key[i] = i - 1;
        return key;
    }

    public static int[] leftShift(int length) {
        int[] key = new int[length];
        key[length - 1] = 0;
        for (int i = 0; i < key.length - 1; i++) key[i] = i + 1;
        return key;
    }

    /** ---------------------------------------------------------
     *      vector operations : scalar - xor - concatenation
     * ---------------------------------------------------------*/

    static int scalar(int[] x, int[] y) {
        if (x.length != y.length) return -1;
        int result = 0;
        for (int i = 0; i < x.length; i++) result += x[i] * y[i];
        return Math.floorMod(result, 256);
    }

    public static int[] xor(int[] a, int[] b) {
        //if (a.length != b.length) return null;
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) res[i] = a[i] ^ b[i];
        return res;
    }

    public static int[] concat(int[] a, int[] b) {
        int[] res = new int[a.length + b.length];
        System.arraycopy(a, 0, res, 0, a.length);
        System.arraycopy(b, 0, res, a.length, b.length);
        return res;
    }
}
