package edu.union.fsm;

import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *component for drawing fsm's
 *
 */
public class DrawingComponent extends JComponent {

    private Model myModel;
    private final int NUMBER_OF_CELLS = 8;
    private final int CELL_SIZE =700/NUMBER_OF_CELLS;
    private final int DIMENSION = CELL_SIZE*NUMBER_OF_CELLS;

    /**
     * constructor.
     * @param  model refference to the model
     */
    public DrawingComponent(Model model){

        this.myModel = model;

        setSize(new Dimension(DIMENSION, DIMENSION));
        setPreferredSize(new Dimension(DIMENSION, DIMENSION));
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
        DisplayStore displayStore = myModel.displayStore;
        FSMStore fsmStore = myModel.fsmStore;

        for(int row = 0; row < displayStore.getRows(); row++){
            for(int column = 0; column < displayStore.getColumns(); column++) {
                if(displayStore.containsState(row,column)){
                    int id = displayStore.getState(row,column);
                    State toDraw = fsmStore.getState(id);
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
        ArrayList<Transition> transitions = myModel.fsmStore.getTransitions();
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
        int fromRow = myModel.displayStore.getRow(fromID);
        int fromColumn = myModel.displayStore.getColumn(fromID);
        int toRow = myModel.displayStore.getRow(toID);
        int toColumn = myModel.displayStore.getColumn(toID);
        int offset = CELL_SIZE/2;
        int x1 = fromRow * CELL_SIZE + offset;
        int y1 = fromColumn * CELL_SIZE + offset;
        int x2 = toRow * CELL_SIZE + offset;
        int y2 = toColumn * CELL_SIZE + offset;

            g.setColor(Color.RED);

        //Draws line
        g.drawLine(x1,y1,x2,y2);

        int midLineX = (x1+x2) / 2;
        int midLineY = (y1+y2) / 2;


        //Draws circle to show direction and name
        g.drawOval(x2-4,y2-4,8,8);

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
