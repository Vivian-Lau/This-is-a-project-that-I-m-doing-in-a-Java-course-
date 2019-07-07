package Game;

import java.awt.Graphics;
import java.awt.*;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Start extends JPanel{

    private int choose=1;

    public Start(){
      
    }
    public void draw(Graphics g){
	
		StartImgSource.getInstance().drawStart(g);
    }

    public void drawEnd(Graphics g){
	
      StartImgSource.getInstance().drawSelect(g);
      }
    public  int getchoose(){
      return choose;
    }
  
    public  int setchoose(int n){
      choose=n;
      return choose;
    }

}
