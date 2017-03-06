/*
	隨機產生有isInScreen的bug
	可能要讓monsters排序
*/
package ce1002.f.s101201521;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.border.LineBorder;
public class BattlePanel extends JPanel{
	private MainFrame frame;
	//mouse X
	private int mx;
	//mouse Y
	private int my;
	private int monsterKind = -1;
	private int monsterMode = 1;
	//seperate the timer
	private int seperate = 0;
	//hero move time
	private int heroT = 0;
	//wave of the monsters
	public static int wave = 0;
	//stage for the game
	public static int stage = 1;
	//monster killed
	public static int monsterKilled = 0;
	//whether the monsters have been send
	public static boolean isSend = false;
	//whether the direction of the weapon is stuck
	private static boolean isStuck = false;
	//list for monsters
	public LinkedList<Monster> monsters = new LinkedList<Monster>();
	//list for weapons
	public LinkedList<Weapon> weapons = new LinkedList<Weapon>();
	//list for fatal effects
	public LinkedList<FatalEffect> effects = new LinkedList<FatalEffect>();
	//button for pause
	public MyButton pauseBt = new MyButton("Pause",6);
	private static Random random = new Random();
	//player status panel
	public Player player = new Player();
	//images for the hero
	private static Image[] heroImg = new Image[7]; 
	//current hero image
	private Image curHeroImg;
	//image for the background
	private Image Bg = new ImageIcon("src/image/battleBg.png").getImage();
	public Timer shooting = new Timer(15, new ShootingListener());
	public BattlePanel(){
		setLayout(null);
		setBounds(0,0,(int)(MainFrame.screemSize.getWidth()), (int)(MainFrame.screemSize.getHeight()));
		addMouseMotionListener(new DirectionOfShootFromMouse());
		addMouseListener(new IsStuckFromMouse());
		add(pauseBt);
		pauseBt.setBounds(5,5,200,100);
		add(player);
		player.setBounds((int)(MainFrame.screemSize.getWidth()/2.0) - 600, 720, 1200, 50);
		for(int i = 0; i < heroImg.length; i++){
			heroImg[i] = new ImageIcon("src/image/move" + i + ".png").getImage();
		}
		curHeroImg = heroImg[0];
	}
	//send a wave of monster
	public void sendMonster(){
		wave++;
		monsterMode = -1;
		monsterKind = -1;
		// monsters // kind // mode // stage
		Monster.generateMsh(monsters, monsterKind, monsterMode, stage);
	}
	public void setFrame(MainFrame frame){
		 this.frame = frame;
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(Bg, 0,0,(int)(MainFrame.screemSize.getWidth()), (int)(MainFrame.screemSize.getHeight()), this);
		g.drawImage(curHeroImg, 0, 210, 300, 300, this);
		//monsters' body
		for(int i = 0; i < monsters.size(); i++){
			if(!monsters.get(i).isInScreen){
				break;
			}
			monsters.get(i).body(g, this);
		}
	
		//weapons' body
		for(int i = 0; i < weapons.size(); i++){
			weapons.get(i).body(g, this);
		}
	}
	//fatal effect
	public void fatalEffect(int ox, int oy){
		FatalEffect curEffect = new FatalEffect(ox, oy);
		effects.add(curEffect);
		add(curEffect);
	}
	//fatal effect
	public class FatalEffect extends JPanel{
		private int ox, oy;
		public int t = 0;
		public FatalEffect(int ox, int oy){
			this.ox = ox;
			this.oy = oy;
			setLayout(null);
			setBounds(ox - 25, oy - 25,50, 50);
		}
		@Override
		protected void paintComponent(Graphics g){
			g.drawImage(new ImageIcon("src/image/fatal_frame_"+ (t-1)/3+".png").getImage(), 0, 0, 50, 50, this);
		}
	}
	//listener of the mouse motion get the direction to shoot
	class DirectionOfShootFromMouse extends MouseMotionAdapter{
		@Override
		public void mouseMoved(MouseEvent e){
			//get mouse location
			if(!isStuck){
			mx = e.getX();
			my = e.getY();
			}
		}
		@Override
		public void mouseDragged(MouseEvent e){
			//get mouse location
			if(!isStuck){
			mx = e.getX();
			my = e.getY();
			}
		}
	}
	//listener for clicks to stuck or unstuck the direction
	class IsStuckFromMouse extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e){
			isStuck = !isStuck;
		}
	}
	class ShootingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(seperate % 66 == 0 && Player.hp < 100){
				Player.hp++;
			}
			
			//generate boss
			if(stage >= 50 && wave < 5 && seperate % 1000 == 0){
				Monster.generateBoss(monsters, stage);
			}
			//send wave of monsters
			if(monsters.size() <= 5 && wave < 5){
				sendMonster();
			}
			//check whether the player is dead
			if(player.hp <= 0){
				frame.dead();
			}
			//check attack and action
			for(int i = 0; i < weapons.size(); i++){
				int wx = (int)(weapons.get(i).x);
				int wy = (int)(weapons.get(i).y);
				for(int j = 0; j < monsters.size(); j++){
					int sX = monsters.get(j).sizeX/2;
					int sY = monsters.get(j).sizeY/2;
					int mX = (int)(monsters.get(j).ox);
					int mY = (int)(monsters.get(j).oy - sY);
					if(!monsters.get(j).isInScreen){
						break;
					}
					else if(wx >= mX - sX && wx <= mX + sX &&
							wy >= mY - sY && wy <= mY + sY){
						weapons.get(i).isAlive = false;
						int f = random.nextInt(100);
						if(f<Skill.fatal){
							monsters.get(j).hp -= 2 * Skill.strength;
							fatalEffect(monsters.get(j).ox, monsters.get(j).oy - monsters.get(j).sizeY);
						}
						else{
							monsters.get(j).hp -= Skill.strength;
						}
						if(monsters.get(j).kind != 6){
							monsters.get(j).ox += Skill.repel;
						}
						if(monsters.get(j).hp <= 0){
							monsters.get(j).isAlive = false;
							Player.money += monsters.get(j).money[monsters.get(j).kind];
						}
						break;
					}
				}
			}
			//remove the effect after 1.5 seconds
			for(int i = 0; i < effects.size(); i++){
				effects.get(i).t++;
				if(effects.get(i).t == 15){
					remove(effects.get(i));
					effects.remove(i);
				}
			}
			//remove the used weapon and move
			for(int i = 0; i < weapons.size(); i++){
				if(!weapons.get(i).isAlive){
					weapons.remove(i);
					i--;
					continue;
				}
				weapons.get(i).shoot(seperate);
			}
			if(seperate % 5 ==0){
			//remove the dead monster and move
			for(int i =0; i < monsters.size(); i++){
				if(!monsters.get(i).isAlive){
					/*
					if(monsters.get(i).kind == 6){
						Monster.generateLmsh(monsters, monsters.get(i).ox, monsters.get(i).oy - monsters.get(i).sizeY/2);
					}
					*/
					monsters.remove(i);
					monsterKilled++;
					i--;
					continue;
				}
				monsters.get(i).move(seperate);
			}
			}
			//generate weapon
			if(seperate%(50 - Skill.agility) == 0){
				Weapon.generateWeapon(weapons, mx, my, Skill.amount);
			}
			//the frame of the hero
			if(seperate % 3 == 0){
				heroT++;
				heroT = heroT % 7;
				curHeroImg = heroImg[heroT];
			}
			repaint();
			seperate++;
			//check whether player is won
			if(wave == 5 && monsters.size() == 0){
				frame.win();
			}
		}
		
	}
}
