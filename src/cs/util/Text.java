package cs.util;

public class Text {

    /** ---------------------------------------------------------
     *      conversion : ascii string && parsing integer
     * ---------------------------------------------------------*/

    public static String valueOf(int[] vector) {
        StringBuilder builder = new StringBuilder();
        for (int i : vector) builder.append((char) i);
        return builder.toString();
    }

    public static String parseVector(int[] vector) {
        StringBuilder str = new StringBuilder();
        for (int i : vector) str.append(i);
        return str.toString();
    }

    /** ---------------------------------------------------------
     *      operations : blocks operations
     * ---------------------------------------------------------*/

    public static String padding(String text, int blockLength) {
        int rest = text.length() % blockLength;
        int length = rest == 0 ? blockLength : blockLength - rest;
        return text + new String(new char[length]).replace('\u0000', 'x');
    }

    public static String[] divideToBlocks(String text, int blockLength) {
        String[] blocks = new String[text.length() / blockLength];
        for (int i = 0; i < blocks.length; i++) blocks[i] = text.substring(i*blockLength, (i+1)*blockLength);
        return blocks;
    }

    public static String appendBlocks(String[] blocks) {
        StringBuilder builder = new StringBuilder();
        for (String block : blocks) builder.append(block);
        return  builder.toString();
    }

    /** ---------------------------------------------------------
     *      operations : self operations
     * ---------------------------------------------------------*/

    public static String permutation(String str, int[] key) {
        StringBuilder builder = new StringBuilder();
        for (int i : key) builder.append(str, i, i+1);
        return builder.toString();
    }

    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }
}
