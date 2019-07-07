package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Base extends Elements {

    public Base(int x, int y,int li,int allli) {
        super(x, y, 34,34);
        life=li;
        alllife=allli;
       /* int a=ra.nextInt(750)+1;
        int b=ra.nextInt(550)+1;
        setX(a);
        setY(b);*/

        id=7;
    }

    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawBase(g, getX(), getY(),life,alllife);

    }

}