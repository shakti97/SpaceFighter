package Sprite;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import bullet.Bullet;
import gameUtils.GameConstant;

public class Player extends Sprite implements GameConstant {
	final int speed=2;
	public ArrayList<Bullet> bullets= new ArrayList<>();
	public Player() {
		img= new ImageIcon(Sprite.class.getResource("rocket.gif")).getImage();
		h = w = 120;
		x=(GAME_WIDTH / 2)-(w/2);
		y=GAME_HEIGHT - (h+50) ;
		isVisible=true;
		xSpeed=ySpeed=0;
		life=3;
	}
	public void addBullets() {
		Bullet bullet = new Bullet(this.x+((this.w)/2)-5, this.y);
		bullets.add(bullet);
	}
	public void drawBullets(Graphics g) {
		for(Bullet bullet : bullets) {
			if(bullet.isVisible()) {
			bullet.drawBullet(g, Player);
		}}
		
	}
	public void drawPlayer(Graphics g) {
		g.drawImage(img, x,y,w,h,null);
	}
	public void move(){
		x+= xSpeed;
		y+=ySpeed;
	}
	public void stop() {
		xSpeed=0;
		ySpeed=0;
	}
	
	public void up() {
		ySpeed+= -speed;
		if(y<500) {
			stop();
			setY(500);
		}
	}
	public void down() {
		ySpeed+= speed;
		if(y>=GAME_HEIGHT-150) {
			stop();
			setY(GAME_HEIGHT-150);
		}
	}
	public void left() {
		xSpeed+= -speed;
		if(x<12) {
			stop();
			setX(12);
		}
	}
	public void right() {
		
		xSpeed+= speed;
		if(x>=GAME_WIDTH-140 ) {
			stop();
			setX(GAME_WIDTH-140);
		}
	}
	
	

}
