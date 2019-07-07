package Game;

import java.awt.Graphics;
import java.io.File;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import java.io.IOException;
public abstract class Tank {
	private int x,y;
	private int v;
	private int width,height;
	private int direction;//1up
	protected int life;
	protected int alllife;
	protected int state = 0;
	protected boolean alive = true; //�Ƿ���
	protected int fireCoolTime = 0;
	protected final int FIRCOOLTIME=10;
	public int Invincibletime=0;		//�޵�ʱ��
	public int Doubleshottime=0;		//��������ʱ��

	public Tank(int x,int y,int width,int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		v = 2;			
	}
	public int getVelocity(){
		return v;
	}
	public void setVelocity(int v){
		this.v = v;
	}
	public boolean getAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public Rectangle getBounds() {
		// ���� һ�������ڣ�x,y��λ�ã����Ϊ(width,height)�ľ��α߽���󲢷���
		return new Rectangle(x-width/2,y-height/2,width,height);
	}
	public Rectangle getNextBounds() {
		switch (direction){
			case 1:
			return new Rectangle(x-width/2,y-v-height/2,width,height);
			case 2:
			return new Rectangle(x+v-width/2,y-height/2,width,height);
			case 3:
			return new Rectangle(x-width/2,y+v-height/2,width,height);
			default:
			return new Rectangle(x-v-width/2,y-height/2,width,height);
		}
	}
	/**
	 * �ж��Ƿ�ײ������̹��
	 * @param x -����̹�˵ĺ�����
	 * @param y -����̹�˵�������
	 * @return ײ������̹�ˣ��򷵻�true
	 */
	public boolean hit(Rectangle r) {
		if(r == null) {//���Ŀ��Ϊ��
			return false; //����false,��������ײ
		}
		return getBounds().intersects(r); //�������ߵı߽�����Ƿ��ཻ
	}
	public void draw(Graphics g){
			
	}	
	public int gettype(){return 0;}

	public void setDirection(int dir){
		if(dir>0 && dir<=4){
			direction = dir;
		}
		
	}
	public int getDirection(){
		return direction;
	}
	public void go(){
		fireCoolTime++;		
	}
	public Bullet fire() {
		return null;
	}
	public Point getNextPosition() {
		Point point=new Point(x,y);
	
		switch(direction){
		case 1:
			point.y = y-v;break;
		case 2:
			point.x = x+v;break;
		case 3:
			point.y = y+v;break;
		case 4:
			point.x = x-v;break;
		}
		return point;
	}
	public void goForward(){
		if(state == 0){
			state = 1;
		}else{
			state = 0;
		}
		Point point;
		point = getNextPosition();
		x = point.x;
		y = point.y;
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
	public void Remove(){
		setX(-100);
		setY(-100);
		setVelocity(0);
		setAlive(false);
	}

	
}
