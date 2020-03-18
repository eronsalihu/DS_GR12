
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Playfair {
    private static String next=new String();

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        boolean done1 = false;
        boolean done2 = false;
        KeyTable table = null;
        Phrase phraseUsed = null;
        String phrase = null;
         next = in.nextLine();

        while (!done1) {
            String key = in.nextLine();
            if (!key.isEmpty()) {
                phrase = key;
            }
            try {
                table = KeyTable.buildFromString(phrase);
                done1 = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Illegal argument");
            }
        }
        while (!done2) {



            if (next.equals("ds playfair encrypt")) {
                String encrypt =
                        in.nextLine().replaceAll("\\s", "");
                phraseUsed = Phrase.buildPhraseFromStringforEnc(encrypt);
                phraseUsed = phraseUsed.encrypt(table);
                System.out.println("Encrypted text is: " +
                        phraseUsed.toString());
                break;
            }

            else if (next.equals("ds playfair decrypt")) {
                System.out.print("Please enter a phrase to decrypt: ");
                String decrypt = in.nextLine();
                decrypt = decrypt.toUpperCase();
                if (decrypt.contains("J")) {
                    System.out.println("Invalid statement to decrypt.");
                    continue;
                }
                for (int i = 0; i < decrypt.length(); i++) {
                    if (!Character.isLetter(decrypt.charAt(i))) {

                        continue;
                    }
                }

                Phrase decryptPhrase =
                        Phrase.buildPhraseFromStringforEnc(decrypt);
                decryptPhrase = decryptPhrase.decrypt(table);
                System.out.println("Decrypted text is: " +
                        decryptPhrase.toString());
                break;
            }
            else {
                System.out.println("Invalid input.");
            }
        }

        in.close();

    }


}
class Phrase extends LinkedList<Bigram> implements Queue<Bigram> {

    public void enqueue(Bigram b) {
        this.addLast(b);
    }

    public Bigram dequeue() {
        return (Bigram) this.remove();
    }

    public Bigram peek() {
        return (Bigram) this.getFirst();
    }

    public static Phrase buildPhraseFromStringforEnc(String s) {
        s = s.toUpperCase();
        String s2 = "";
        Phrase p = new Phrase();
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (Character.isLetter(s.charAt(j))) {
                s2 += s.charAt(j);
            }
        }
        while (i < s2.length()) {
            Bigram b;
            if (i == s2.length() - 1) {
                b = new Bigram(s2.charAt(i), 'X');
                i++;
            }
            else if (s2.charAt(i) == s2.charAt(i + 1)) {
                b = new Bigram(s2.charAt(i), 'X');
                i++;
            }
            else {
                b = new Bigram(s2.charAt(i), s2.charAt(i + 1));
                i = i + 2;
            }
            p.enqueue(b);
        }
        return p;
    }


    public static char findEncryptedRight(KeyTable k, char c) {
        int i = k.findRow(c);
        int j = k.findCol(c);
        if (j == k.getKeyTable()[0].length - 1) {
            j = 0;
        }
        else {
            j++;
        }
        return k.getKeyTable()[i][j];
    }

    public static void encryptRightShift(KeyTable k, Bigram b) {
        b.setFirst(findEncryptedRight(k, b.getFirstChar()));
        b.setSecond(findEncryptedRight(k, b.getSecondChar()));
    }

    public static char findEncryptedBottom(KeyTable k, char c) {
        int i = k.findRow(c);
        if (i == k.getKeyTable().length - 1) {
            i = 0;
        }
        else {
            i++;
        }
        return k.getKeyTable()[i][k.findCol(c)];
    }


    public static void encryptBottomShift(KeyTable k, Bigram b) {
        b.setFirst(findEncryptedBottom(k, b.getFirstChar()));
        b.setSecond(findEncryptedBottom(k, b.getSecondChar()));
    }


    public static void encryptRectangle(KeyTable k, Bigram b) {
        int i1 = k.findRow(b.getFirstChar());
        int j1 = k.findCol(b.getFirstChar());
        int i2 = k.findRow(b.getSecondChar());
        int j2 = k.findCol(b.getSecondChar());
        int temp = j1;
        j1 = j2;
        j2 = temp;
        b.setFirst(k.getKeyTable()[i1][j1]);
        b.setSecond(k.getKeyTable()[i2][j2]);
    }

    public Phrase encrypt(KeyTable key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Phrase encrypted = new Phrase();
        while (!this.isEmpty()) {
            Bigram b = this.dequeue();
            if (key.findRow(b.getFirstChar()) ==
                    key.findRow(b.getSecondChar())) {
                encryptRightShift(key, b);
            }
            else if (key.findCol(b.getFirstChar()) ==
                    key.findCol(b.getSecondChar())) {
                encryptBottomShift(key, b);
            }
            else {
                encryptRectangle(key, b);
            }
            encrypted.enqueue(b);
        }
        return encrypted;
    }

    public static char findDecryptedLeft(KeyTable k, char c) {
        int i = k.findRow(c);
        int j = k.findCol(c);
        if (j == 0) {
            j = k.getKeyTable()[0].length - 1;
        }
        else {
            j--;
        }
        return k.getKeyTable()[i][j];
    }


    public void decryptLeftShift(KeyTable k, Bigram b) {
        b.setFirst(findDecryptedLeft(k, b.getFirstChar()));
        b.setSecond(findDecryptedLeft(k, b.getSecondChar()));
    }


    public static char findDecryptedTop(KeyTable k, char c) {
        int i = k.findRow(c);
        if (i == 0) {
            i = k.getKeyTable().length - 1;
        }
        else {
            i--;
        }
        return k.getKeyTable()[i][k.findCol(c)];
    }

    public static void decryptTopShift(KeyTable k, Bigram b) {
        b.setFirst(findDecryptedTop(k, b.getFirstChar()));
        b.setSecond(findDecryptedTop(k, b.getSecondChar()));
    }

    public Phrase decrypt(KeyTable key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Phrase decrypted = new Phrase();
        while (!this.isEmpty()) {
            Bigram b = this.dequeue();
            if (key.findRow(b.getFirstChar()) != key.findRow(b.getSecondChar())
                    && key.findCol(b.getFirstChar()) != key.findCol(b.getSecondChar())){
                encryptRectangle(key, b);
            }
            else if (key.findCol(b.getFirstChar()) ==
                    key.findCol(b.getSecondChar())){
                decryptTopShift(key, b);
            }
            else {
                decryptLeftShift(key, b);
            }
            decrypted.enqueue(b);
        }
        return decrypted;
    }

    public String toString() {
        String str = "";
        Phrase temp = new Phrase();
        while (!this.isEmpty()) {
            str += ("" + this.peek().getFirstChar() +
                    this.peek().getSecondChar());
            temp.enqueue(this.dequeue());
        }
        while (!temp.isEmpty()) {
            this.enqueue(temp.dequeue());
        }
        return str;
    }

}
class KeyTable {
    private static final int MAX_ROWS = 5;
    private static final int MAX_COLUMNS = 5;
    private char[][] key = new char[MAX_ROWS][MAX_COLUMNS];
    Playfair playfair=new Playfair();


