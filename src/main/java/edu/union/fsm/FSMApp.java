package edu.union.fsm;

import javax.swing.JFrame;

public Class FSMApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FSMApp instance = new FSMApp();
                instance.start();
            }
        });
    }

    public void start() {
        JFrame frame = new JFrame("Finite State Machine Viewer");
        FSMApp app = new FSMApp();

        View viewPane = new View();
        Control appControl = new Control(app, view);

        frame.getContentPane().add(view);
        frame.pack();
        frame.setVisible(true);
}
