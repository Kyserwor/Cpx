import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * Created by rlukas on 28.01.2015.
 */
public class CpxMain {

    public static void main(String[] args) throws IOException {
        String tmp = null;
        if (args[2] != null){
            tmp = args[2];
        }
        if (checkParameter(args) != true){
            if (!loopResult (args[0], args[1], tmp))
                System.out.println ("Copy failed.");
        }else{
            System.out.println ("Wrong parameter.");
        } // if

    }



/**
    Methode checkParameter
    Checks if parameter are right
    */
    public static boolean checkParameter (String[] str){
        if (str.length < 2 || str.length > 3)
            return true;
        else
            return false;
    } // checkParameter


    /**
        Methode listDir
        Returns an Array of File-Objects
    */
    public static File[] listDir (File source){
        File[] files = source.listFiles();
        return files;
    } // listDir


    /**
        Methode filterFile
        Returns an name sorted File array
    */
    public static File[] filterFiles (File source, final String fileFilter){
        //  Abstrakten Filter überschreiben
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String tmp) {
                File newDir = new File (dir.getAbsolutePath() + "/" + tmp);
                tmp = fileFilter;
                if (newDir.isDirectory()) {
                    return true;
                }else{
                    String fileName = newDir.getName();
                    int index = fileName.lastIndexOf (".");
                    String endung = fileName.substring(index);
                    if (endung.equals(tmp)){
                        return true;
                    }else{
                        return false;
                    } // if - else
                } // if
            } //accept
        }; //fileNameFilter

        File[] fileArray  = source.listFiles(filter);
        return fileArray;
    }


    /**
        Methode loopResult
        Loops the CpxCopyFile execute methode
    */
    public static boolean loopResult (String fnSource, String fnDestination, String filter) throws IOException {
        boolean returnboolean = true;
        File source = new File (fnSource);
        File destination = new File (fnDestination);
        File[] sourceFileArray = null;

        if (filter != null){
            sourceFileArray = filterFiles(source, filter);
        }else{
            sourceFileArray = listDir (source);
        }

// 		try CpxCopyFile
        try{
            for (int i=0; i<sourceFileArray.length; ++i){
                File choosenFile = sourceFileArray[i];
                if (choosenFile.isDirectory()){
                    String newDestination = CpxCreateDir.createDir (destination, choosenFile);
                    String newSource = fnSource + "/" + choosenFile.getName();
                    loopResult (newSource, newDestination, filter);
                }else{
                    String fnNewSource = fnSource + "/" + choosenFile.getName();
                    CpxCopyFile.execute (fnNewSource, fnDestination);
                } // if
            } // for
        }catch (Exception e){
            // When error: return false
            returnboolean = false;
        } // try
// 		After copy is trough: return true
        return returnboolean;
    } // loopResult
}

