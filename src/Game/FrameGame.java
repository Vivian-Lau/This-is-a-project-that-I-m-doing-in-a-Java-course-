package Game;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;
import java.util.Random;
import java.util.ArrayList;

public class FrameGame extends JFrame {
	private static int Page = 0;
	private int marks=0;
	private Start st = new Start();

	private Tank tank;
	// private Tank tank2;
	private ArrayList playerBullets = new ArrayList();
	private ArrayList autoBullets = new ArrayList();
	private ArrayList elements = new ArrayList();
	// private ArrayList<AutoTank> autoTank=new ArrayList<AutoTank>();
	private ArrayList autoTanks = new ArrayList();
	private ArrayList explodes = new ArrayList();
	private Map map = new Map();
	private int tankCoolTime = 1;
	private Image offScreenImage = null;
	private Graphics gOffScreen = null;

	FreshThread t = new FreshThread();

	public FrameGame() {
		super("TankWar");
		setSize(800, 600);
		this.setResizable(false);
		//关闭弹窗
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				int option = JOptionPane.showConfirmDialog(null, "          退出游戏？",
						"提示", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (option == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		//关闭弹窗
		ImageIcon icon=new ImageIcon("Icon.jpg");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());
		this.addKeyListener(new KeyMonitor());
		tank = new PlayerTank();


		/*
		 * tank2=new PlayerTank(); tank2.setX(map.getMainPoint().x+40);
		 * tank2.setY(map.getMainPoint().y);
		 */
		Music mc = new Music();

		FreshThread t = new FreshThread();
		//t.start();
	}

	public void Iscollision(Tank tan, Elements ele) {
		if (tan.hit(ele.getBounds())) {
			switch (ele.id) {
			case 0:
				tan.life++;
				elements.remove(ele);
				break;
			case 1:
				tan.Invincibletime = 200;
				elements.remove(ele);
				break;
			case 2:
				tan.Doubleshottime = 200;
				elements.remove(ele);
				break;
			case 3:
				break;
			}
		}

	}

	public void paint(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(800, 600);
			gOffScreen = offScreenImage.getGraphics();
		}
		super.paint(gOffScreen);
		gOffScreen.fillRect(0, 0, 800, 600);
		if (Page == 0) {

			st.draw(gOffScreen);
			if (st.getchoose() == 1) {
				ImgSource.getInstance().drawPlayerTank(gOffScreen, 1, 264, 355, 2, 0, 1);
			} else {
				ImgSource.getInstance().drawPlayerTank(gOffScreen, 1, 264, 400, 2, 0, 1);
			}

		}
		if (Page == 3) {

			st.drawEnd(gOffScreen);

			//ImgSource.getInstance().drawPlayerTank(gOffScreen, 1, 264, 355, 2, 0, 1);

		} else if (Page == 1) {

			gOffScreen.setColor(Color.black);
			tank.draw(gOffScreen);
			for (int i = 0; i < elements.size(); i++) {
				Elements a = (Elements) elements.get(i);
				a.draw(gOffScreen);
			}

			for (int i = 0; i < playerBullets.size(); i++) {
				Bullet b = (Bullet) playerBullets.get(i);
				b.draw(gOffScreen);
			}

			for (int i = 0; i < autoTanks.size(); i++) {
				AutoTank a = (AutoTank) autoTanks.get(i);
				a.draw(gOffScreen);
			}
			for (int i = 0; i < autoBullets.size(); i++) {
				Bullet b = (Bullet) autoBullets.get(i);
				b.draw(gOffScreen);
			}
			for (int i = 0; i < elements.size(); i++) {
				Elements a = (Elements) elements.get(i);
				a.draw(gOffScreen);
			}
			for (int i = 0; i < explodes.size(); i++) {
				Explode e = (Explode) explodes.get(i);
				if (e.getAlive()) {
					e.draw(gOffScreen);
				} else {
					explodes.remove(e);
				}
			}

		}
		/*
		 * else if(Page==2){
		 * 
		 * 
		 * gOffScreen.setColor(Color.black);
		 * 
		 * tank.draw(gOffScreen); tank2.draw(gOffScreen);
		 * 
		 * 
		 * for(int i=0;i<playerBullets.size();i++){ Bullet b =
		 * (Bullet)playerBullets.get(i); b.draw(gOffScreen); }
		 * 
		 * for(int i=0;i<autoTanks.size();i++){ AutoTank a = (AutoTank)autoTanks.get(i);
		 * a.draw(gOffScreen); } for(int i=0;i<autoBullets.size();i++){ Bullet b =
		 * (Bullet)autoBullets.get(i); b.draw(gOffScreen); } for(int
		 * i=0;i<explodes.size();i++) { Explode e = (Explode)explodes.get(i);
		 * if(e.getAlive()) { e.draw(gOffScreen); }else { explodes.remove(e); } }
		 * 
		 * 
		 * }
		 */
		g.drawImage(offScreenImage, 0, 0, null);
	}

