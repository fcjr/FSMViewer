package edu.union.fsm;

/**
 * Simple View controller to test swift.
 *
 * @author Frank, Nate, & Rudy.
 * @version a1
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class View extends JComponent {    

    private Control control;

    public View(Control control) {
        this.control = control;

        Font myFont = new Font("TimesRoman", Font.PLAIN, 12);
        this.setFont(myFont);
        FontMetrics metrics = getFontMetrix(myFont);
        int height = metrics.getHeight();

        setSize(1080,720);
        setPreferredSize(1080,720);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCircle(g, 10, 10);
        drawCircle(g, 20, 20);

    }

    public void drawCircle(Graphics g, int x, int y) {
        
    }
