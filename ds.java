import java.util.*;

public class ds {
    System.out.println(args[0] + " " + args[1] + " " + args[2] + " " + args[3]);
        String a = args[0];
        String b = args[1];
        if ((a.equalsIgnoreCase("vigenere") || a.equalsIgnoreCase("permutation")
                || a.equalsIgnoreCase("playfair")) && (b.equalsIgnoreCase("encrypt")
                || b.equalsIgnoreCase("decrypt") || b.equalsIgnoreCase("encrypt_table")|| b.equalsIgnoreCase("decrypt_table"))) {

            switch (a) {
                case "vigenere":
                    if (b.equalsIgnoreCase("encrypt")) {
                        vigenere.vigenere_encrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt")) {
                        vigenere.vigenere_decrypt(args[2], args[3]);
                        break;
                    } else {
                        System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                    }
                    break;
                case "permutation":
                    if (b.equalsIgnoreCase("encrypt")) {
                        permutation.permutation_encrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt")) {
                        permutation.permutation_decrypt(args[2], args[3]);
                        break;
                    } else {
                        System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                    }
                    break;
                case "playfair":
                    if (b.equalsIgnoreCase("encrypt")) {
                        playFair.playfair_encrypt(args[2], args[3]);
                        break;
                    } else if(b.equalsIgnoreCase("encrypt_table")){
                        playFair.playyfair_tabela(args[2]);
                        playFair.playfair_encrypt(args[2], args[3]);
                        break;
                    }
                    else if (b.equalsIgnoreCase("decrypt")) {
                        playFair.playfair_decrypt(args[2], args[3]);
                        break;
                    }else if(b.equalsIgnoreCase("decrypt_table")){
                        playFair.playyfair_tabela(args[2]);
                        playFair.playfair_decrypt(args[2], args[3]);
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
}







