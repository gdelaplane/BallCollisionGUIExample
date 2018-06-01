//
//This class creates and manages the Ball objects, handles the animation, and specifies the constants.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BallController extends JPanel {

   //Constants
   private final int ANIMATION_INTERVAL  = 35;//The animation speed.
   private final int MAX_COLLISIONS=10;//The maximum number of collisions before a ball is destroyed.
   
   //Data structure
	private static ArrayList<Ball> list = new ArrayList<Ball>();//A static ArrayList to hold the ball objects.
   
   //Imported object
   private static Timer timer;

   //Tracking fields
   private static int ballCount=25;//The number of balls to add to the JFrame.
   private static int collisionCount=0;//Keeps track of the number of collisions.
   private static int addTotal=0;//Keeps track of the total sum of all collisions relative to each ball index.
   private static int index=0;//The current index: used for assigning a number to each Ball object.

   //Constructor
   public BallController() {
    	 for(int i=0;i<ballCount;i++){
    		 Ball b=new Ball();
    		 list.add(b);//Add all of the Ball objects to the ArrayList.
          index++;//Used to assign each Ball object a number.
    	 }
        timer = new Timer(ANIMATION_INTERVAL, new TimerAction());//Set the animation interval.
    }//end of constructor
    
    public static void setAnimation(boolean turnOnOff) {
        if (turnOnOff) {
            timer.start();
        } else {
            timer.stop();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i=0;i<ballCount;i++){
        	  Ball b=list.get(i);
           if(list.get(i)==null){
               continue;
           }
           
           if(b.getCollisions()<MAX_COLLISIONS){
        	      b.draw(g);
           }else{
           System.out.println("Ball " + b.getCurrentIndex() + " has been destroyed.");
           list.set(i, null);
           }
        }
    }
    
    public static ArrayList getList(){
    	return list;
    }
    public static int getCollisionCount(){
    	return collisionCount;
    }
    public static int getBallCount(){
    	return ballCount;
    }
    public static void incrementCount(){
    	collisionCount++;
    }
    public static void addTotal(int n){
    	addTotal=addTotal+n;
    }
    public static int getTotal(){
    	return addTotal;
    }
    
    public static int getIndex(){
    	return index;
    }
    
    public static void addBall(){//Add a ball object to the window.
      Ball newBall=new Ball();
      list.add(newBall);
      ballCount++;
      index++;

    }
    
    class TimerAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<ballCount;i++){
            	Ball b=list.get(i);
               if(list.get(i)==null){
               continue;
           }
            	b.setBounds(getWidth(), getHeight());
            	b.move();
            }
            repaint();
        }
    }
}