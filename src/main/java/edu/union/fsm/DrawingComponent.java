package edu.union.fsm;

import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 *
 */


public class DrawingComponent extends JComponent {

    private Model myModel;
    private final int NUMBER_OF_CELLS = 8;
    private final int CELL_SIZE =700/NUMBER_OF_CELLS;
    private final int DIMENSION = CELL_SIZE*NUMBER_OF_CELLS;
    private Model model;

    public DrawingComponent(Model model){

        this.model = model;

        setSize(new Dimension(DIMENSION, DIMENSION));
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
        this.myModel = myModel;
    }


    public void paintComponent(Graphics g) {

    super.paintComponent(g);
    paintGridLines(g);
    paintStates(g);
    paintTransitions(g);

    paintState(g, "THIS THING", true, true , 4, 4);

    }

    private void paintGridLines(Graphics g)
    {
        //paint columns
        for(int i = 0; i <= NUMBER_OF_CELLS; i++){
            if(i != NUMBER_OF_CELLS) {
                g.drawLine(CELL_SIZE*i,0,CELL_SIZE*i,CELL_SIZE*NUMBER_OF_CELLS);
            } else { //FIND A BETTER SOLUTION TO THIS
            g.drawLine(CELL_SIZE*i-1,0,CELL_SIZE*i-1,CELL_SIZE*NUMBER_OF_CELLS);
            }
        }
        // paint Rows
        for(int i = 0; i <= NUMBER_OF_CELLS; i++){
            if(i != NUMBER_OF_CELLS) {
                g.drawLine(0,CELL_SIZE*i,CELL_SIZE*NUMBER_OF_CELLS,CELL_SIZE*i);
            } else { //FIND A BETTER SOLUTION TO THIS
            g.drawLine(0,CELL_SIZE*i-1,CELL_SIZE*NUMBER_OF_CELLS,CELL_SIZE*i-1);
            }
        }

    }

    private void paintStates(Graphics g){

    }

    private void paintTransitions(Graphics g){

    }

     public int coordToCellSpot(int coord)
    {
        return coord/CELL_SIZE;
    }

    private void paintState(Graphics g, String name, boolean isStart, boolean isAccept,
              int row, int column)
    {

        //draw initial circle
       g.drawOval(row*CELL_SIZE, column*CELL_SIZE, CELL_SIZE, CELL_SIZE);

       //if is an accept draws the second circle
       if (isAccept) {
           g.drawOval(row*CELL_SIZE+4, column*CELL_SIZE+4,CELL_SIZE-8, CELL_SIZE-8);
       }

       //iff is a start state draw the triangle
       if (isStart) {
           int quarter = CELL_SIZE/4;
           int half = CELL_SIZE/2;
           int touchingPointy = row*CELL_SIZE + quarter;
           int touchingPointx = column*CELL_SIZE + half;
           int x1 = row*CELL_SIZE + quarter;
           int y1 = column*CELL_SIZE;
           int x2 = row*CELL_SIZE + quarter + half;
           int y2 = column*CELL_SIZE;
           g.drawLine(x1,y1,touchingPointx,touchingPointy);
           g.drawLine(x2,y2,touchingPointx,touchingPointy);
       }

       //draw name
       Rectangle rect = new Rectangle(row*CELL_SIZE,column*CELL_SIZE,CELL_SIZE,CELL_SIZE);
       drawCenteredString(g,name,rect,g.getFont());

    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
    // Get the FontMetrics
    FontMetrics metrics = g.getFontMetrics(font);
    // Determine the X coordinate for the text
    int x = (int)rect.getX() + (rect.width - metrics.stringWidth(text)) / 2;
    // Determine the Y coordinate for the text
    int y = (int)rect.getY() + ((rect.height - metrics.getHeight()) / 2) - metrics.getAscent() + rect.height/3;
    // Set the font
    g.setFont(font);
    // Draw the String
    g.drawString(text, x, y);
    }
}
