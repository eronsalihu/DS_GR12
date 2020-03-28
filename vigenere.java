public class vigenere {
    public static void vigenere_encrypt(String key, String plaintext) {
        char ch;
        int x = plaintext.length();
        String encriptuara = "";
        int c = 0;
        // logjika e perseritjes se qelesit nga GeeksForGeeks
        for (int i = 0;; i++) {
            if (x == i) i = 0;
            if (key.length() == plaintext.length()) break;
            key += (key.charAt(i));
        }

        for (int i = 0; i < plaintext.length(); i++) {
            if (Character.isWhitespace(plaintext.charAt(i))) {
                ch = ' ';
                encriptuara += ch;
                c++;

            } else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                int b = key.charAt(i - c) - 'a';
                ch = (char)(plaintext.charAt(i) + b);
                if (ch > 'z') {
                    ch = (char)(ch - 'z' + 'a' - 1); //GeeksForGeeks ne logjiken e modulos
                }
                encriptuara += ch;
            } else if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                char a = Character.toUpperCase(key.charAt(i - c));
                int d = a - 'A';
                ch = (char)(plaintext.charAt(i) + d);
                if (ch > 'Z') {
                    ch = (char)(ch - 'Z' + 'A' - 1);
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

        for (int i = 0;; i++) {
            if (x == i) i = 0;
            if (key.length() == encrypted.length()) break;
            key += (key.charAt(i));
        }

        for (int i = 0; i < encrypted.length(); i++) {
            if (Character.isWhitespace(encrypted.charAt(i))) {
                ch = ' ';
                decriptuara += ch;
                c++;

            } else if (encrypted.charAt(i) >= 'a' && encrypted.charAt(i) <= 'z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {

                int b = key.charAt(i - c) - 'a';

                ch = (char)(encrypted.charAt(i) - b);
                if (ch < 'a') {
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                decriptuara += ch;
            } else if (encrypted.charAt(i) >= 'A' && encrypted.charAt(i) <= 'Z' && key.charAt(i - c) >= 'a' && key.charAt(i - c) <= 'z') {
                char a = Character.toUpperCase(key.charAt(i - c));
                int d = a - 'A';
                ch = (char)(encrypted.charAt(i) - d);
                if (ch < 'A') {
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }
                decriptuara += ch;
            } else if (encrypted.charAt(i) < 'a' || encrypted.charAt(i) > 'Z') {
                decriptuara += encrypted.charAt(i);
                c++;

            }

        }
        System.out.println(decriptuara);
    }
}
