//
//This class creates the custom JPanel used for all of the GUI and animation elements.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BallPanel extends JPanel {
    private BallController ballController;
    private boolean state=true;//used for starting/pausing the window animation.
    private JButton jb1;//Create a button.
    private JButton jb2;//Create a button.
    BallPanel() {
        ballController = new BallController();        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());//The buttons use FlowLayout
        this.setLayout(new BorderLayout());//The JPanel uses BorderLayout
        jb1 = new JButton("PAUSE");
        jb1.setBackground(Color.WHITE);
        jb1.setForeground(Color.BLACK);
        jb1.setFont(new Font("Arial", Font.PLAIN, 30));
        jb1.setMargin(new Insets(10, 20, 10, 20));
        jb1.setHorizontalAlignment(SwingConstants.LEFT);
        jb1.addActionListener(new ButtonActionOne());//Specify the method used when clicking the button.
        jb2 = new JButton("ADD A BALL");
        jb2.setBackground(Color.WHITE);
        jb2.setForeground(Color.BLACK);
        jb2.setFont(new Font("Arial", Font.PLAIN, 30));
        jb2.setMargin(new Insets(10, 20, 10, 20));
        jb2.setHorizontalAlignment(SwingConstants.LEFT);
        jb2.addActionListener(new ButtonActionTwo());//Specify the method used when clicking the button.
        buttonPanel.add(Box.createVerticalStrut(80));
        buttonPanel.add(jb1,BorderLayout.CENTER);
        buttonPanel.add(jb2,BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(ballController, BorderLayout.CENTER);
        ballController.setAnimation(true);
    }//end of constructor
    class ButtonActionOne implements ActionListener {//Add functionality to the start/pause button.
        public void actionPerformed(ActionEvent e) {
        if(state){
            ballController.setAnimation(false);
            state=false;
            jb1.setText("START");
        }else{
            ballController.setAnimation(true);
            state=true;
             jb1.setText("PAUSE");
        }
        }
    }//end of inner class
    class ButtonActionTwo implements ActionListener {//Add functionality to the "add a ball" button
        public void actionPerformed(ActionEvent e) {
            BallController.addBall();
        }
    }//end of inner class
}//end of class