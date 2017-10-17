package board;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Life {
	Image img;
	int w,h;
	protected int x;
	public boolean isVisible=true;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Life(int x, int y) {
		img = new ImageIcon(Board.class.getResource("Beating_Heart.gif")).getImage();
		w=h=100;
		this.x=x;
		
	}
	
	public void imageScore(Graphics g) {
		g.drawImage(img,x,10 , 50,50, null);
			}

}
