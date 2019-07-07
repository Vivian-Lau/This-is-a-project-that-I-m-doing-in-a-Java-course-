package Game;

import java.awt.Graphics;
import java.util.Random;
public class AutoTank2 extends AutoTank{
    private int id=1;
    public AutoTank2(){
        super();
        life=3;
        alllife=3;
        setVelocity(5);
        
    }
    public void draw(Graphics g){			
		
		ImgSource.getInstance().drawAutoTank2(g,state,getX(),getY(),getDirection(),life,alllife);
	}
    
}