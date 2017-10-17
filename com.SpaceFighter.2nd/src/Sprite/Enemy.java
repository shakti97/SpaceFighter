package Sprite;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import bullet.Bullet;
import gameUtils.GameConstant;

public class Enemy extends Sprite implements GameConstant{
	public ArrayList<Bullet> bullets= new ArrayList<>();
	int a=0;
	public Enemy(int x,int y) {
		img = new ImageIcon(Sprite.class.getResource("alien2.gif")).getImage();
		this.x=x;
		this.y=y;
		w=h=100;
		isVisible=true;
		xSpeed=ySpeed=2;
		life=3;
	}
	public void addBullets() {
		Bullet bullet = new Bullet(this.x+((this.w)/2)-5, this.y+100);
		bullets.add(bullet);
		y+=this.ySpeed+20;
		//x+=this.xSpeed+20;
	}
	public void drawBullets(Graphics g) {
		for(Bullet bullet : bullets) {
			if(bullet.isVisible()) {
			bullet.drawBullet(g, Enemy);
//			bullet.move();
			if(y>50) {
			y-=ySpeed;
			}
		    }
		}
	}
	
	public void drawEnemy(Graphics g){
		g.drawImage(img, x, y, w, h, null);
	}

}
