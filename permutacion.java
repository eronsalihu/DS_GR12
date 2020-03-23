package ushtrimi1;

public class program {

	public static void main(String[] args) {
		permutation_encrypt("3142", "siguria");
		permutation_decrypt("3142", "gsuiarwi");
	}
	public static void permutation_encrypt(String key, String plaintext) {
        //Variability
		String cyphertext = "";
		//end variability
		
		int size = key.length();
        String[] stringKeys = key.split("");
        int [] keys = new int[size];
        //ruaj key ne nje int array
        for (int i = 0; i < size; i++) {
        	keys[i] = Integer.parseInt(stringKeys[i]);
		}
        
        
        String[] tokens = plaintext.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
        	char[] tempToken = new char[size];
        	
            for (int j = 0; j < tokens[i].length(); j++) {
                if (tokens[i].length() < size)         /* Per plotesim te karaktereve vendosim w*/
                    tokens[i] += 'w';
                if (Character.isWhitespace(tokens[i].charAt(j)))         //Per space vendosim x
                {
                    String str = tokens[i];
                    char ch = 'x';
                    int pos = j;

                    tokens[i] = str.substring(0, pos) + ch + str.substring(pos + 1);
                }
                tempToken[j] = tokens[i].charAt(keys[j]-1);
            }
            cyphertext += String.valueOf(tempToken);
        }
        System.out.println(cyphertext);
   
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
        System.out.println(size);

        String[] tokens = encrypted.split("(?<=\\G.{" + size + "})");
        for (int i = 0; i < tokens.length; i++) {
	        char[] tempToken = new char[size];
	        
	        for (int j = 0; j < tokens[i].length(); j++) {
	        	tempToken[keys[j]-1] = tokens[i].charAt(j);
	        }
	        decrypted += String.valueOf(tempToken); 
        }
		
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

	
	














}

