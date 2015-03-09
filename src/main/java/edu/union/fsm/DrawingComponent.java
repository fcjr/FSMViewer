package edu.union.fsm;

//import edu.union.fsm.storage.*;

import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

/**
 *component for drawing fsm's
 *
 */
public class DrawingComponent extends JComponent {

    private InformationStore informationStore;
    private final int NUMBER_OF_CELLS = 8;
    private final int CELL_SIZE =700/NUMBER_OF_CELLS;
    private final int DIMENSION = CELL_SIZE*NUMBER_OF_CELLS;

    /**
     * constructor.
     * @param  model refference to the model
     */
    public DrawingComponent(InformationStore model){

        this.informationStore = model;

        setSize(new Dimension(DIMENSION, DIMENSION));
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
    }

    public void printAll(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0,0,DIMENSION,DIMENSION);
        //paintGridLines(g);
        paintStates(g);
        paintTransitions(g);
        }

    /**
     * paints the fsm
     * @param g magical graphics object.
     */
    public void paintComponent(Graphics g) {

    super.paintComponent(g);
    paintGridLines(g);
    paintStates(g);
    paintTransitions(g);

    }

    /**
     * paints the grid layout
     * @param g magical graphics object.
     */
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

    /**
     * paints the states to the component
     * @param g magical graphics object
     */
    private void paintStates(Graphics g){

        for(int row = 0; row < informationStore.getRows(); row++){
            for(int column = 0; column < informationStore.getColumns(); column++) {
                if(informationStore.containsState(row,column)){
                    State toDraw = informationStore.getState(row,column);
                    String name = toDraw.getName();
                    boolean isStart = toDraw.isStart();
                    boolean isAccept = toDraw.isAccept();
                    paintState(g,name,isStart,isAccept,row,column);
                }
            }
        }

    }

    /**
     * paints the transtitions to the component
     * @param g magical graphics object
     */
    private void paintTransitions(Graphics g){
        ArrayList<Transition> transitions = informationStore.getTransitions();
        for(Transition currentTrans: transitions){
            String name = currentTrans.getLabel();
            int fromID = currentTrans.getFromID();
            int toID = currentTrans.getToID();

            paintTransition(g,name,fromID,toID);
        }

    }

    /**
     * paints a transition.
     * @param  g magical graphics object
     * @param  name name of the transition
     * @param  fromID id of the fromstate
     * @param  toID id of the toState
     */
    private void paintTransition(Graphics g, String name, int fromID, int toID){
        int fromRow = informationStore.getRow(fromID);
        int fromColumn = informationStore.getColumn(fromID);
        int toRow = informationStore.getRow(toID);
        int toColumn = informationStore.getColumn(toID);
        int offset = CELL_SIZE/2;
        int x1 = fromRow * CELL_SIZE + offset;
        int y1 = fromColumn * CELL_SIZE + offset;
        int x2 = toRow * CELL_SIZE + offset;
        int y2 = toColumn * CELL_SIZE + offset;
	int xl = x2-x1;
	int yl = y2-y1;
	int x3 = 0;
	int y3 = 0;

	g.setColor(Color.ORANGE);

	// This is the case in which the states are vertically to one another
	if (xl == 0){
	    if (yl<0){
		double thetal = 3*Math.PI/2 + Math.PI/4;
		double thetar = 3*Math.PI/2 - Math.PI/4;
		int ytl = (int)(Math.sin(thetal)*(double)offset/2);
		int xtl = (int)(Math.cos(thetal)*(double)offset/2);
		int ytr = (int)(Math.sin(thetar)*(double)offset/2);
		int xtr = (int)(Math.cos(thetar)*(double)offset/2);

		y3 = y2+offset;
		x3 = x2;
		xtl = x3-xtl;
		ytl = y3-ytl;
		xtr = x3-xtr;
		ytr = y3-ytr;

		int x1Points[] = {xtl, xtr, x3, xtl};
		int y1Points[] = {ytl, ytr, y3, ytl};
		g.fillPolygon(x1Points, y1Points, 3);
		g.drawLine(x1,y1,x3, y3);
	    }
	    else{
		double thetal = Math.PI/2 + Math.PI/4;
		double thetar = Math.PI/2 - Math.PI/4;
		int ytl = (int)(Math.sin(thetal)*(double)offset/2);
		int xtl = (int)(Math.cos(thetal)*(double)offset/2);
		int ytr = (int)(Math.sin(thetar)*(double)offset/2);
		int xtr = (int)(Math.cos(thetar)*(double)offset/2);

		y3 = y2-offset;
		x3 = x2;
		xtl = x3-xtl;
		ytl = y3-ytl;
		xtr = x3-xtr;
		ytr = y3-ytr;

		int x1Points[] = {xtl, xtr, x3, xtl};
		int y1Points[] = {ytl, ytr, y3, ytl};
		g.fillPolygon(x1Points, y1Points, 3);
		g.drawLine(x1,y1,x3, y3);
	    }
	}

	else{
	    double theta = Math.atan((double)yl/(double)xl);
	    double thetal = theta + Math.PI/4;
	    double thetar = theta - Math.PI/4;
	    int ytl = (int)(Math.sin(thetal)*(double)offset/2);
	    int xtl = (int)(Math.cos(thetal)*(double)offset/2);
	    int ytr = (int)(Math.sin(thetar)*(double)offset/2);
	    int xtr = (int)(Math.cos(thetar)*(double)offset/2);
	    int yt = (int)(Math.sin(theta)*(double)offset);
	    int xt = (int)(Math.cos(theta)*(double)offset);

	    if(x1<x2 && (y1<y2 || y1>=y2)){
		x3 = x2-xt;
		y3 = y2-yt;
		xtl = x3-xtl;
		ytl = y3-ytl;
		xtr = x3-xtr;
		ytr = y3-ytr;
	    }
	    else{
		x3 = x2+xt;
		y3 = y2+yt;
		xtl = x3+xtl;
		ytl = y3+ytl;
		xtr = x3+xtr;
		ytr = y3+ytr;
	    }

	    //Draws line
	    int x1Points[] = {xtl, xtr, x3, xtl};
	    int y1Points[] = {ytl, ytr, y3, ytl};
	    g.fillPolygon(x1Points, y1Points, 3);
	    g.drawLine(x1,y1,x3,y3);
	}

        int midLineX = (x1+x2) / 2;
        int midLineY = (y1+y2) / 2;

        //Draws name
        g.drawString(name,midLineX, midLineY);
	g.setColor(Color.BLACK);
    }

    /**
     * converts raw coords to cell locations.
     * @param  coord raw coord
     * @return       cell data
     */
     public int coordToCellSpot(int coord)
    {
        return coord/CELL_SIZE;
    }

    /**
     * paints a state.
     * @param  g magic
     * @param  name name of the state
     * @param  isStart the fact that it is a start or not
     * @param  isAccept the fact that it is an accept or not
     * @param  row the row to draw it in
     * @param  column the column to draw it in
     */
    private void paintState(Graphics g, String name, boolean isStart, boolean isAccept,
              int row, int column)
    {
        g.setColor(Color.BLACK);

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
           int touchingPointx = row*CELL_SIZE + half;
           int touchingPointy = column*CELL_SIZE + quarter;
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

    /**
     * draws a string in the center of the given rectangle
     * @param  g magic
     * @param  text text to draw
     * @param  rect the rect to draw in
     * @param  font the font to write in
     */
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
