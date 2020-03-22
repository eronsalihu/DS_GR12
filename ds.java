import java.util.Arrays;

public class ds {
    public static void main(String[] args){
        System.out.println(args[0]+" "+args[1]+" "+args[2]+" "+args[3]);
        String a=args[0];
        String b=args[1];
        switch (a){
            case "vigenere":
                if (b.equalsIgnoreCase("encrypt"))
                {vigenere_encrypt(args[2],args[3]);
                break;
                }
                else if (b.equalsIgnoreCase("decrypt")){
                    vigenere_decrypt(args[2],args[3]);
                    break;
                }
                else {
                    System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                }
                break;
            case "permutation":
                if(b.equalsIgnoreCase("encrypt")){
                    permutation_encrypt(args[2],args[3]);
                    break;
                }
                else if(b.equalsIgnoreCase("decrypt")){
                    permutacion_decrypt(args[2],args[3]);
                    break;
                }
                else {
                    System.out.println("Duhet qe fjala e dyte te jete encrypt ose decrypt");

                }
                break;


            default:System.out.println("Veprim i gabuar");
        }
    }
    public static  void vigenere_encrypt(String key,String plaintext){
    char ch;
    int x=plaintext.length();
    String encriptuara="";
    int c=0;
// logjika e perseritjes se qelesit nga GeeksForGeeks
        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == plaintext.length())
                break;
            key+=(key.charAt(i));
        }

        for (int i=0;i<plaintext.length();i++){
            if (Character.isWhitespace(plaintext.charAt(i)))
            { ch=' ';
            encriptuara+=ch;
            c++;

            }

            else if(plaintext.charAt(i)>='a' && plaintext.charAt(i)<='z' && key.charAt(i-c)>='a' && key.charAt(i-c)<='z'){
                int b=key.charAt(i-c)-'a';
                ch = (char)(plaintext.charAt(i)+b);
                    if(ch>'z'){
                        ch = (char)(ch - 'z' + 'a' - 1); //GeeksForGeeks ne logjiken e modulos
                    }
                encriptuara+=ch;}
                    else if(plaintext.charAt(i)>='A' && plaintext.charAt(i)<='Z' && key.charAt(i-c)>='a' && key.charAt(i-c)<='z'){
                        char a=Character.toUpperCase(key.charAt(i-c));
                        int d=a-'A';
                        ch=(char)(plaintext.charAt(i)+d);
                        if(ch>'Z'){
                            ch=(char)(ch-'Z'+'A'-1);
                        }
                        encriptuara+=ch;

                }
                    else if(plaintext.charAt(i)<'a' || plaintext.charAt(i)>'Z' )
            {  encriptuara+=plaintext.charAt(i);
            c++;

            }
            }


        System.out.println(encriptuara);

    }
    public static void vigenere_decrypt(String key,String encrypted)
    {
        char ch;
        int x=encrypted.length();
        String decriptuara="";
        int c=0;

        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == encrypted.length())
                break;
            key+=(key.charAt(i));
        }

        for (int i=0;i<encrypted.length();i++){
            if (Character.isWhitespace(encrypted.charAt(i)))
            { ch=' ';
                decriptuara+=ch;
                c++;

            }
            else if(encrypted.charAt(i)>='a' && encrypted.charAt(i)<='z' && key.charAt(i-c)>='a' && key.charAt(i-c)<='z'){


                int b=key.charAt(i-c)-'a';

                ch = (char)(encrypted.charAt(i)-b);
                if (ch<'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                decriptuara+=ch;
            }
            else if(encrypted.charAt(i)>='A' && encrypted.charAt(i)<='Z' && key.charAt(i-c)>='a' && key.charAt(i-c)<='z'){
                char a=Character.toUpperCase(key.charAt(i-c));
                int d=a-'A';
                ch=(char)(encrypted.charAt(i)-d);
                if (ch<'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }
                decriptuara+=ch;
            }
            else if(encrypted.charAt(i)<'a' || encrypted.charAt(i)>'Z' )
            {  decriptuara+=encrypted.charAt(i);
                c++;

            }


        }
        System.out.println(decriptuara);
    }



    public static void permutation_encrypt(String key,String plaintext){
      int size=key.length();

        String[] tokens = plaintext.split("(?<=\\G.{" + size + "})");
        for(int i=0;i<tokens.length;i++)
        {  if(tokens[i].length()<size)         // Per plotesim te karaktereve vendosim w
            tokens[i]+='w';
        for(int j=0;j<tokens[i].length();j++){
            if (Character.isWhitespace(tokens[i].charAt(j)))         //Per space vendosim x
            {	String str = tokens[i];
                char ch = 'x';
                int pos = j;

                tokens[i] = str.substring(0, pos) + ch + str.substring(pos + 1);}
        }
        }
for (int i=0;i<tokens.length;i++){
    for(int j=0;j<tokens[i].length();j++){

    }
}

System.out.print("PlainText:");
        for(int i=0;i<tokens.length;i++)
        {
            System.out.print(tokens[i]);
            System.out.print(" ");
        }
        System.out.println();
        System.out.print("key:      ");
        for(int i=0;i<tokens.length;i++)
        {
            System.out.print(key);
            System.out.print(" ");
        }
    }

    public static void permutacion_decrypt(String key,String cypher){

    }
    }

