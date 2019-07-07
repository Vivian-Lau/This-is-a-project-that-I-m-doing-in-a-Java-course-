package Game;

import java.awt.Graphics;
import java.util.Random;
public class AutoTank3 extends AutoTank{
    private int id=2;
    public AutoTank3(){
        super();
        life=5;
        alllife=5;
        setVelocity(7);
        
    }
    public void draw(Graphics g){			
		
		ImgSource.getInstance().drawAutoTank3(g,state,getX(),getY(),getDirection(),life,alllife);
	}
    
}