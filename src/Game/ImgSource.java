package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImgSource {
	private static ImgSource imgs=null;
	private static Image image=null;
	private static ImgSource imgs2=null;
	private static Image image2=null;


	int imagex1,imagey1,imagex2,imagey2;
	private ImgSource(){
		initImage();
	}
	public static ImgSource getInstance(){
		if(imgs == null){
			imgs = new ImgSource();
		}
		return imgs;
	}
	
	public void initImage(){
		File f = new File("insect_sprite.png");
		File f2= new File("life.png");

		try{
			image = ImageIO.read(f);
			image2 = ImageIO.read(f2);


		}catch(IOException e){
			e.printStackTrace();
		}
	}

	
	public void drawBullet(Graphics g,int state,int x,int y,int direction){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 5*34;
		imagey1 = 6 *34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;		
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawPlayerTank(Graphics g,int state,int x,int y,int direction,int life,int alllife){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	

		g.drawImage(image2,x - 25, y - 25, x-21, y-25+(int)(34 * ((double) life / alllife)),0,0,6,34,null);
	}
	public void drawAutoTank1(Graphics g,int state,int x,int y,int direction,int life,int alllife){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34;
		imagey1 = 34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
		g.drawImage(image2,x - 25, y - 25, x-21, y-25+(int)(34 * ((double) life / alllife)),0,0,6,34,null);
	}
	public void drawAutoTank2(Graphics g,int state,int x,int y,int direction,int life,int alllife){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34;
		imagey1 = 307;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
		g.drawImage(image2,x - 25, y - 25, x-21, y-25+(int)(34 * ((double) life / alllife)),0,0,6,34,null);
	}
	public void drawAutoTank3(Graphics g,int state,int x,int y,int direction,int life,int alllife){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (direction-1)*34*2+state*34+546;
		imagey1 = 273;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
		g.drawImage(image2,x - 25, y - 25, x-21, y-25+(int)(34 * ((double) life / alllife)),0,0,6,34,null);
	}
	public void drawPlayerTank(Graphics g,int x,int y){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x,y,
				x+34,y+34,
				imagex1,imagey1,
				imagex2,imagey2,null);	

	}
	public void drawAutoTank(Graphics g,int x,int y){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 0;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x,y,
				x+34,y+34,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawExplode(Graphics g,int state,int x,int y) {
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = (state+20)*34;
		imagey1 = 4*34;
		imagex2 = imagex1+34;
		imagey2 = imagey1+34;	
		g.drawImage(image,
				x-17,y-17,
				x+17,y+17,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}
	public void drawSpade(Graphics g,int x,int y) {
		drawElement(g,x,y,24,7,34);		
	}
	public void drawWater(Graphics g,int x,int y) {
		drawElement(g,x,y,0,240,34);			
	}
	public void drawGrass(Graphics g,int x,int y) {
		drawElement(g,x,y,136,238,34);		
	}
	public void drawBrick(Graphics g,int x,int y) {
		drawElement(g,x,y,137,171,8);			
	}
	public void drawIron(Graphics g,int x,int y) {
		drawElement(g,x,y,0,206,17);			
	}
	public void drawBase(Graphics g,int x,int y,int life,int alllife) {
		drawElement(g,x,y,645,172,34);

		g.drawImage(image2,x - 25, y - 25, x-21, y-25+(int)(34 * ((double) life / alllife)),0,0,6,34,null);
	}
	public void drawAddlife(Graphics g,int x,int y) {
		drawElement(g,x,y,749,170,34);	
		
	}
	public void drawInvincible(Graphics g,int x,int y) {
		drawElement(g,x,y,820,207,34);	
		
	}
	public void drawDoubleshot(Graphics g,int x,int y) {
		drawElement(g,x,y,922,208,34);	
		
	}
	public void drawElement(Graphics g,int x,int y,int imgX,int imgY,int width) {
		
		g.drawImage(image,
				x,y,
				x+width,y+width,
				imgX,imgY,
				imgX+width,imgY+width,null);
	}	


}
