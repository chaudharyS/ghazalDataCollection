 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghazaldatacollection;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author Sanatt Abrol (PRO GSOC :P)
 */
public class FileHandle {
    public String getPathToRunnable() throws URISyntaxException{
        return new File(XMLClass.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getAbsolutePath();
    }
    public boolean CheckForFileInParentDirectory(String subFolder,String fileName){
        String directoryPath;
        try {
            directoryPath = getPathToRunnable() + File.separator + subFolder;
            return new File(new File(directoryPath), fileName).exists();
        } catch (URISyntaxException ex) {
            return true;
        }
    }
    
    public boolean CheckForFolderInParentDirectory(String subFolder){
        try {
            String dirName = getPathToRunnable() +File.separator + subFolder;
            return Files.isDirectory(Paths.get(dirName));
        } catch (URISyntaxException ex) {
            return true;
        }
    }
    
    public void CreateSubFolder(String rootDirectory, String subFolder){
        new File(rootDirectory + File.separator + subFolder).mkdir();
    }
}
