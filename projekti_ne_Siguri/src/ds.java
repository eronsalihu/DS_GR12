import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class ds {

    public static void main(String[] args) throws Exception, ParserConfigurationException, IOException, SAXException, InvalidPathException, FileNotFoundException {
        if (args.length == 0) {
            System.out.println("There were no commandline arguments passed!");
            System.exit(1);
        } else if (args.length == 1) {
            System.out.println("Komanda nuk eshte e kompletuar ose nuk eshte ne rregull");
            System.exit(1);
        }


        String a = args[0];
        String b = args[1];
        if (a.equalsIgnoreCase("create-user")) {
            if (args.length != 2) {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            } else if (b.matches("[A-Za-z0-9_]+")) {
                createUser.generate_key(b);
            } else {
                System.out.println("Argumenti i dyte mund te permbaje vetem shkronja numra ose underscore");
                System.exit(1);
            }


        } else if (a.equalsIgnoreCase("delete-user")) {
            if (args.length != 2) {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            } else deleteUser.delete(b);
        } else if (a.equalsIgnoreCase("import-key")) {
            if (args.length != 3) {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            } else if (b.matches("[A-Za-z0-9_]+")) {
                importKey.import_Key(b, args[2]);
            } else {
                System.out.println("Argumenti i dyte mund te permbaje vetem shkronja numra ose underscore");
                System.exit(1);
            }
        } else if (a.equalsIgnoreCase("export-key")) {
            if (args.length == 3) {
                exportKey.export_public_private(b, args[2]);
            } else if (args.length == 4) {
                exportKey.export_public_private(b, args[2], args[3]);
            } else {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            }
        } else if (a.equalsIgnoreCase("write-message")) {
            if (args.length == 3) {
                writeMessage.write_Message(b, args[2]);
            } else if (args.length == 4) {
                writeMessage.write_Message(b, args[2], args[3]);
            } else {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            }
        } else if (a.equalsIgnoreCase("read-message")) {
            if (args.length == 2) {
                readMessage.decrypt(b);
            } else {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);

            }
        } else if ((a.equalsIgnoreCase("vigenere") || a.equalsIgnoreCase("permutation") ||
                a.equalsIgnoreCase("playfair")) && (b.equalsIgnoreCase("encrypt") ||
                b.equalsIgnoreCase("decrypt") || b.equalsIgnoreCase("encrypt_table") || b.equalsIgnoreCase("decrypt_table"))) {

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
                    } else if (b.equalsIgnoreCase("encrypt_table")) {
                        playFair.playyfair_tabela(args[2]);
                        playFair.playfair_encrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt")) {
                        playFair.playfair_decrypt(args[2], args[3]);
                        break;
                    } else if (b.equalsIgnoreCase("decrypt_table")) {
                        playFair.playyfair_tabela(args[2]);
                        playFair.playfair_decrypt(args[2], args[3]);
                        break;
                    } else {
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