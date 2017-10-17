package board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Sprite.Enemy;
import Sprite.Player;
import bullet.Bullet;
import gameUtils.GameConstant;
import gameUtils.RandomGenerator;

public class Board extends JPanel implements GameConstant{
	Image background;
	Timer timer;
	Player player;
	Enemy enemies[] = new Enemy[MAX_ENEMY];
	private static RandomGenerator range= new RandomGenerator(MAX_ENEMY);
	int a=50;
	private boolean gameover;
	private boolean gameOverValue=true;
	private int score;
	Life lifes[] = new Life[3];
	private int lifeLine;
	public Board() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		loadBackgound();
     	player = new Player();
     	setFocusable(true);
		bindEvents();
		loadEnemies();
		loadLife();
		gameLoop();
		
		
	}
	private void loadBackgound() {
		background= new ImageIcon(Board.class.getResource("Spaceimg2.jpg")).getImage();
	}
	private void drawBackGround(Graphics g) {
		g.drawImage(background,0, 0, GAME_WIDTH, GAME_HEIGHT, null);
	}
	private void bindEvents() {
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				player.stop();
			}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_SPACE) {
					player.addBullets();
				}
				if(e.getKeyCode()==KeyEvent.VK_UP) {
					player.up();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN) {
					player.down();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT) {
					player.left();
				}if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
					player.right();
				}
			}
		});
	}
	private void loadEnemies(){
		int x=170;
		for(int i=0; i<MAX_ENEMY;i++) {
			enemies[i]= new Enemy(x,50);
			x+=200;
		}
	}
	private void drawEnemy(Graphics g) {
		for(Enemy enemy : enemies) {
			if(enemy.isVisible()) {
			enemy.drawEnemy(g);
			enemy.drawBullets(g);
			}
		}
	}
	private void shootBullet(Graphics g) {
		int i=range.getRandomGenerator();
		enemies[i].addBullets();
		
	}
	
	private void checkCollision() {
		for(int i=0;i<player.bullets.size();i++) {
			for(int j=0;j<MAX_ENEMY;j++) {
				if(player.bullets.get(i).isVisible() && enemies[j].isVisible()) {
					if(isCollisionEnemy(player.bullets.get(i) , enemies[j]) ){
						player.bullets.get(i).setVisible(false);
						score+=50;
						
						if(enemies[j].getLife()>0) {
						enemies[j].setLife(enemies[j].getLife()-1);
						}
						if(enemies[j].getLife()==0) {
							enemies[j].setVisible(false);
							score+=1000;
						}
					}
				}
			}
		}
		for(int i=0;i<MAX_ENEMY;i++) {
		for(int j=0;j<enemies[i].bullets.size();j++) {
			System.out.println("LIFE IS " +player.getLife());
			if(enemies[i].bullets.get(j).isVisible() && player.isVisible()) {
				if(isCollisionPlayer(enemies[i].bullets.get(j) , player)) {
					enemies[i].bullets.get(j).setVisible(false);
					lifeLine++;
					if(player.getLife()>0) {
						player.setLife(player.getLife()-1);
						System.out.println("life is " + player.getLife());
						}
					if(player.getLife()==0) {
						player.setVisible(false);
						System.out.println("hey i m woking");
					}
				}
			}
			
		}
		}
	}
	private boolean isCollisionPlayer(Bullet bullet , Player player) {
		int xDistance=Math.abs(bullet.getX()-player.getX());
		int yDistance=Math.abs(bullet.getY()-player.getY());
		return xDistance<player.getW()+50 && yDistance<player.getH()-100;
	}
	
	private boolean isCollisionEnemy(Bullet bullet , Enemy enemies) {
		int xDistance=Math.abs(bullet.getX()-enemies.getX());
		int yDistance=Math.abs(bullet.getY()-enemies.getY());
		return xDistance<enemies.getW() && yDistance<enemies.getH();
	}
	private void gameOver(Graphics g) {
		if(isGameOver()) {
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,70));
		String value = gameOverValue?"GAME OVER ":"";
		g.drawString(value , ((GAME_WIDTH)/2)-250, (GAME_HEIGHT)/2);
		g.drawString("SCORE "+score,((GAME_WIDTH)/2)-200 , ((GAME_HEIGHT)/2+250) );
		gameOverValue = !gameOverValue;
		
		}
	}
	private boolean isGameOver() {
		    gameover=true;
			if(player.isVisible()) {
				gameover=false;
			}
			return gameover;

		}
	private void drawScore(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Arial",Font.BOLD,30));
		g.drawString("Score "+score , 50, 40);
		
		
	}
	
private void loadLife() {
	int x=800;
	int y=10;
		for(int i=0;i<3;i++) {
			lifes[i]= new Life(x,y);
			x-=110;
		}
	}
	private void visibility() {
		for(int i=0;i<lifeLine;i++) {
			lifes[i].setVisible(false);
		}
	}
	private void drawLife(Graphics g) {
		for(Life life: lifes) {
				if(life.isVisible) {
				life.imageScore(g);
				}
		}
	}
		
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackGround(g);
		checkCollision();
		if(player.isVisible()) {
		player.drawPlayer(g);
		player.drawBullets(g);
		}
		drawEnemy(g);
		if(!isGameOver()) {
		while(a>=500) {
		shootBullet(g);
		a=0;
		break;
		}}
		a+=50;
		drawScore(g);
		visibility();
		drawLife(g);
		gameOver(g);
	}
	private void gameLoop() {
		timer =new Timer(DELAY,(e)->{
		         repaint();	
		         player.move();
		});
		timer.start();
	}
	
	
}
