/**
* Saves a seralized model class to a file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.io.*;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;

public class SavePNG {

    private JComponent componentToSave;
    private JFrame paneToPrompt;

    public SavePNG(JComponent componentToSave, JFrame paneToPrompt){
        this.componentToSave = componentToSave;
        this.paneToPrompt = paneToPrompt;
    }

    public void saveFile() {
        try {

            JFileChooser c = new JFileChooser();
            int returnVal = c.showSaveDialog(paneToPrompt);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = c.getSelectedFile();

                Rectangle rect = componentToSave.getVisibleRect();

                BufferedImage image = new BufferedImage(rect.width,rect.height,BufferedImage.TYPE_INT_RGB);

                Graphics graphics = image.getGraphics();

                //componentToSave.paintComponent(graphics);

                ImageIO.write(image, "png", file);
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
