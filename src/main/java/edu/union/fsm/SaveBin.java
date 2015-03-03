/**
* Saves a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;

public class SaveBin {

    private Model model;

    public SaveBin(Model model){
        this.model = model;
    }

    public void saveFile(String fileName){
        try {

            FileOutputStream fs = new FileOutputStream(fileName);

            ObjectOutputStream os = new ObjectOutputStream(fs);

            os.writeObject(model);

            os.close();

            fs.close();
        } catch (FileNotFoundException e) {
            //DO NOTHING
        } catch (IOException e) {
            //DO NOTHING
        }
    }


}
