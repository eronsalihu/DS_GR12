import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class deleteUser {
    public static void delete(String outFile){
        File file=new File("keys/", outFile + ".xml");
        File file1=new File("keys/",outFile+".pub.xml");

            try {
                if (file.exists() && file1.exists()) {
                    file.delete();
                    file1.delete();
                    System.out.println("Eshte larguar celesi privat 'keys/" + outFile + ".xml'");
                    System.out.println("Eshte larguar celesi privat 'keys/" + outFile + ".pub.xml'");


                }
                else if  (file.exists() && !file1.exists()){
                    file.delete();
                    System.out.println("Eshte larguar celesi privat 'keys/" + outFile + ".xml'");
                }
                else if  (!file.exists() && file1.exists()){
                    file1.delete();
                    System.out.println("Eshte larguar celesi privat 'keys/" + outFile + ".pub.xml'");
                }
                else {
                    System.out.println("Gabim:Celesi '"+outFile+"' nuk ekziston");
                }



            } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

