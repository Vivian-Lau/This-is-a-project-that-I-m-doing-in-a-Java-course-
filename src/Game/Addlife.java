package Game;

import java.awt.Graphics;
import java.util.Random;

public class Addlife extends Elements {
    private Random ra =new Random();

    public Addlife(){
        super(0, 0, 34, 34);
        
        int a=ra.nextInt(750)+1;
        int b=ra.nextInt(550)+1;
        setX(a);
        setY(b);

        id=0;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawAddlife(g, getX(), getY());

	}
}