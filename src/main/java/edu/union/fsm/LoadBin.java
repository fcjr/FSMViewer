/**
* Loads a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadBin {

    private JFrame toPrompt;

    public LoadBin(JFrame toPrompt){
        this.toPrompt = toPrompt;
    }

    public Object loadFile(){
        try {

            Object toReturn = null;

            JFileChooser c = new JFileChooser();

            FileFilter filter = new FileNameExtensionFilter("Bin Files","bin");
            c.setFileFilter(filter);

            int returnVal = c.showOpenDialog(toPrompt);

            if (returnVal == JFileChooser.APPROVE_OPTION) {


                File file = c.getSelectedFile();

                FileInputStream fs = new FileInputStream(file);

                ObjectInputStream os = new ObjectInputStream(fs);

                toReturn = os.readObject();


                os.close();

                fs.close();


            }
            if (returnVal == JFileChooser.CANCEL_OPTION) {
                //DO NOTHING
            }

            return toReturn;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
