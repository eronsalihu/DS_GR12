import java.util.*;

public class ds {
    static List<Digraph> cdigraphs = new LinkedList<Digraph>();
    static List<Digraph> ddigraphs = new LinkedList<Digraph>();
    static char[][] keytable = new char[5][5];
    static Hashtable<Character, Integer> rowval = new Hashtable<Character,Integer>();
    static Hashtable<Character, Integer> colval = new Hashtable<Character,Integer>();
    public static void main(String[] args) {
        System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3]);
        String a = args[0];
        String b = args[1];
        if ((a.equalsIgnoreCase("vigenere") || a.equalsIgnoreCase("permutation") || a.equalsIgnoreCase("playfair"))
                && (b.equalsIgnoreCase("encrypt") || b.equalsIgnoreCase("decrypt"))) {

            switch (a) {
                case "vigenere":
                    if (b.equalsIgnoreCase("encrypt")) {
                        vigenere_encrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt")) {
                        vigenere_decrypt(args[2], args[3]);
                        break;
                    } else {
                        System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                    }
                    break;
                case "permutation":
                    if (b.equalsIgnoreCase("encrypt")) {
                        permutation_encrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt")) {
                        permutation_decrypt(args[2], args[3]);
                        break;
                    } else {
                        System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                    }
                    break;
                case "playfair":
                    if (b.equalsIgnoreCase("encrypt")) {
                            playfair_encrypt(args[2],args[3]);
                        break;
                    }
                    else if (b.equalsIgnoreCase("decrypt")){
                            playfair_decrypt(args[2],args[3]);
                        break;
                    }
                    else {
                        System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");
                    }
                    break;

                default:
                    System.out.println("Veprim i gabuar");
            }
        } else {
            System.out.println("Metoda e zgjedhur e cipher codit eshte gabim");
            System.out.println("Argumenti i pare duhet  te jete vigenere ose permutation ose playfair");
            System.out.println("Duhet zgjedhur ne argumentin e dyte enkriptimi ose dekriptimi");
            System.out.println("Argumenti i trete nuk duhet te jete i zbrazet permban qelsin e enkriptimit");
            System.out.print("Argumenti i 4 permban plaintextin ose ciphertextin te cilin deshironi ta deshifroni");
            System.exit(0);
        }

    }

    public static void vigenere_encrypt(String key, String plaintext) {
        char ch;
        int x = plaintext.length();
        String encriptuara = "";
        int c = 0;
// logjika e perseritjes se qelesit nga GeeksForGeeks
        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == plaintext.length())
                break;
            key += (key.charAt(i));
        }

        for (int i = 0; i < plaintext.length(); i++) {
            if (Character.isWhitespace(plaintext.charAt(i))) {
                ch = ' ';
                encriptuara += ch;
                c++;

            } else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                int b = key.charAt(i - c) - 'a';
                ch = (char) (plaintext.charAt(i) + b);
                if (ch > 'z') {
                    ch = (char) (ch - 'z' + 'a' - 1); //GeeksForGeeks ne logjiken e modulos
                }
                encriptuara += ch;
            } else if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                char a = Character.toUpperCase(key.charAt(i - c));
                int d = a - 'A';
                ch = (char) (plaintext.charAt(i) + d);
                if (ch > 'Z') {
                    ch = (char) (ch - 'Z' + 'A' - 1);
                }
                encriptuara += ch;

            } else if (plaintext.charAt(i) < 'a' || plaintext.charAt(i) > 'Z') {
                encriptuara += plaintext.charAt(i);
                c++;

            }
        }


        System.out.println(encriptuara);

    }

    public static void vigenere_decrypt(String key, String encrypted) {
        char ch;
        int x = encrypted.length();
        String decriptuara = "";
        int c = 0;

        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == encrypted.length())
                break;
            key += (key.charAt(i));
        }

        for (int i = 0; i < encrypted.length(); i++) {
            if (Character.isWhitespace(encrypted.charAt(i))) {
                ch = ' ';
                decriptuara += ch;
                c++;

            } else if (encrypted.charAt(i) >= 'a' && encrypted.charAt(i) <= 'z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {


                int b = key.charAt(i - c) - 'a';

                ch = (char) (encrypted.charAt(i) - b);
                if (ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }
                decriptuara += ch;
            } else if (encrypted.charAt(i) >= 'A' && encrypted.charAt(i) <= 'Z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                char a = Character.toUpperCase(key.charAt(i - c));
                int d = a - 'A';
                ch = (char) (encrypted.charAt(i) - d);
                if (ch < 'A') {
                    ch = (char) (ch + 'Z' - 'A' + 1);
                }
                decriptuara += ch;
            } else if (encrypted.charAt(i) < 'a' || encrypted.charAt(i) > 'Z') {
                decriptuara += encrypted.charAt(i);
                c++;

            }


        }
        System.out.println(decriptuara);
    }


    public static void permutation_encrypt(String key, String plaintext) {
        //Variability
        String cyphertext = "";
        /* end variability */

        int size = key.length();
        String[] stringKeys = key.split("");
        int [] keys = new int[size];
        //ruaj key ne nje int array
        for (int i = 0; i < size; i++) {
            keys[i] = Integer.parseInt(stringKeys[i]);
        }
        char x='x';
        plaintext=plaintext.replace(' ',x);

        String[] tokens = plaintext.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
            char[] tempToken = new char[size];

            for (int j = 0; j < tokens[i].length(); j++) {
                if (tokens[i].length() < size)         /* Per plotesim te karaktereve vendosim w*/
                    tokens[i] += 'w';

                tempToken[j] = tokens[i].charAt(keys[j]-1);
            }
            cyphertext += String.valueOf(tempToken);
        }

        System.out.println(cyphertext);
        System.out.print("PlainText:  ");
        for(int i=0;i<tokens.length;i++)
        {
            System.out.print(tokens[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("key:        ");
        for(int i=0;i<tokens.length;i++)
        {
            System.out.print(key);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Ciphertext: ");
        String[] ciferi = cyphertext.split("(?<=\\G.{" + size + "})");
        for (int i=0;i<ciferi.length;i++){
            System.out.print(ciferi[i]+" ");

        }
        System.out.println();
    }
    public static void permutation_decrypt(String key, String encrypted) {
        //Variability
        String decrypted= "";
        //end variability

        int size = key.length();
        String[] stringKeys = key.split("");
        int [] keys = new int[size];
        //ruaj key ne nje int array.
        for (int i = 0; i < size; i++) {
            keys[i] = Integer.parseInt(stringKeys[i]);
        }


        String[] tokens = encrypted.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
            char[] tempToken = new char[size];

            for (int j = 0; j < tokens[i].length(); j++) {
                tempToken[keys[j]-1] = tokens[i].charAt(j);
            }
            decrypted += String.valueOf(tempToken);
        }
        decrypted=decrypted.replace('x',' ');

        char[] decrCharArray = decrypted.toCharArray();
        for(int i=decrCharArray.length-1; i >= 0; i--) {
            if(decrCharArray[i] == 'w')
            {
                decrCharArray[i] = '\u0000'; // ascii code per empty character (stackoverflow)
            }

            else
            {
                break;
            }

        }
        System.out.println(String.valueOf(decrCharArray));


    }
    public static void playfair_encrypt(String key,String plaintext){
        buildKeyTable(key);
        buildDigraphList(plaintext);
        encryptDigraphList();
        System.out.println("Encrypted message:");
        for(Digraph d : ddigraphs) {
            System.out.print(d.c1);
            System.out.print(d.c2+" ");
        }
System.out.println();
System.out.println("TABELA PLAYFAIR");
        for(int i=0;i<5;i++) {

            for(int j=0;j<5;j++) {
                System.out.print(keytable[i][j]+" ");
            }
            System.out.println();
        }




    }

    public static void playfair_decrypt(String key,String cipherText){
        buildKeyTable(key);
        buildDigraphList(cipherText);
        decryptDigraphList();

        System.out.println("Decrypted message:");
        for(Digraph d : ddigraphs) {
            System.out.print(d.c1);
            System.out.print(d.c2);
        }
        System.out.println();
        System.out.println("TABELA PLAYFAIR");
        for(int i=0;i<5;i++) {

            for(int j=0;j<5;j++) {
                System.out.print(keytable[i][j]+" ");
            }
            System.out.println();
        }


    }



    private static void buildKeyTable(String keyword) {
        keyword = keyword.toUpperCase();
        keyword = keyword.replaceAll("J","I");
        keyword = keyword.replaceAll("\\s+","");
        keyword = keyword.replaceAll("[^a-zA-Z]", "");
        keyword = keyword.concat("ABCDEFGHIKLMNOPQRSTUVWXYZ");
        keyword = removeDuplicates(keyword);

        int k = 0;
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                keytable[i][j] = keyword.charAt(k);
                rowval.put(keyword.charAt(k), i);
                colval.put(keyword.charAt(k), j);
                k++;
            }
        }
    }
    private static void buildDigraphList(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        ciphertext = ciphertext.replaceAll("\\s+","");
        ciphertext = ciphertext.replaceAll("J","I");
        ciphertext = ciphertext.replaceAll("[^a-zA-Z]", "");

        char last = 7;
        char curr;
        StringBuilder sb = new StringBuilder();

        int k = 0;
        for(int i=0; i<ciphertext.length(); i++) {
            curr = ciphertext.charAt(i);
            if(curr == last && k%2==1) {
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
        if(newtext.length()%2 == 1) {
            newtext = newtext + "X";
        }

        for(int i=0; i<newtext.length(); i=i+2) {
            Digraph toadd = new Digraph(newtext.charAt(i),newtext.charAt(i+1));
            cdigraphs.add(toadd);
        }
    }
    private static void encryptDigraphList() {
        char one;
        int rowone = -1;
        int colone =-1;
        char two;
        int rowtwo =-2;
        int coltwo =-2;

        for(Digraph d:cdigraphs) {
            one = d.c1;
            two = d.c2;

            rowone = rowval.get(one);
            colone = colval.get(one);
            rowtwo = rowval.get(two);
            coltwo = colval.get(two);

            Digraph newd = new Digraph();
            if(rowone == rowtwo) {

                newd.c1 = keytable[rowone][(colone+1)%5];
                newd.c2 = keytable[rowtwo][(coltwo+1)%5];

            }

            else if(colone == coltwo) {

                if(rowone == 4 && rowtwo != 4) {
                    newd.c1 = keytable[0][colone];
                    newd.c2 = keytable[(rowtwo+1)%5][coltwo];
                }
                else if(rowone != 4 && rowtwo == 4) {
                    newd.c1 = keytable[(rowone+1)%5][colone];
                    newd.c2 = keytable[0][coltwo];
                }
                else if(rowone == 4 && rowtwo == 4) {
                    newd.c1 = keytable[0][colone];
                    newd.c2 = keytable[0][coltwo];
                }
                else {
                    newd.c1 = keytable[(rowone+1)%5][colone];
                    newd.c2 = keytable[(rowtwo+1)%5][coltwo];
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
        int colone =-1;
        char two;
        int rowtwo =-2;
        int coltwo =-2;

        for(Digraph d:cdigraphs) {
            one = d.c1;
            two = d.c2;

            rowone = rowval.get(one);
            colone = colval.get(one);
            rowtwo = rowval.get(two);
            coltwo = colval.get(two);


            Digraph newd = new Digraph();
            if(rowone == rowtwo) {
                if(colone == 0 && coltwo == 0) {
                    newd.c1 = keytable[rowone][4];
                    newd.c2 = keytable[rowtwo][4];
                }
                else if (colone == 0 && coltwo != 0) {
                    newd.c1 = keytable[rowone][4];
                    newd.c2 = keytable[rowtwo][(coltwo-1)%5];
                }
                else if (colone != 0 && coltwo == 0) {
                    newd.c1 = keytable[rowone][(colone-1)%5];
                    newd.c2 = keytable[rowtwo][4];
                }
                else {
                    newd.c1 = keytable[rowone][(colone-1)%5];
                    newd.c2 = keytable[rowtwo][(coltwo-1)%5];
                }

            }

            else if(colone == coltwo) {

                if(rowone == 0 && rowtwo != 0) {
                    newd.c1 = keytable[4][colone];
                    newd.c2 = keytable[(rowtwo-1)%5][coltwo];
                }
                else if(rowone != 0 && rowtwo == 0) {
                    newd.c1 = keytable[(rowone-1)%5][colone];
                    newd.c2 = keytable[4][coltwo];
                }
                else if(rowone == 0 && rowtwo == 0) {
                    newd.c1 = keytable[4][colone];
                    newd.c2 = keytable[4][coltwo];
                }
                else {
                    newd.c1 = keytable[(rowone-1)%5][colone];
                    newd.c2 = keytable[(rowtwo-1)%5][coltwo];
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

        Set<Character> set = new LinkedHashSet<Character>();

        for(int i=0; i< str.length(); i++) {
            set.add(str.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for(Character character: set) {
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






