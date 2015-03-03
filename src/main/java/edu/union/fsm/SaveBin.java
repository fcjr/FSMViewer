/**
* Saves a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;
import javax.swing.JFileChooser;

public class SaveBin {

    private class SerialChooser extends JFileChooser implements Serializable{}

    private Model model;
    private View view;

    public SaveBin(Model model, View view){
        this.model = model;
        this.view = view;
    }

    public void saveFile(){
        try {

            JFileChooser c = new JFileChooser();
            int returnVal = c.showSaveDialog(view);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = c.getSelectedFile();

                String fileName = file.getPath();

                System.out.println(fileName);

                //String file = "testing.bin";

                FileOutputStream fs = new FileOutputStream(fileName);

                ObjectOutputStream os = new ObjectOutputStream(fs);

                //model.cleanForWriting();

                os.writeObject(model);

                os.close();

                fs.close();

            }
            else if (returnVal == JFileChooser.CANCEL_OPTION) {
                //DO NOTHING
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
