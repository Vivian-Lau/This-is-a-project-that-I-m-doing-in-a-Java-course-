package Game;

import java.awt.Graphics;
import java.util.Random;

public class Invincible extends Elements {
    private Random ra = new Random();

    public Invincible() {
        super(0, 0, 34, 34);
        
        int a=ra.nextInt(750)+1;
        int b=ra.nextInt(550)+1;
        setX(a);
        setY(b);

        id=1;
    }
    public void draw(Graphics g){
	
		
		ImgSource.getInstance().drawInvincible(g, getX(), getY());

	}
}