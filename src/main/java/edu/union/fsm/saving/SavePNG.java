/**
* Saves a seralized model class to a file.
*/

package edu.union.fsm.saving;

import java.io.*;
import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import java.awt.image.BufferedImage;

import org.apache.commons.io.FilenameUtils;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


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

            FileFilter filter = new FileNameExtensionFilter("PNG Files","png");
            c.setFileFilter(filter);

            int returnVal = c.showSaveDialog(paneToPrompt);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                File file = c.getSelectedFile();

                //ENSURE PNG FILE
                if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("png")) {
                        //DO NOTHING FILE IS GOOD
                } else {
                    //replace file extension if is wrong
                    file = new File(file.getParentFile(), FilenameUtils.getBaseName(file.getName())+".png");
                }

                Rectangle rect = componentToSave.getVisibleRect();

                BufferedImage image = new BufferedImage(rect.width,rect.height,BufferedImage.TYPE_INT_RGB);

                Graphics graphics = image.createGraphics();

                componentToSave.printAll(graphics);

                ImageIO.write(image, "png", file);

                graphics.dispose();

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
