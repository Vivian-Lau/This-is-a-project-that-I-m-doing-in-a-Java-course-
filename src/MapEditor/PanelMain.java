package MapEditor;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JPanel;

import Game.ImgSource;

public class PanelMain extends JPanel{

	private PanelIcon panelIcon;
	private List<MapElement> elementList = new ArrayList<MapElement>();

	private boolean changed=false;

	private List<MapElement> all_elementList = new ArrayList<MapElement>();
	private List<Boolean> opList = new ArrayList<Boolean>();
	private int counts=-1;
	private boolean add=true;
	private boolean remove=false;

	public void initPanel() {
		this.addMouseListener(new MouseClick());
		this.addMouseMotionListener(new MouseDrag());
		this.addKeyListener(new KeyClick());
	}
	public void setPanelIcon(PanelIcon panelIcon) {
		this.panelIcon = panelIcon;
	}
	/**
	 * 重写绘制组件方法
	 */	
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0;i<elementList.size();i++) {
			elementList.get(i).draw(g);
		}			
		
	}
	public void removeall(){
		elementList.clear();
	}
	public boolean getchanged(){
		return changed;
	}
	public void setchanged(boolean change){
		changed = change;
	}
	private class KeyClick extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
	
			switch(e.getKeyCode()) {
			case KeyEvent.VK_DELETE:
				System.out.println("按下了delete键");
			}
		}			
	}

	private class MouseClick extends MouseAdapter {
		public void mouseClicked(MouseEvent obj) {
			//JPanel panel = (JPanel)obj.getSource();
			//panel.requestFocus();//每次点击时，PanelMain获得输入焦点，以监听键盘事件。
			int index = -1;
			for(int i=0;i<elementList.size();i++) {	
				if(elementList.get(i).pointSelect(obj.getX(), obj.getY())) {
					index = i;
				}
			}
			MapElement curElement;
			curElement = panelIcon.getCurElement();
			if(index != -1) {				
				if(curElement instanceof MapElementSpade) {
					all_elementList.add(elementList.get(index));
					opList.add(remove);
					counts++;
					elementList.remove(index);
				}
			}else {
				if(curElement != null) {
					if(!(curElement instanceof MapElementSpade)){
						elementList.add(curElement.CloneElement(obj.getX()/curElement.width*curElement.width, 
								obj.getY()/curElement.width*curElement.width));
						all_elementList.add(curElement.CloneElement(obj.getX()/curElement.width*curElement.width,
								obj.getY()/curElement.width*curElement.width));
						opList.add(add);
						counts++;
					}
				}				
			}
			setchanged(true);
			repaint();//重新绘制界面，不重新绘制界面无法看到图形
		}
	}

	private class MouseDrag extends MouseAdapter{
		public void mouseDragged(MouseEvent obj){
			int index = -1;
			for(int i=0;i<elementList.size();i++) {	//找点击对应元素
				if(elementList.get(i).pointSelect(obj.getX(), obj.getY())) {
					index = i;
				}
			}
			MapElement curElement;
			curElement = panelIcon.getCurElement();
			if(index != -1) {
				if(curElement instanceof MapElementSpade) {
					elementList.remove(index);
					counts++;
				}
			}else {
				if(curElement != null) {
					if(!(curElement instanceof MapElementSpade)){
						elementList.add(curElement.CloneElement(obj.getX()/curElement.width*curElement.width,
								obj.getY()/curElement.width*curElement.width));
						all_elementList.add(curElement.CloneElement(obj.getX()/curElement.width*curElement.width,
								obj.getY()/curElement.width*curElement.width));
						opList.add(add);
						counts++;
					}
				}
			}
			setchanged(true);
			repaint();//重新绘制界面，不重新绘制界面无法看到图形
		}

	}
	public void saveMap(File file) throws FileNotFoundException {
		Map<String,String> map = new HashMap<String,String>();
		for(int i=0;i<elementList.size();i++) {
			String type = elementList.get(i).getType();	
			String value = map.get(type);
			if(value != null) {
				value = value + elementList.get(i);
			}else {
				value = elementList.get(i).toString();
			}
				
			map.put(type, value);
		}
	
		OutputStream out = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(out);
		BufferedWriter bw = new BufferedWriter(writer);
		PrintWriter pw = new PrintWriter(bw,true);
		
		Set<String> sets = map.keySet();//返回键的set集合
		for(String str: sets) {//遍历每一个键值
			System.out.println(map.get(str));//返回键值对应的value
			pw.println(str + "="+map.get(str));
		}

		pw.close();
	}

	public void openMap(File file) throws Exception {

		InputStream in =new FileInputStream(file);
		InputStreamReader reader=new InputStreamReader(in);
		BufferedReader br= new BufferedReader(reader);
		String line="";
		line=br.readLine();
		while(line!=null) {
			String[] arrs=null;
			String[] arrs2=null;
			String line_type=null;
			arrs=line.split("=");
			line_type=arrs[0];
			arrs2=arrs[1].split(";");
			for(int j=0;j<arrs2.length;j++){
				String[] arrs3=null;
				arrs3=arrs2[j].split(",");
				MapElement me;
				System.out.println(arrs2.length);
				switch(line_type){
					case "AUTOTANK":
						me=new MapElementAutoTank(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "BASE":
						me=new MapElementBase(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "BRICK":
						me=new MapElementBrick(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "GRASS":
						me=new MapElementGrass(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "IRON":
						me=new MapElementIron(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "PLAYERTANK":
						me=new MapElementPlayerTank(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					case "SPADE":
						me=new MapElementSpade(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					default:
						me=new MapElementWater(Integer.valueOf(arrs3[0]), Integer.valueOf(arrs3[1]));break;
					
				}elementList.add(me);
			}
            line=br.readLine();
		}
		br.close();
		in.close();
		reader.close();
		repaint();
	}


    public void Undo(){
		if(opList.get(counts)){
			elementList.remove(elementList.size()-1);
		}
		else{
			elementList.add(all_elementList.get(counts));
		}
		counts--;
	    repaint();
    }
    public void Redo(){
		if(opList.get(counts+1)){
			elementList.add(all_elementList.get(counts+1));
		}
		else{
			elementList.remove(all_elementList.size()-1);
		}
		counts++;
	    repaint();
    }
}
