package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Brick extends Elements {


    public Brick(int x, int y) {
        super(x, y, 7,7);
        


        id=4;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawBrick(g, getX(), getY());

    }

}