//
//This class creates the Ball object that will be used by the other classes. 
//Note the mostly empty constructor: everything is randomized except the index.
//

import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class Ball {
    private Random rng=new Random();//Create the Random object for use with randomization.
    private Color color=new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));//create a random color from the RGB spectrum.
    private int diameter = (rng.nextInt(41))+15;//Generate a random diameter between 15 and 55.
    private int rightBound;//The maximum X-Coordinate the ball can reach.
    private int bottomBound;//The maximum Y-Coordinate the ball can reach.
    private int x=rng.nextInt(800);//The initial randomized X-Coordinate for the ball.      
    private int y=rng.nextInt(800);//The initial randomized Y-Coordinate for the ball.
    private int velocityX=(rng.nextInt(3))+1;//The initial randomized X-Velocity for the ball.  
    private int velocityY=(rng.nextInt(3))+1;//The initial randomized Y-Velocity for the ball.
    private int currentIndex=0;//Tracks the current index for Ball comparison.
    private int collisions=0;
    
    public Ball() {//Mostly empty constructor, Balls are generated with random features.
    currentIndex=BallController.getIndex();//used once to assign a number to each ball.
    }
    
    public void setBounds(int width, int height) {//Set border limits to keep the balls visible in the window.
        rightBound  = width  - diameter;
        bottomBound = height - diameter;
    }
    
    public void collisionCheck(){//Check for a collision between two balls, update them accordingly.
    	  ArrayList<Ball> list=BallController.getList();//Retrieve the ArrayList containing all of the Balls.
        for(int i=0;i<BallController.getBallCount();i++){
        	  Ball otherBall=list.get(i);
           if(list.get(i)==null){
               continue;
           }
        	  if (currentIndex==i){//Make sure the ball does not detect itself and attempt a collision.
        		  continue;
        	  }//Calculate the distance from ball center to ball center using the distance formula.
		     double distance=Math.sqrt( (this.getX()-otherBall.getX())*(this.getX()-otherBall.getX())+(this.getY()-otherBall.getY())*(this.getY()-otherBall.getY()));
		     if (distance<(double)this.getDiameter()/(double)2+(double)otherBall.getDiameter()/(double)2){//Check for collision
			     if(this.getX()>otherBall.getX()&&this.getY()>otherBall.getY()){//Handle the collision accordingly
				     velocityX = Math.abs(velocityX);
				     velocityY = Math.abs(velocityY);
				     otherBall.setVel(otherBall.getXVel()*-1, otherBall.getYVel()*-1);
			     }
			     else if(this.getX()<otherBall.getX()&&this.getY()<otherBall.getY()){
				     velocityX = Math.abs(velocityX)*-1;
				     velocityY = Math.abs(velocityY)*-1;
				     otherBall.setVel(otherBall.getXVel()*-1, otherBall.getYVel()*-1);
			     }
			     else if(this.getX()>otherBall.getX()&&this.getY()<otherBall.getY()){
				     velocityX = Math.abs(velocityX);
				     velocityY = Math.abs(velocityY)*-1;
				     otherBall.setVel(otherBall.getXVel()*-1, otherBall.getYVel()*-1);
			     }
			     else if(this.getX()<otherBall.getX()&&this.getY()>otherBall.getY()){
				     velocityX = Math.abs(velocityX)*-1;
				     velocityY = Math.abs(velocityY);
				     otherBall.setVel(otherBall.getXVel()*-1, otherBall.getYVel()*-1);
			     }
			     else {
			        velocityX = -velocityX;
			        velocityY = -velocityY;
			     }
           //Finish up with updating the counts and re-randomizing elements of each ball involved in the collision.
			  BallController.incrementCount();
			  BallController.addTotal(add(currentIndex,otherBall.getCurrentIndex()));
			  this.updateColor();
			  otherBall.updateColor();
           this.updateDiameter();
           otherBall.updateDiameter();
           otherBall.incrementCollisions();
           this.incrementCollisions();
			  System.out.println(currentIndex+" collided with "+otherBall.getCurrentIndex()+".");
			  System.out.println(currentIndex+"+"+otherBall.getCurrentIndex()+"="+add(currentIndex,otherBall.getCurrentIndex())+".");
			  System.out.println(add(currentIndex,otherBall.getCurrentIndex())+" has been added to the Total Sum."+"");
           }//end of if (distance<(double)this.getDiameter()...
         }//end of for(int i=0;i<BallController...
       }//end of collisionCheck()
    
    public void move() {//Move the ball.
    	collisionCheck();//Check for collision with each movement.
        x += velocityX;
        y += velocityY;        
        
        if (x < 0) {//Keep the ball in the GUI.                 
            x = 0;            
            velocityX = -velocityX;
            
        } else if (x > rightBound) {
            x = rightBound;   
            velocityX = -velocityX; 
        }
        
        if (y < 0) {              
            y = 0;
            velocityY = -velocityY;
            
        } else if (y > bottomBound) { 
            y =  bottomBound;
            velocityY = -velocityY;
        }
    }//end of move()
    
 
    public void draw(Graphics g) {//Draw the ball.
    	  g.setColor(color);
        g.setFont(new Font("Times Roman",Font.PLAIN,13));
        g.fillOval(x, y, diameter, diameter);
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(currentIndex), x+3, y+2);
        g.setFont(new Font("Times Roman",Font.PLAIN,30));
        g.drawString("Collisions: "+Integer.toString(BallController.getCollisionCount())+"  Total Sum: "+BallController.getTotal(), 50, 35);
    }//end of draw method
    
    public int  getDiameter() { return diameter;}//Getters, setters, and simple updaters.
    public int  getX() { return x+(diameter/2);}//To retrieve the center of each ball we must add the radius.
    public int  getY() { return y+(diameter/2);}
    public int getXVel() {return velocityX;}
    public int getYVel() {return velocityY;}
    public int getCurrentIndex() {return currentIndex;}
    public int getCollisions() {return collisions;}
    
    public void setPosition(int x, int y) {
        x = x;
        y = y;
    }
    
    public void setDiameter(int d) {
        diameter=d;
    }
    
    public void setVel(int x, int y){
        velocityX= -x;
    	  velocityY= -y;
    }
    
    public void updateColor(){
    	  color=new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
    }
    
    public void updateDiameter(){
        diameter=(rng.nextInt(40))+15;
    }
    
    public void incrementCollisions(){
    collisions++;
    }
    
    public int add(int one, int two){
    	  return one+two;
    }
    
}//end of class