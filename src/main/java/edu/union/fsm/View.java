/**
* Finite State Machine Viewer
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

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
  private Model model;
  private DrawingComponent drawingComponent;


  View(Model model) {
    this.model = model;
    drawingComponent = new DrawingComponent(model);

    JPanel panel = new JPanel();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Finite State Machine Viewer");
    this.setSize(980, 760);
    this.setResizable(false);

    // add the panes to the panel
    panel.add(stateLabel);
    panel.add(addStateButton);
    panel.add(deleteStateButton);
    panel.add(moveStateButton);
    panel.add(toggleTypeButton);
    panel.add(transitionLabel);
    panel.add(addTransitionButton);
    panel.add(deleteTransitionButton);
    panel.add(nameLabel);
    panel.add(nameField);
    panel.add(drawingComponent);

    this.add(panel);

  }

  public void update(){
      drawingComponent.repaint();
      this.clearName();
  }

  public String getName(){

    return nameField.getText();

  }

  public void clearName(){

    nameField.setText("");

  }

  public int coordToCellSpot(int coord) {
      return drawingComponent.coordToCellSpot(coord);
  }

  //ADD LISTENER FUNCTIONS

  public void addMouseListener(MouseListener mouseHandler){

      drawingComponent.addMouseListener(mouseHandler);

  }

  public void addAddStateButtonListener(ActionListener listenForAddStateButton){

    addStateButton.addActionListener(listenForAddStateButton);

  }

  public void addMoveStateButtonListener(ActionListener listenForAddStateButton){

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



  /**
  * Displays a popup with the given message.
  *
  * @param errorMessage the message to add.
  */
  public void displayErrorMessage(String errorMessage){

    JOptionPane.showMessageDialog(this, errorMessage);

  }

}
