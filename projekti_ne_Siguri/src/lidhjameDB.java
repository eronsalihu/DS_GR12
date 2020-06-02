import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class lidhjameDB {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //shto("Ani","Berisha");
        //fshi("Ani");
        verifiko("Ani","Berisha");

    }


        public static void shto(String a,String b){
            String algorithm="SHA-256";
            String url="jdbc:mysql://localhost:3306/ds_12";
            String name="root";
            String password="E.berisha1";
            try {
                Connection con= DriverManager.getConnection(url,name,password);
                String hashi=toHexString(getSHA(b));
                String kerko="Select * from users where username='"+a+"'";
                Statement s = con.createStatement();
                ResultSet rs=s.executeQuery(kerko);
                if (rs.next()==false){

                String sql="Insert into users (username,password)"+ "VALUES ('"+a+"','"+hashi+"')";
                PreparedStatement stmt = con.prepareStatement(sql);
                // stmt.setString(1, a);
                // stmt.setString(2, b);
                stmt.executeUpdate(sql);
                System.out.println("Eshte krijuar shfrytezuesi "+a);}
                else
                    System.out.print("");

            }
            catch (SQLException err){
                System.out.println(err.getMessage());
            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown for incorrect algorithm: " + e);
            }


        }
        public static void fshi(String a){
            String url="jdbc:mysql://localhost:3306/ds_12";
            String name="root";
            String password="E.berisha1";
            try {
                Connection con = DriverManager.getConnection(url, name, password);
                String fshi = "Delete from users where username='" + a + "'";
                PreparedStatement stmt = con.prepareStatement(fshi);
                stmt.executeUpdate(fshi);

            }
            catch (SQLException err){
                System.out.println(err.getMessage());
            }

        }

    public static void verifiko(String a,String b) throws NoSuchAlgorithmException {
        String pwHash=toHexString(getSHA(b));
        String url="jdbc:mysql://localhost:3306/ds_12";
        String name="root";
        String password="E.berisha1";
        try {
            Connection con = DriverManager.getConnection(url, name, password);
            String verifiko="Select * from users where username='"+a+"' and password='"+pwHash+"'";
            Statement s = con.createStatement();
            ResultSet rs=s.executeQuery(verifiko);
            if (rs.next()==false){
                System.out.println("Gabim: Shfrytezuesi ose fjalekalimi i gabuar.");
            }
            else
            {
              //  logIn.login(a);
                System.out.println("a");
            }
        }
        catch (SQLException err){
            System.out.println(err.getMessage());
        }


    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

}


