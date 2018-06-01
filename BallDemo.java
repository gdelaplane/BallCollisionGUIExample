//
//This class runs the demo by instantiating a JFrame and
//then instantiating a BallPanel inside of it.
//

import javax.swing.*;

public class BallDemo {
    public static void main(String[] args) {
        JFrame win = new JFrame("Collision Demo");//Create the JFrame
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setSize(800,800);//This is the initial size, it may be adjusted in real time by dragging the window edges.
        win.setContentPane(new BallPanel());//Add the BallPanel to the JFrame
        win.setVisible(true);
    }
}//endclass BallDemo