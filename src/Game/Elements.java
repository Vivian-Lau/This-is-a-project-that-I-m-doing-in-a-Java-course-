package Game;

import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
public class Elements {
	private int x,y;
	private int width,height;
	protected int life;
	protected int alllife;
	protected int id;
	protected boolean alive = true; //是否存活
	public Elements(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public Rectangle getBounds() {
		// 创建 一个坐标在（x,y）位置，宽度为(width,height)的矩形边界对象并返回
		return new Rectangle(x,y,width,height);
	}
	/**
	 * 判断是否撞到其它坦克
	 * @param x -自身坦克的横坐标
	 * @param y -自身坦克的纵坐标
	 *
	 */
	public boolean hit(Rectangle r) {
		if(r == null) {//如果目标为空
			return false; //返回false,不发生碰撞
		}
		return getBounds().intersects(r); //返回两者的边界对象是否相交
	}
	public void draw(Graphics g){
			
	}	
	public void setX(int x){
		this.x = x;
	}
	public int getX(){
		return x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getY(){
		return y;
	}
	public void setwidth(int x){
		width=x;
	}
	public void setheight(int x){
		height=x;
	}
	public void Remove(){
		setX(-100);
		setY(-100);
		setAlive(false);
	}

	
}
