import java.util.*;

public class playFair {
    static List< Digraph > cdigraphs = new LinkedList< Digraph >();
    static List < Digraph > ddigraphs = new LinkedList < Digraph > ();
    static char[][] keytable = new char[5][5];
    static Hashtable< Character,
            Integer > rowval = new Hashtable < Character,
            Integer > ();
    static Hashtable < Character,
            Integer > colval = new Hashtable < Character,
            Integer > ();
    public static void playyfair_tabela(String key){
        buildKeyTable(key);
        System.out.println("TABELA PLAYFAIR");
        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {
                System.out.print(keytable[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void playfair_encrypt(String key, String plaintext) {
        buildKeyTable(key);
        buildDigraphList(plaintext);
        encryptDigraphList();
        System.out.println("Encrypted message:");
        for (Digraph d: ddigraphs) {
            System.out.print(d.c1);
            System.out.print(d.c2 + " ");
        }
        System.out.println();

    }

    public static void playfair_decrypt(String key, String cipherText) {
        buildKeyTable(key);
        buildDigraphList(cipherText);
        decryptDigraphList();

        System.out.println("Decrypted message:");
        for (Digraph d: ddigraphs) {
            System.out.print(d.c1);
            System.out.print(d.c2);
        }
        System.out.println();

    }

    private static void buildKeyTable(String keyword) {
        keyword = keyword.toUpperCase();
        keyword = keyword.replaceAll("J", "I");
        keyword = keyword.replaceAll("\\s+", "");
        keyword = keyword.replaceAll("[^a-zA-Z]", "");
        keyword = keyword.concat("ABCDEFGHIKLMNOPQRSTUVWXYZ");
        keyword = removeDuplicates(keyword);

        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                keytable[i][j] = keyword.charAt(k);
                rowval.put(keyword.charAt(k), i);
                colval.put(keyword.charAt(k), j);
                k++;
            }
        }
    }
    private static void buildDigraphList(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        ciphertext = ciphertext.replaceAll("\\s+", "");
        ciphertext = ciphertext.replaceAll("J", "I");
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");

        char last = 7;
        char curr;
        StringBuilder sb = new StringBuilder();

        int k = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            curr = ciphertext.charAt(i);
            if (curr == last && k % 2 == 1) {
                sb.append('X');
                k++;
                sb.append(curr);
                k++;
            }
            else {
                sb.append(curr);
                k++;
            }
            last = ciphertext.charAt(i);
        }

        String newtext = sb.toString();
        if (newtext.length() % 2 == 1) {
            newtext = newtext + "X";
        }

        for (int i = 0; i < newtext.length(); i = i + 2) {
            Digraph toadd = new Digraph(newtext.charAt(i), newtext.charAt(i + 1));
            cdigraphs.add(toadd);
        }
    }
    private static void encryptDigraphList() {
        char one;
        int rowone = -1;
        int colone = -1;
        char two;
        int rowtwo = -2;
        int coltwo = -2;

        for (Digraph d: cdigraphs) {
            one = d.c1;
            two = d.c2;

            rowone = rowval.get(one);
            colone = colval.get(one);
            rowtwo = rowval.get(two);
            coltwo = colval.get(two);

            Digraph newd = new Digraph();
            if (rowone == rowtwo) {

                newd.c1 = keytable[rowone][(colone + 1) % 5];
                newd.c2 = keytable[rowtwo][(coltwo + 1) % 5];

            }

            else if (colone == coltwo) {

                if (rowone == 4 && rowtwo != 4) {
                    newd.c1 = keytable[0][colone];
                    newd.c2 = keytable[(rowtwo + 1) % 5][coltwo];
                }
                else if (rowone != 4 && rowtwo == 4) {
                    newd.c1 = keytable[(rowone + 1) % 5][colone];
                    newd.c2 = keytable[0][coltwo];
                }
                else if (rowone == 4 && rowtwo == 4) {
                    newd.c1 = keytable[0][colone];
                    newd.c2 = keytable[0][coltwo];
                }
                else {
                    newd.c1 = keytable[(rowone + 1) % 5][colone];
                    newd.c2 = keytable[(rowtwo + 1) % 5][coltwo];
                }

            }

            else {

                newd.c1 = keytable[rowone][coltwo];
                newd.c2 = keytable[rowtwo][colone];
            }

            ddigraphs.add(newd);
        }

    }
    private static void decryptDigraphList() {
        char one;
        int rowone = -1;
        int colone = -1;
        char two;
        int rowtwo = -2;
        int coltwo = -2;

        for (Digraph d: cdigraphs) {
            one = d.c1;
            two = d.c2;

            rowone = rowval.get(one);
            colone = colval.get(one);
            rowtwo = rowval.get(two);
            coltwo = colval.get(two);

            Digraph newd = new Digraph();
            if (rowone == rowtwo) {
                if (colone == 0 && coltwo == 0) {
                    newd.c1 = keytable[rowone][4];
                    newd.c2 = keytable[rowtwo][4];
                }
                else if (colone == 0 && coltwo != 0) {
                    newd.c1 = keytable[rowone][4];
                    newd.c2 = keytable[rowtwo][(coltwo - 1) % 5];
                }
                else if (colone != 0 && coltwo == 0) {
                    newd.c1 = keytable[rowone][(colone - 1) % 5];
                    newd.c2 = keytable[rowtwo][4];
                }
                else {
                    newd.c1 = keytable[rowone][(colone - 1) % 5];
                    newd.c2 = keytable[rowtwo][(coltwo - 1) % 5];
                }

            }

            else if (colone == coltwo) {

                if (rowone == 0 && rowtwo != 0) {
                    newd.c1 = keytable[4][colone];
                    newd.c2 = keytable[(rowtwo - 1) % 5][coltwo];
                }
                else if (rowone != 0 && rowtwo == 0) {
                    newd.c1 = keytable[(rowone - 1) % 5][colone];
                    newd.c2 = keytable[4][coltwo];
                }
                else if (rowone == 0 && rowtwo == 0) {
                    newd.c1 = keytable[4][colone];
                    newd.c2 = keytable[4][coltwo];
                }
                else {
                    newd.c1 = keytable[(rowone - 1) % 5][colone];
                    newd.c2 = keytable[(rowtwo - 1) % 5][coltwo];
                }

            }

            else {

                newd.c1 = keytable[rowone][coltwo];
                newd.c2 = keytable[rowtwo][colone];

            }

            ddigraphs.add(newd);
        }

    }
    private static String removeDuplicates(String str) {

        Set< Character > set = new LinkedHashSet< Character >();

        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (Character character: set) {
            sb.append(character);
        }

        return sb.toString();
    }
}
class Digraph {
    public char c1;
    public char c2;
    public int row;
    public int col;

    public Digraph(char c1, char c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Digraph() {}
}