	private boolean hitBorder(Tank obj) throws InterruptedException {
		Point point;
		point = obj.getNextPosition();
		if (point.x < 17 || point.x > 800 - 17 || point.y < 17 || point.y > 600 - 17) {

			return true;
		}
		for (int i = 0; i < elements.size(); i++) {
			Elements a = (Elements) elements.get(i);
			if (a.id == 4 && a.hit(obj.getNextBounds())) {
				if (obj.gettype() == 1)
					elements.remove(a);
				return true;
			}
			if (a.id == 5 && a.hit(obj.getNextBounds()))
				return true;
			if (a.id == 6 && a.hit(obj.getNextBounds())) {
				if (obj.gettype() == 1)
					return false;
				return true;
			}
			if (a.id == 7 && a.hit(obj.getNextBounds()) ) {
				if (obj.gettype() == 1) {
					System.out.println(a.life);a.life--;
					if (a.life <= 0){
						elements.remove(a);
						Page=3;
						repaint();
						//Thread.sleep(500);
						JOptionPane.showMessageDialog(null,"你的基地被摧毁了，游戏结束，你得了"+marks+"分","提示",JOptionPane.PLAIN_MESSAGE);
						t.stop();
					}
					return true;
				}
				return true;
			}
		}
		return false;
	}

