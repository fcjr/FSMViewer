/**
* Loads a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadBin {

    private Model model;
    private View view;

    public LoadBin(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void loadFile(){
        try {

            JFileChooser c = new JFileChooser();
            
            FileFilter filter = new FileNameExtensionFilter("Bin Files","bin");
            c.setFileFilter(filter);

            int returnVal = c.showOpenDialog(view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {


                File file = c.getSelectedFile();

                FileInputStream fs = new FileInputStream(file);

                ObjectInputStream os = new ObjectInputStream(fs);

                Model loadedModel = (Model) os.readObject();

                model.load(loadedModel);
                model.addListener(view);

                os.close();

                fs.close();


            }
            if (returnVal == JFileChooser.CANCEL_OPTION) {
                //DO NOTHING
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