    public KeyTable() {
        char[][] key;
    }


    public static KeyTable buildFromString(String keyphrase) throws
            IllegalArgumentException {
        String newString = "";
        if (keyphrase == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < keyphrase.length(); i++) {
            if (!newString.contains(("" + keyphrase.charAt(i))) &&
                    Character.isLetter(keyphrase.charAt(i))) {
                newString += keyphrase.charAt(i);
            }
        }
        newString = newString.replaceAll("\\s", "");
        newString = newString.toUpperCase();
        String newString2 = "";
        for (int i = 0; i < newString.length(); i++) {
            if (newString.charAt(i) != 'J') {
                newString2 += newString.charAt(i) + "";
            }
        }
        for (int i = 1; i <= 26; i++) {
            if (i == 10) {
                continue;
            }
            
            else {
                if ((!newString2.contains("" + ((char) (i + 64)))) && i != 10) {
                    newString2 += ((char) (i + 64));
                }
            }
        }
        int indexOfNewString = 0;
        KeyTable newTable = new KeyTable();
        for (int i = 0; i < newTable.key.length; i++) {
            for (int j = 0; j < newTable.key[0].length; j++) {
                newTable.key[i][j] = newString2.charAt(indexOfNewString);
                indexOfNewString++;
            }
        }

        return newTable;
    }


    public char[][] getKeyTable() {
        return key;
    }


    public int findRow(char c) throws IllegalArgumentException {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                if (this.key[i][j] == c) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public int findCol(char c) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                if (this.key[i][j] == c) {
                    return j;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                returnString += "" + this.key[i][j];
                if (j != key[0].length - 1) {
                    returnString += " ";
                }
            }
            returnString += "\n";
        }
        return returnString;
    }

}
class Bigram {
    private char first, second;

    public Bigram() {
    }

    public Bigram(char f, char s) {
        first = f;
        second = s;
    }

    public String toString() {
        return first + second + "";
    }


    public char getFirstChar() {
        return first;
    }


    public char getSecondChar() {
        return second;
    }


    public void setFirst(char c) {
        first = c;
    }


    public void setSecond(char c) {
        second = c;
    }
}

