import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class chemotaxis extends PApplet {

boolean eaten = false;
boolean foodsies = true;
Bacteria [] pest;
Food yummy;
class Bacteria    
{   
	int myX;
	int myY;
	int pestColor;
	Bacteria(int x, int y)
 		{
 		myX = x;
 		myY = y; 
 		pestColor = color(150,150,150);
		}
 	public void move()
 	{
 		myX = myX + (int)(Math.random() * 8) - 4;
 		myY = myY + (int)(Math.random() * 8) - 4;
 	}
 	public void show()
 	{
 		noStroke();
 		fill(pestColor);
 		ellipse(myX,myY,15,15);
 	}
 	public void chase() //Causes the bacteria to hunt the food
 	{
 		if(yummy.foodX > myX)
 		{
 			myX = myX + (int)(Math.random() * 8) - 2;
 			if(yummy.foodY > myY)
 				myY = myY + (int)(Math.random() * 8) - 2;
 			else if(yummy.foodY < myY)
 				myY = myY + (int)(Math.random() * 8) - 6;
 		}
 		else if(yummy.foodX < myX)
 		{
 			myX = myX + (int)(Math.random() * 8) - 6;
 			if(yummy.foodY > myY)
 				myY = myY + (int)(Math.random() * 8) - 2;
 			else if(yummy.foodY < myY)
 				myY = myY + (int)(Math.random() * 8) - 6;
 		}
 	}
} 
class Food
{
	int foodX;
	int foodY;
	int foodColor;
	Food()
	{
   		foodX = (int)(Math.random() * 450);
   		foodY = (int)(Math.random() * 450);
   		foodColor = color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256)); 
    }
	public void show()
	{
   		noStroke();
   		fill(foodColor);
   		rect(foodX,foodY,8,8);
	}
}
public void setup()   
{     
 	frameRate(15);
 	size(500,500);
 	pest = new Bacteria[5];
 	for(int index = 0; index < pest.length; index++)
 	{
 		 pest[index] = new Bacteria(250,250);
 	}
 	yummy = new Food();
 }   
public void draw()   
{   
	background(255);
	for(int index = 0; index < pest.length; index++)
	{
		pest[index].move();
 		pest[index].show();
 		pest[index].chase();
		if((pest[index].myX > yummy.foodX - 10 && pest[index].myX < yummy.foodX + 10) && (pest[index].myY > yummy.foodY - 10 && pest[index].myY < yummy.foodY + 10))
 			eaten = true; //The bacteria has eaten the food
 		if(eaten == true)
 		{
 			pest[index].pestColor = yummy.foodColor; //The bacteria will become the same color as the food
 			mousePressed();
 			eaten = false;
 		}
 	}
 	if(foodsies == true)
		yummy.show();
}  
public void mousePressed() //Creates a new food with random color and location 
{
	yummy = new Food();
	yummy.show();
	foodsies = true;
}
  
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
