package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Water extends Elements {
    private Random ra = new Random();

    public Water(int x,int y) {
        super(x, y, 34,34);
        


        id=6;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawWater(g, getX(), getY());

    }

}