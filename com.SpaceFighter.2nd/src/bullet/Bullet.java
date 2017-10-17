package bullet;

import java.awt.Color;
import java.awt.Graphics;

import Sprite.Sprite;
import gameUtils.GameConstant;

public class Bullet extends Sprite implements GameConstant {
	boolean isVisible=true;
	
	public Bullet(int x, int y) {
		this.x=x;
		this.y=y;
		
		
	}
	public void move() {
		y+=2*(ySpeed);
	}
	public void drawBullet(Graphics g, int Owner) {
		if(Owner==Player) {
			g.setColor(Color.YELLOW);
			ySpeed=-10;
		}
		if(Owner==Enemy) {
			g.setColor(Color.RED);
			ySpeed=10;
		}
		g.fillOval(x,y, 10, 10);
		move();
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
