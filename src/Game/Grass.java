package Game;

import java.awt.Graphics;
import java.util.Random;

public class Grass extends Elements {


    public Grass(int x,int y) {
        super(x, y, 34, 34);
        


        id=3;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawGrass(g, getX(), getY());

	}
}