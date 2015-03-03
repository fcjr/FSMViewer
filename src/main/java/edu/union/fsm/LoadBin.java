/**
* Loads a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;

public class LoadBin {

    private Model model;

    public LoadBin(Model model){
        this.model = model;
    }

    public void loadFile(String fileName){
        try {

            FileInputStream fs = new FileInputStream(fileName);

            ObjectInputStream os = new ObjectInputStream(fs);

            Model loadedModel = (Model) os.readObject();

            model.load(loadedModel);

            os.close();

            fs.close();
        } catch (FileNotFoundException e) {
            //DO NOTHING
        } catch (IOException e) {
            //DO NOTHING
        } catch (ClassNotFoundException e) {
            //DO NOTHING
        }
    }


}
