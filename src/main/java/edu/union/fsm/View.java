/**
* Finite State Machine Viewer
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame{


  private JButton addStateButton = new JButton("Add State");
  private JButton deleteStateButton = new JButton("Delete State");
  private JButton addTransitionButton = new JButton("Add Transition ");
  private JButton deleteTransitionButton = new JButton("Delete Transition");
  private JLabel nameLabel = new JLabel(" Name: ");
  private JTextField nameField = new JTextField(10);

  View(){

    JPanel panel = new JPanel();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Finite State Machine Viewer");
    this.setSize(1080, 720);

    // add the panes to the panel
    panel.add(addStateButton);
    panel.add(deleteStateButton);
    panel.add(addTransitionButton);
    panel.add(deleteTransitionButton);
    panel.add(nameLabel);
    panel.add(nameField);

    this.add(panel);

  }

  public String getName(){

    return nameField.getText();

  }

  public void clearName(){

    nameField.setText("");

  }

  //ADD LISTENER FUNCTIONS

  public void addAddStateListener(ActionListener listenForAddStateButton){

    addStateButton.addActionListener(listenForAddStateButton);

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
