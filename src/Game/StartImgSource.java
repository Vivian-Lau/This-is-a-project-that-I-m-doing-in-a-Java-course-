package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class StartImgSource {
	private static StartImgSource imgs = null;
	private static Image image=null;
	private static StartImgSource imgs2 = null;
	private static Image image2=null;
	int imagex1,imagey1,imagex2,imagey2;
	private StartImgSource(){
		initImage();
	}
	public static StartImgSource getInstance(){
		if(imgs == null){
			imgs = new StartImgSource();
		}
		return imgs;
	}
	
	public void initImage(){
		File f = new File("start.png");
		File f2= new File("gameover.jpg");
		try{
			image = ImageIO.read(f);
			image2 = ImageIO.read(f2);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	

	public void drawStart(Graphics g){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 0;
		imagex2 = imagex1+500;
		imagey2 = imagey1+294;		
		g.drawImage(image,
				0,0,
				800,600,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}	
	public void drawSelect(Graphics g){
		int imagex1,imagey1,imagex2,imagey2;
		imagex1 = 0;
		imagey1 = 0;
		imagex2 = imagex1+580;
		imagey2 = imagey1+507;		
		g.drawImage(image2,
				0,0,
				800,600,
				imagex1,imagey1,
				imagex2,imagey2,null);	
	}	
}
