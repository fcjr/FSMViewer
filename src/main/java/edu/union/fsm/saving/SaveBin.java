/**
* Saves a seralized model class to a file.
*/

package edu.union.fsm.saving;

import javax.swing.*;
import java.io.*;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveBin {

    private class SerialChooser extends JFileChooser implements Serializable{}

    private Object toSave;
    private JFrame frameToPrompt;

    public SaveBin(Object toSave, JFrame frameToPrompt){
        this.toSave = toSave;
        this.frameToPrompt = frameToPrompt;
    }

    public void saveFile(){
        try {

            JFileChooser c = new JFileChooser();

            FileFilter filter = new FileNameExtensionFilter("Bin Files","bin");
            c.setFileFilter(filter);

            int returnVal = c.showSaveDialog(frameToPrompt);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = c.getSelectedFile();

                //ENSURE BIN FILE
                if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("bin")) {
                        //DO NOTHING FILE IS GOOD
                } else {
                    //replace file extension if is wrong
                    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".bin");
                }


                FileOutputStream fs = new FileOutputStream(file);

                ObjectOutputStream os = new ObjectOutputStream(fs);

                os.writeObject(toSave);

                os.close();

                fs.close();

            }
            else if (returnVal == JFileChooser.CANCEL_OPTION) {
                //DO NOTHING, close pane
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
