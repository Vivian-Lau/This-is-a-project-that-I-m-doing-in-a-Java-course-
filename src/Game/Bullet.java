package Game;

import java.awt.Graphics;

public class Bullet extends Tank{
	public Bullet(){
		super(-100,-100,17,17);
		setVelocity(10);
	}
	public void draw(Graphics g){
		
		ImgSource.getInstance().drawBullet(g,0,getX(),getY(),getDirection());
	}
	public void go(){
		goForward();
	}
	public int gettype(){
		return 1; 			//是炮弹
	}
	

}
