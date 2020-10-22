package cs.abs;

public abstract class CryptoSystemAbstract implements CryptoSystem {

    private String text, cipher;
    private String stringKey;
    private int[] arrayKey;

    public CryptoSystemAbstract() {}

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    public String getCipher() {
        return cipher;
    }

    public void setStringKey(String stringKey) {
        this.stringKey = stringKey;
    }

    public String getStringKey() {
        return stringKey;
    }

    public void setArrayKey(int[] arrayKey) {
        this.arrayKey = arrayKey;
    }

    public int[] getArrayKey() {
        return arrayKey;
    }

    @Override
    public abstract String cipher(String text);

    @Override
    public abstract String decipher(String cipher);
}