	private void openmap() {
		JFileChooser fileDialog = new JFileChooser();
		int state = fileDialog.showOpenDialog(null);
		fileDialog.setCurrentDirectory(new File("."));
		if (state == JFileChooser.APPROVE_OPTION) {
			String name = fileDialog.getSelectedFile().getPath();
			System.out.println(name);
			File file = new File(name);
			// 取得文件路径显示在tfDir文本框中
			try {
				// Desktop.getDesktop().open(file);//待会改
				InputStream in = new FileInputStream(file);
				InputStreamReader reader = new InputStreamReader(in);
				BufferedReader br = new BufferedReader(reader);
				String line = "";
				line = br.readLine();
				while (line != null) {
					String[] arrs = null;
					String[] arrs2 = null;
					String line_type = null;
					arrs = line.split("=");
					line_type = arrs[0];
					arrs2 = arrs[1].split(";");
					for (int j = 0; j < arrs2.length; j++) {
						String[] arrs3 = null;
						arrs3 = arrs2[j].split(",");
						Elements me;
	
						switch (line_type) {
						case "AUTOTANK":
							map.add(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							break;
						case "BASE":
							me = new Base(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]),100,100);
							elements.add(me);
							break;
						case "BRICK":
							me = new Brick(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							elements.add(me);
							break;
						case "GRASS":
							me = new Grass(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							elements.add(me);
							break;
						case "IRON":
							me = new Iron(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							elements.add(me);
							break;
						case "PLAYERTANK":
							map.setmainPoint(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							break;
						case "SPADE":
							break;
						default:
							me = new Water(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));
							elements.add(me);
							break;

						}
					}
					line = br.readLine();
				}
				tank.setX(map.getMainPoint().x);
				tank.setY(map.getMainPoint().y);
				br.close();
				in.close();
				reader.close();
				t.start();
				repaint();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());

		}

	}

	private class FreshThread extends Thread {
		/*private final Object lock = new Object();
		private boolean pause = false;

		public boolean getpause() {
			return pause;
		}

		public void pauseThread() {
			pause = true;
		}

		public void resumeThread() {
			pause = false;
			synchronized (lock) {
				lock.notify();
			}
		}*/

		public void run() {
			while (true) {

				/*while (pause) {
					synchronized (lock) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}*/


				{
					try {
						Thread.sleep(10);
						if (!hitBorder(tank)) {
							tank.go();
						}
						else{
							tank.fireCoolTime=tank.FIRCOOLTIME;
						}
						for (int i = 0; i < playerBullets.size(); i++) {
							Bullet b = (Bullet) playerBullets.get(i);
							if (hitBorder(b)) {
								explodes.add(new Explode(b.getX(), b.getY()));
								playerBullets.remove(b);
								continue;
							}
							for (int j = 0; j < autoTanks.size(); j++) {
								AutoTank a = (AutoTank) autoTanks.get(j);
								if (b.hit(a.getBounds())) {
									a.life--;
									if (a.life <= 0) {
										explodes.add(new Explode(a.getX(), a.getY()));
										autoTanks.remove(a);
										marks++;
									}
									playerBullets.remove(b);
									continue;
								}
							}
							b.go();
						}
						for (int i = 0; i < elements.size(); i++) {
							Elements a = (Elements) elements.get(i);

							Iscollision(tank, a);
						}

						for (int i = 0; i < autoTanks.size(); i++) {
							AutoTank a = (AutoTank) autoTanks.get(i);
							if (!hitBorder(a)) {
								a.go();
							}
							else {

								a.fireCoolTime=a.FIRCOOLTIME;

								Random rd=new Random();
								int d=rd.nextInt(4)+1;
								a.setDirection(d);
							}
							Bullet b = a.fire();
							if (b != null) {
								autoBullets.add(b);
							}
						}

						for (int i = 0; i < autoBullets.size(); i++) {
							Bullet b = (Bullet) autoBullets.get(i);
							if (hitBorder(b)) {
								explodes.add(new Explode(b.getX(), b.getY()));
								autoBullets.remove(b);
								continue;
							}
							if (tank.hit(b.getBounds())) {
								if (tank.Invincibletime <= 0) {
									tank.life--;
									if (tank.life <= 0) {
										explodes.add(new Explode(tank.getX(), tank.getY()));
										tank.Remove();
										Page=3;
										
										//Thread.sleep(500);
										repaint();
										JOptionPane.showMessageDialog(null,"你的坦克被摧毁了，游戏结束，你得了"+marks+"分","提示",JOptionPane.PLAIN_MESSAGE);
										t.stop();
										
									}
									
								}
								autoBullets.remove(b);
								continue;
							}

							b.go();
						}
						if (tank.Invincibletime > 0)
							tank.Invincibletime--;
						if (tank.Doubleshottime > 0)
							tank.Doubleshottime--;
						Random ra = new Random();
						int s = ra.nextInt(100);
						if (s == 0) {
							Addlife a = new Addlife();
							int mark=0;
							for(int i=0;i<elements.size();i++){
								Elements temp=(Elements)elements.get(i);
								if(a.hit(temp.getBounds())==true)  {mark=1;break;}
							}
							if(mark==0)	
								elements.add(a);
						} else if (s == 1) {
							Invincible a = new Invincible();
							int mark=0;
							for(int i=0;i<elements.size();i++){
								Elements temp=(Elements)elements.get(i);
								if(a.hit(temp.getBounds())==true)  {mark=1;break;}
							}
							if(mark==0)	
							elements.add(a);
						} else if (s == 2) {
							Doubleshot a = new Doubleshot();
							int mark=0;
							for(int i=0;i<elements.size();i++){
								Elements temp=(Elements)elements.get(i);
								if(a.hit(temp.getBounds())==true)  {mark=1;break;}
							}
							if(mark==0)	
							elements.add(a);
						}

						/*if (tank.alive == false)
							Page = 3;*/
						// else if()
						repaint();
						if (tankCoolTime == 50) {
							Random random = new Random();
							AutoTank aTank;
							int i = random.nextInt(3);// up
							if (i == 0)
								aTank = new AutoTank1();
							else if (i == 1)
								aTank = new AutoTank2();
							else
								aTank = new AutoTank3();
							if(map.getcount()!=0){
							aTank.setX(map.getPoints()[i%map.getcount()].x);
							aTank.setY(map.getPoints()[i%map.getcount()].y);
							}
							else {
								aTank.setX(400);
								aTank.setY(200);
							}
							aTank.setGoCategory(IntllgntFactory.getGoCategory(tank));
							autoTanks.add(aTank);
							tankCoolTime = 0;
						} else {
							tankCoolTime++;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				/*
				 * else if(Page==2){ try{ Thread.sleep(100); if(!hitBorder(tank)){ tank.go(); }
				 * if(!hitBorder(tank2)){ tank2.go(); } for(int i=0;i<playerBullets.size();i++){
				 * Bullet b = (Bullet)playerBullets.get(i); if(hitBorder(b)){ explodes.add(new
				 * Explode(b.getX(),b.getY())); playerBullets.remove(b); continue; } for(int
				 * j=0;j<autoTanks.size();j++){ AutoTank a = (AutoTank)autoTanks.get(j);
				 * if(b.hit(a.getBounds())){ a.life--; if(a.life<=0){ explodes.add(new
				 * Explode(a.getX(),a.getY())); autoTanks.remove(a); } playerBullets.remove(b);
				 * continue; } } b.go(); }
				 * 
				 * for(int i=0;i<autoTanks.size();i++){ AutoTank a = (AutoTank)autoTanks.get(i);
				 * if(!hitBorder(a)) { a.go(); } Bullet b = a.fire(); if(b != null) {
				 * autoBullets.add(b); } }
				 * 
				 * for(int i=0;i<autoBullets.size();i++){ Bullet b = (Bullet)autoBullets.get(i);
				 * if(hitBorder(b)){ explodes.add(new Explode(b.getX(),b.getY()));
				 * autoBullets.remove(b); continue; } if(tank.hit(b.getBounds())){ tank.life--;
				 * if(tank.life<=0){ explodes.add(new Explode(tank.getX(),tank.getY()));
				 * tank.Remove(); } autoBullets.remove(b); continue; } else
				 * if(tank2.hit(b.getBounds())){ tank2.life--; if(tank2.life<=0){
				 * explodes.add(new Explode(tank2.getX(),tank2.getY())); tank2.Remove(); }
				 * autoBullets.remove(b); continue; } b.go(); } repaint(); if(tankCoolTime==50){
				 * AutoTank aTank = new AutoTank(); Random random = new Random(); int i =
				 * random.nextInt(map.getcount());//up aTank.setX(map.getPoints()[i].x);
				 * aTank.setY(map.getPoints()[i].y); if(i%2==0&&tank.alive==true){
				 * aTank.setGoCategory(IntllgntFactory.getGoCategory(tank)); } else {
				 * aTank.setGoCategory(IntllgntFactory.getGoCategory(tank2)); }
				 * autoTanks.add(aTank); tankCoolTime=0; }else{ tankCoolTime++; }
				 * }catch(InterruptedException e){ e.printStackTrace(); } }
				 */

			}
		}
	}

	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_UP:
				/*
				 * if(Page==0){ if(st.getchoose()==1) st.setchoose(2); else{ st.setchoose(1); }
				 * 
				 * }
				 */
				if (Page == 1) {
					if (tank.getDirection() == 1)
						tank.setVelocity(tank.getVelocity() + 1);
					else {
						tank.setVelocity(2);
						tank.setDirection(1);
					}

				}
				/*
				 * else if(Page==2){ if(tank.getDirection()==1)
				 * tank.setVelocity(tank.getVelocity()+1); else{ tank.setVelocity(2);
				 * tank.setDirection(1); }
				 * 
				 * }
				 */
				break;
			case KeyEvent.VK_DOWN:
				/*
				 * if(Page==0){ if(st.getchoose()==1) st.setchoose(2); else{ st.setchoose(1); }
				 * 
				 * }
				 */
				if (Page == 1) {
					if (tank.getDirection() == 3)
						tank.setVelocity(tank.getVelocity() + 1);
					else {
						tank.setVelocity(2);
						tank.setDirection(3);
					}

				}
				/*
				 * else if(Page==2){ if(tank.getDirection()==3)
				 * tank.setVelocity(tank.getVelocity()+1); else{ tank.setVelocity(2);
				 * tank.setDirection(3); }
				 * 
				 * }
				 */
				break;
			case KeyEvent.VK_ENTER:
				if (Page == 0) {
					openmap();
					Page = 1;

				}
				if (Page == 3) {
					
					System.exit(0);
				}
				/*
				 * else if(Page==2){ Bullet bullet = tank.fire(); if(bullet != null) {
				 * playerBullets.add(bullet); } }
				 */
				break;

			case KeyEvent.VK_RIGHT:
				if (Page == 1) {
					if (tank.getDirection() == 2)
						tank.setVelocity(tank.getVelocity() + 1);
					else {
						tank.setVelocity(2);
						tank.setDirection(2);
					}
				}
				/*
				 * else if(Page==2){ if(tank.getDirection()==2)
				 * tank.setVelocity(tank.getVelocity()+1); else{ tank.setVelocity(2);
				 * tank.setDirection(2); } }
				 */
				break;
			case KeyEvent.VK_LEFT:
				if (Page == 1) {
					if (tank.getDirection() == 4)
						tank.setVelocity(tank.getVelocity() + 1);
					else {
						tank.setVelocity(2);
						tank.setDirection(4);
					}
				}
				/*
				 * else if(Page==2){ if(tank.getDirection()==4)
				 * tank.setVelocity(tank.getVelocity()+1); else{ tank.setVelocity(2);
				 * tank.setDirection(4); } }
				 */
				break;
			case KeyEvent.VK_SPACE:
				if (Page == 1) {
					Bullet bullet = tank.fire();
					if (bullet != null) {
						playerBullets.add(bullet);
						if (tank.Doubleshottime > 0) {
							tank.fireCoolTime = tank.FIRCOOLTIME;
						}

					}
				}
			case KeyEvent.VK_P:
				if (Page == 1) {
					try {
						t.wait();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				/*else if(Page==2){
					Bullet bullet2 = tank2.fire();
					if(bullet2 != null) {
						playerBullets.add(bullet2);
					}
				}
				break;
				case KeyEvent.VK_W:
				if(Page==2){
					if(tank2.getDirection()==1) tank2.setVelocity(tank2.getVelocity()+1);
					else{
						tank2.setVelocity(2);
						tank2.setDirection(1);
					}
				}*/
				break;
				/*case KeyEvent.VK_S:
				if(Page==2){
					if(tank2.getDirection()==3) tank2.setVelocity(tank2.getVelocity()+1);
					else{
					tank2.setVelocity(2);
					tank2.setDirection(3);	
					}
				}
				break;
				case KeyEvent.VK_D:
				if(Page==2){
					if(tank2.getDirection()==2) tank2.setVelocity(tank2.getVelocity()+1);
					else{
						tank2.setVelocity(2);
						tank2.setDirection(2);	
					}
				}
				break;
				case KeyEvent.VK_A:
				if(Page==2){
					if(tank2.getDirection()==4) tank2.setVelocity(tank2.getVelocity()+1);
					else{
						tank2.setVelocity(2);
						tank2.setDirection(4);
						}
				}
				break;
				case KeyEvent.VK_P:
				if(Page==1||Page==2){
					if(t.getpause()==false)
						
						t.pauseThread();

					else t.resumeThread();
				}
				break;
				*/
			}
			repaint();	
		}	
	}
}
