package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Iron extends Elements {


  public Iron(int x,int y) {
        super(x, y, 17,17);


        id=5;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawIron(g, getX(), getY());

    }

}