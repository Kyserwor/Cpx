/**
 * Created by rlukas on 28.01.2015.
 */

    import java.io.File;

    public class CpxCreateDir{
        public static String createDir (File destination, File namePath){
            String returnStr = destination + "/" + namePath.getName();
            File newDest = new File (returnStr);
            newDest.mkdirs();
            newDest.renameTo (namePath);
            System.out.println ("\tCreated directory: " + returnStr);
            System.out.println (" ");
            return returnStr;
        }
    }

