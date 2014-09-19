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

public class Chemotaxis extends PApplet {


int colorA = color(150,150,150);
int colorB;
boolean eaten = false;
boolean foodsies = true;
Bacteria [] pest;
Food yummy;
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
	if(foodsies == true)
		yummy.show();
	for(int index = 0; index < pest.length; index++)
	{
		pest[index].move();
 		pest[index].show();
 		pest[index].chase();
	}
	 			if((myX > yummy.foodX - 20 && myX < yummy.foodX + 20) && (myY > yummy.foodX - 20 && myY < yummy.foodY + 20))
 				eaten = true; //The bacteria has eaten the food
 			if(eaten == true)
 			{
 				colorA = colorB; //The bacteria will become the same color as the food
 				mousePressed();
 			}
}  
public void mousePressed() //Creates a new food with random color and location 
{
	yummy = new Food();
	yummy.show();
	colorB = color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
	foodsies = true;
}
class Food
{
	int foodX;
	int foodY;
	Food()
	{
   		foodX = (int)(Math.random()*450);
   		foodY = (int)(Math.random()*450);
    }
	public void show()
	{
   		noStroke();
   		fill(colorB);
   		rect(foodX,foodY,8,8);

	}
}
class Bacteria    
{   
	int myX;
	int myY;
	Bacteria(int x, int y)
 		{
 		colorB = color((int)(Math.random() * 256),(int)(Math.random() * 256),(int)(Math.random() * 256));
 		myX = x;
 		myY = y; 
		}
 	public void move()
 	{
 		myX = myX + (int)(Math.random() * 8) - 4;
 		myY = myY + (int)(Math.random() * 8) - 4;
 	}
 	public void show()
 	{
 		noStroke();
 		fill(colorA);
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
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Chemotaxis" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
