//Aaron Chow
//Block 3 CompSci
//This is my Chemotaxis Program
//aaronchowapcs.github.io/Chemotaxis/

int spawnX;
int spawnY;
boolean eaten = false;
boolean foodsies = true;
Bacteria [] pest;
Food yummy;
class Bacteria    
{   
	int myX;
	int myY;
	int sizeX;
	int pestColor;
	Bacteria(int x, int y)
 		{
 		myX = x;
 		myY = y; 
 		sizeX = 8;
 		pestColor = color(255,255,255);
		}
 	void move()
 	{
 		myX = myX + (int)(Math.random() * 9) - 4;
 		myY = myY + (int)(Math.random() * 9) - 4;
 	}
 	void show()
 	{
 		noStroke();
 		fill(pestColor);
 		ellipse(myX,myY,sizeX,sizeX);
 	}
 	void chase() //Causes the bacteria to hunt the food
 	{
 		if(yummy.foodX > myX)
 		{
 			myX = myX + (int)(Math.random() * 9) - 2;
 			if(yummy.foodY > myY)
 				myY = myY + (int)(Math.random() * 9) - 2;
 			else if(yummy.foodY < myY)
 				myY = myY + (int)(Math.random() * 9) - 6;
 		}
 		else if(yummy.foodX < myX)
 		{
 			myX = myX + (int)(Math.random() * 9) - 6;
 			if(yummy.foodY > myY)
 				myY = myY + (int)(Math.random() * 9) - 2;
 			else if(yummy.foodY < myY)
 				myY = myY + (int)(Math.random() * 9) - 6;
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
	void show()
	{
   		noStroke();
   		fill(foodColor);
   		rect(foodX,foodY,8,8);
	}
}
void setup()   
{     
 	frameRate(15);
 	size(500,500);
 	pest = new Bacteria[8];
 	for(int index = 0; index < pest.length; index++)
 	{
 		spawnX = (int)(Math.random() * 500);
 		spawnY = (int)(Math.random() * 500);
 		pest[index] = new Bacteria(spawnX, spawnY);
 	}
 	yummy = new Food();
 }   
void draw()   
{   
	background(0);
	for(int index = 0; index < pest.length; index++)
	{
		pest[index].move();
 		pest[index].show();
 		pest[index].chase();
		if((pest[index].myX > yummy.foodX - 10 && pest[index].myX < yummy.foodX + 10) && (pest[index].myY > yummy.foodY - 10 && pest[index].myY < yummy.foodY + 10))
 			eaten = true; //The bacteria has eaten the food
 		if(eaten == true)
 		{
 			pest[index].sizeX = pest[index].sizeX + 5;
 			pest[index].pestColor = yummy.foodColor; //The bacteria will become the same color as the food
 			mousePressed();
 			eaten = false;
 		}
 	}
 	if(foodsies == true)
		yummy.show();
}  
void mousePressed() //Creates a new food with random color and location 
{
	yummy = new Food();
	yummy.show();
	foodsies = true;
}
  
