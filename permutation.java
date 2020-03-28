public class permutation {
    public static void permutation_encrypt(String key, String plaintext) {
        //Variability
        String cyphertext = "";
        /* end variability */

        int size = key.length();
        String[] stringKeys = key.split("");
        int[] keys = new int[size];
        //ruaj key ne nje int array
        for (int i = 0; i < size; i++) {
            keys[i] = Integer.parseInt(stringKeys[i]);
        }
        char x = 'x';
        plaintext = plaintext.replace(' ', x);

        String[] tokens = plaintext.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
            char[] tempToken = new char[size];

            for (int j = 0; j < tokens[i].length(); j++) {
                if (tokens[i].length() < size)
                    /* Per plotesim te karaktereve vendosim w*/
                    tokens[i] += 'w';

                tempToken[j] = tokens[i].charAt(keys[j] - 1);
            }
            cyphertext += String.valueOf(tempToken);
        }

        System.out.println(cyphertext);
        System.out.print("PlainText:  ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.print(tokens[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("key:        ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.print(key);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("Ciphertext: ");
        String[] ciferi = cyphertext.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < ciferi.length; i++) {
            System.out.print(ciferi[i] + " ");

        }
        System.out.println();
    }
    public static void permutation_decrypt(String key, String encrypted) {
        //Variability
        String decrypted = "";
        //end variability

        int size = key.length();
        String[] stringKeys = key.split("");
        int[] keys = new int[size];
        //ruaj key ne nje int array.
        for (int i = 0; i < size; i++) {
            keys[i] = Integer.parseInt(stringKeys[i]);
        }

        String[] tokens = encrypted.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
            char[] tempToken = new char[size];

            for (int j = 0; j < tokens[i].length(); j++) {
                tempToken[keys[j] - 1] = tokens[i].charAt(j);
            }
            decrypted += String.valueOf(tempToken);
        }
        decrypted = decrypted.replace('x', ' ');

        char[] decrCharArray = decrypted.toCharArray();
        for (int i = decrCharArray.length - 1; i >= 0; i--) {
            if (decrCharArray[i] == 'w') {
                decrCharArray[i] = '\u0000'; // ascii code per empty character (stackoverflow)
            }

            else {
                break;
            }

        }
        System.out.println(String.valueOf(decrCharArray));

    }
}
