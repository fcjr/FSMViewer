/**
* Finite State Machine Viewer
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame implements ModelListener{


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
  private JButton saveBin = new JButton("Save");
  private JButton loadBin = new JButton("Load");

  private Model model;
  private DrawingComponent drawingComponent;


  /**
   * constructor.  takes in a refference to the model
   *
   * @param model the model class
   */
  View(Model model) {
    this.model = model;
    drawingComponent = new DrawingComponent(model);

    JPanel panel = new JPanel();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Finite State Machine Viewer");
    this.setSize(980, 800);
    this.setResizable(false);
    this.setLayout(new BorderLayout());

    // add the panes to the panel
    panel.add(stateLabel, BorderLayout.PAGE_START);
    panel.add(addStateButton, BorderLayout.PAGE_START);
    panel.add(deleteStateButton, BorderLayout.PAGE_START);
    panel.add(moveStateButton, BorderLayout.PAGE_START);
    panel.add(toggleTypeButton, BorderLayout.PAGE_START);
    panel.add(transitionLabel, BorderLayout.PAGE_START);
    panel.add(addTransitionButton, BorderLayout.PAGE_START);
    panel.add(deleteTransitionButton, BorderLayout.PAGE_START);
    panel.add(nameLabel, BorderLayout.PAGE_START);
    panel.add(nameField, BorderLayout.PAGE_START);
    panel.add(drawingComponent, BorderLayout.CENTER);
    panel.add(saveBin, BorderLayout.PAGE_END);
    panel.add(loadBin, BorderLayout.PAGE_END);

    this.add(panel);

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
      saveBin.addActionListener(l);
  }
  public void addLoadBinButtonListener(ActionListener l) {
      loadBin.addActionListener(l);
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
