/**
* Finite State Machine Swing Display
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;

public class SwingDisplay extends JFrame implements InformationStoreListener{


  private JLabel stateLabel = new JLabel("State: ");
  private JButton addStateButton = new JButton("Add");
  private JButton deleteStateButton = new JButton("Delete");
  private JButton moveStateButton = new JButton("Move");
  private JButton toggleTypeButton = new JButton("Toggle Type");
  private JLabel transitionLabel = new JLabel(" Transition: ");
  private JButton addTransitionButton = new JButton("Add");
  private JButton deleteTransitionButton = new JButton("Delete");
  private JLabel nameLabel = new JLabel(" Name: ");
  private JTextField nameField = new JTextField(10);
  private JButton saveBinButton = new JButton("Save");
  private JButton loadBinButton = new JButton("Load");
  private JButton savePNGButton = new JButton("Save Image (PNG)");

  private InformationStore model;
  private DrawingComponent drawingComponent;


  /**
   * constructor.  takes in a refference to the model
   *
   * @param model the model class
   */
  SwingDisplay(InformationStore model) {
    this.model = model;
    drawingComponent = new DrawingComponent(model);

    JPanel topPanel = new JPanel();
    JPanel middlePanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Finite State Machine Viewer");
    this.setSize(980, 810);
    this.setResizable(false);
    this.setLayout(new BorderLayout());

    // add the panes to the panel
    topPanel.add(stateLabel);
    topPanel.add(addStateButton);
    topPanel.add(deleteStateButton);
    topPanel.add(moveStateButton);
    topPanel.add(toggleTypeButton);
    topPanel.add(transitionLabel);
    topPanel.add(addTransitionButton);
    topPanel.add(deleteTransitionButton);
    topPanel.add(nameLabel);
    topPanel.add(nameField);
    middlePanel.add(drawingComponent);
    bottomPanel.add(saveBinButton);
    bottomPanel.add(loadBinButton);
    bottomPanel.add(savePNGButton);

    this.add(topPanel,BorderLayout.NORTH);
    this.add(middlePanel,BorderLayout.CENTER);
    this.add(bottomPanel,BorderLayout.SOUTH);

  }

  /**
   *	Returns the main display component.
   */
   public JComponent getMainDisplayComponent(){
       return this.drawingComponent;
   }

  /**
   * repaints the drawing component
   */
  public void update(){
      drawingComponent.repaint();
      this.clearName();
  }

  /**
   * returns the string in the name field
   * @return user inputed string.
   */
  public String getName(){

    return nameField.getText();

  }

  /**
   * clears nameField.
   */
  public void clearName() {
    nameField.setText("");
  }

  /**
   * converts raw coords to cell locations.
   * @param  coord raw coord
   * @return       cell data
   */
  public int coordToCellSpot(int coord) {
      return drawingComponent.coordToCellSpot(coord);
  }

  //ADD LISTENER FUNCTIONS
  public void addMouseListener(MouseListener mouseHandler) {
      drawingComponent.addMouseListener(mouseHandler);
  }
  public void addAddStateButtonListener(ActionListener listenForAddStateButton) {
    addStateButton.addActionListener(listenForAddStateButton);
  }
  public void addMoveStateButtonListener(ActionListener listenForAddStateButton) {
    moveStateButton.addActionListener(listenForAddStateButton);
  }
  public void addToggleTypeButtonListener(ActionListener l) {
      toggleTypeButton.addActionListener(l);
  }
  public void addDeleteStateButtonListener(ActionListener l) {
    deleteStateButton.addActionListener(l);
  }
  public void addAddTransitionButtonListener(ActionListener l) {
    addTransitionButton.addActionListener(l);
  }
  public void addDeleteTransitionButtonListener(ActionListener l) {
    deleteTransitionButton.addActionListener(l);
  }
  public void addSaveBinButtonListener(ActionListener l) {
      saveBinButton.addActionListener(l);
  }
  public void addLoadBinButtonListener(ActionListener l) {
      loadBinButton.addActionListener(l);
  }
  public void addSavePNGButtonListener(ActionListener l) {
      savePNGButton.addActionListener(l);
  }



  /**
  * Displays a popup with the given message.
  *
  * @param errorMessage the message to add.
  */
  public void displayErrorMessage(String errorMessage) {
    JOptionPane.showMessageDialog(this, errorMessage);
  }

}
