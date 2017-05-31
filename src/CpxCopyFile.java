/**
 * Created by rlukas on 28.01.2015.
 */
/*
	Class CpxCopyFile
	Copies a File from a existing directory to another existing directory.
*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class CpxCopyFile{
    public static void execute (String source, String destination) throws IOException{
        File fileSource = new File (source);
        File fileDestination = new File (destination);
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        int count = 0;

        try{
            fileDestination.createNewFile();
            fileDestination.renameTo (fileSource);

            String strNewDest = destination + "/" + fileSource.getName();
            File fileNewDest = new File (strNewDest);

            in = new BufferedInputStream (new FileInputStream (fileSource));
            out = new BufferedOutputStream (new FileOutputStream (fileNewDest));

            byte[] buffer = new byte[64];
            int charsGelesen;
            /** Lesevorgang */
            charsGelesen = in.read(buffer);
            while (charsGelesen != -1) {
                /** Inhalt des Lesevorgangs in Zieldatei
                 *  schreiben */
                out.write(buffer, 0, charsGelesen);
                count += charsGelesen;
                charsGelesen = in.read(buffer);

            }

            System.out.print ("File: " + fileNewDest.getName() + " copied to " + fileNewDest.getAbsolutePath() + " ");
            System.out.println (count + " Bytes.");
            System.out.println (" ");
        }catch (Exception e){
            e.printStackTrace();
        } // try - catch

        if (in != null) {
            in.close();
        } // if
        if (out != null) {
            out.close();
        } // if
    } // execute
}
