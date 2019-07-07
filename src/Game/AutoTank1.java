package Game;

import java.awt.Graphics;
import java.util.Random;
public class AutoTank1 extends AutoTank{
    private int id=0;
    public AutoTank1(){
        super();
        life=1;
        alllife=1;
        setVelocity(2);
        
    }
    public void draw(Graphics g){			
		
		ImgSource.getInstance().drawAutoTank1(g,state,getX(),getY(),getDirection(),life,alllife);
	}
    
}