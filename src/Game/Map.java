package Game;

import java.awt.Point;
public class Map {
	private Point[] points;
	private Point mainPoint;
	private int count; 
	public Map(){
		points = new Point[100];
		/*points[0] = new Point(50,50);
		points[1] = new Point(400,50);
		points[2] = new Point(750,50);*/
		count=0;
		mainPoint = new Point(400,550);
	}
	public void add(int x,int y){
		points[count]=new Point(x,y);
		count++;
	}
	public void setmainPoint(int x,int y){
		mainPoint.x=x;
		mainPoint.y=y;

	}
	public void clear(){
		count=0;
	}
	public int getcount(){
		return count;
	}
	public Point[] getPoints(){
		return points;
	}
	public Point getMainPoint(){
		return mainPoint;
	}
}
