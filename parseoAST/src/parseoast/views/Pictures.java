package src.parseoast.views;

import org.eclipse.swt.graphics.Image;
import src.org.eclipse.wb.swt.ResourceManager;

public class Pictures {
	private int x = 238;
	private int y =22;
	private int pos = 0;
	private int width = 0;
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}

	private int height = 0;
	
	public int getPos() {
		return pos;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Image FOR () {
		Image imagen = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/decision-symbol.jpg");
		
		if (pos !=0) {
			y+=100;
		}
		width = 124;
		height = 60;
		pos+=1;
		return imagen;
	}
	public Image Start () {
		Image imagen = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/start-end-process.jpg");
		if (pos!= 0) {
			y+=80;
		}
		width = 124;
		height = 60;
		pos+=1;
		return imagen;
	}
	public Image process () {
		Image imagen = ResourceManager.getPluginImage("parseoAST", "Iconos de Diagrama/action-process-symbol.png");
		
		if (pos !=0) {
			y+=100;
		}
		width = 124;
		height = 60;
		pos+=1;
		return imagen;
	}
}
