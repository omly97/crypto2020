package main;

import cs.impl.BlockTransposition;
import cs.impl.DESLegacy;
import cs.impl.HillEncryption;
import cs.util.Text;

public class Test {

    private static final int[] TRANSPOSITION_KEY = {3, 2, 1, 0};
    private static final String HILL_KEY = "j!h@mol$xqwsijsr";


    public static void main(String[] args) {
        String clearText = "1BONJOUR ET BIENVENU CHEZ NOUS BINETA";
        String cipherText;
        String decipherText;

        cipherText = cipher(clearText);
        decipherText = decipher(cipherText);

        System.out.println(clearText);
        System.out.println(cipherText);
        System.out.println(decipherText);
    }

    public static String cipher(String text) {
        BlockTransposition b = new BlockTransposition();
        b.setArrayKey(TRANSPOSITION_KEY);

        HillEncryption h = new HillEncryption();
        h.setStringKey(HILL_KEY);

        DESLegacy des = new DESLegacy();

        StringBuilder cipherText = new StringBuilder();
        String[] blocks = {};

        // chiffrement classique
        text = Text.padding(text, 16);
        blocks = Text.divideToBlocks(text, 16);
        for (String block : blocks) {
            block = b.cipher(block);
            block = h.cipher(block);
            cipherText.append(block);
        }

        return cipherText.toString();
    }

    public static String decipher(String cipherText) {
        BlockTransposition b = new BlockTransposition();
        b.setArrayKey(TRANSPOSITION_KEY);

        HillEncryption h = new HillEncryption();
        h.setStringKey(HILL_KEY);

        StringBuilder text = new StringBuilder();
        String[] blocks = {};

        //cipherText = Text.padding(cipherText, 16);
        blocks = Text.divideToBlocks(cipherText, 16);
        for (String block : blocks) {
            block = h.decipher(block);
            block = b.decipher(block);
            text.append(block);
        }

        return text.toString();
    }

    /**
     * HillEncryption h1 = new HillEncryption();
     *         h1.setStringKey("j!h@mol$xqwsijsr");
     *         h1.setText("1234-ABCD-TDSI-X");
     *         h1.setCipher(h1.cipher(h1.getText()));
     *
     *         HillEncryption h2 = new HillEncryption();
     *         h2.setStringKey( h1.getStringKey() );
     *         h2.setCipher( h1.getCipher() );
     *         h2.setText( h2.decipher(h2.getCipher()) );
     *
     *         System.out.println(h1.getText());
     *         System.out.println(h2.getText());
     * */

    /**
     * BlockTransposition b1 = new BlockTransposition();
     *         int[] tkey = {3, 2, 1, 0};
     *         b1.setArrayKey(tkey);
     *         b1.setText("1234-ABCD-TDSI-X");
     *         b1.setCipher(b1.cipher(b1.getText()));
     *
     *         BlockTransposition b2 = new BlockTransposition();
     *         b2.setArrayKey( b1.getArrayKey() );
     *         b2.setCipher( b1.getCipher() );
     *         b2.setText( b2.decipher(b2.getCipher()) );
     *
     *         System.out.println(b1.getText());
     *         System.out.println(b2.getText());
     * */
}
