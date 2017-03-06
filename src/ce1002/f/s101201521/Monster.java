package ce1002.f.s101201521;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.*;

public class Monster {
	private Player player = new Player();
	private Random random = new Random();
	//current maxhp
	private static int[] maxhp = new int[7];
	//initial maxhp
	private static int[] maxhp0 ={3, 4, 5, 5, 6, 7, 200};
	//current hp
	public int hp;
	//whether the monster is dead or not
	public boolean isAlive = true;
	//whether the monster is in the screen
	public boolean isInScreen = false;
	public int ox;//image horizontal center
	public int oy;//image vertical bottom
	//x size of the current monster
	public int sizeX = 10;
	//y size of the current monster
	public int sizeY = 10;
	//monsters' drop money
	public static int[] money = {10, 10, 12, 15, 20, 30, 200};
	//monsters' width 
	private static int[] sizeXs ={36, 60, 50, 50, 60, 60, 100};
	//monsters' height
	private static int[] sizeYs ={36, 60, 50, 50, 60, 60, 100};
	//monster's speed
	private static int[] speeds = {7, 6, 5, 5, 6, 6, 3};
	//monsters' damage
	private static int[] damage = {3, 4, 5, 5, 7, 7, 20};
	//monsters's attack speed
	private static int[] attackSpeed = {50, 100, 150, 150, 100, 100, 200};
	//monsters' image
	private static Image[] images = {new ImageIcon("src/image/monster/msh0.png").getImage(),
									new ImageIcon("src/image/monster/msh1.png").getImage(),
									new ImageIcon("src/image/monster/msh2.png").getImage(),
									new ImageIcon("src/image/monster/msh3.png").getImage(),
									new ImageIcon("src/image/monster/msh4.png").getImage(),
									new ImageIcon("src/image/monster/msh5.png").getImage(),
									new ImageIcon("src/image/monster/mshM0.png").getImage()
									};
	//for the jump to start
	boolean isFirst = true;
	//for the jump
	int s0=0;
	//monsters' speed if not in screen
	private int speed = 5;
	//current monster image
	private Image image = new ImageIcon("src/image/monster/msh0.png").getImage();
	//width of the castle
	private int castleW = 155;
	//kind of the monster
	public int kind;
	public Monster(int ox, int oy, int k){
		this.ox = ox;
		this.oy = oy;
		if(k == -1){
			k = random.nextInt(6);
		}
		this.kind = k;
		update();
		hp = maxhp[k];
		image = images[k];
		sizeX = sizeXs[k];
		sizeY = sizeYs[k];
		speed = 2*speeds[k];
	}
	//update hp
	public static void update(){
		
		for(int i = 0; i < maxhp.length; i++){
			maxhp[i] = maxhp0[i] + (int)(BattlePanel.stage * maxhp0[i]/2.0);
		}
		
	}
	//monster body
	public void body(Graphics g, JPanel p){
		g.drawImage(image, ox - sizeX / 2, oy - sizeY, sizeX, sizeY, p);
		g.setColor(Color.BLACK);
		g.fillRect(ox - 20, oy - sizeY - 5, 40, 3);
		g.setColor(Color.RED);
		g.fillRect(ox - 20, oy - sizeY - 5, (int)(40 * (double)(hp) / (double)(maxhp[kind])), 3);
		//if(ox < 100)
			//go.stop();
	}
	//monster move
	public void move(int s){
		//move
		if(ox - sizeX / 2 > MainFrame.screemSize.getWidth()){
			ox = ox - 10;
		}
		else if(ox - sizeX / 2 > castleW){
			ox = ox - speed;
			if(ox - sizeX / 2 - castleW < speed){
				ox = castleW + sizeX / 2;
			}
		}
		else{
			attack(s);
		}
		s = s/10;
		//jump
		if(ox < MainFrame.screemSize.getWidth()){
			isInScreen = true;
			if(isFirst){
				s0 = s%2;
				isFirst = false;
			}
			if(s%2 == s0)
				oy = oy+5;
			else
				oy = oy-5;
		}
		
	}
	//monster attack
	public void attack(int s){
		if(s % attackSpeed[kind] == 0){
		if(Player.hp > 0){
			if(Player.hp - damage[kind] <= 0){
				Player.hp = 0;
			}
			else{
				Player.hp -= damage[kind];
			}
		}
		}
	}
	//boss generator
	public static void generateBoss(LinkedList<Monster> monsters, int stage){
		int vG = 40;//total 15 seats
		int hG = 60;
		int vO = 140;
		int hO = (int)(MainFrame.screemSize.getWidth()*1.3);
		Random random = new Random();
		monsters.add(new Monster(hO, vO + vG * random.nextInt(15), 6));
	}
	//monster from the boss
	public static void generateLmsh(LinkedList<Monster> monsters, int x, int y){
		int vG = 40;
		for(int i = 0; i < 5; i++){
			monsters.add(new Monster(x, y + vG * (i - 2), 0));
		}
	}
	//monster generator
	public static void generateMsh(LinkedList<Monster> monsters, int kind, int mode, int stage){
		int vG = 40;//total 15 seats
		int hG = 60;
		int vO = 140;
		int hO = (int)(MainFrame.screemSize.getWidth()*1.3);
		stage = stage / 10 + 1;
		Random random = new Random();
		if(mode == -1){
			mode = random.nextInt(3);
		}
		if(kind == -1){
			kind = random.nextInt(6);
		}
		switch(mode){
		case 0://random pop up
			stage = stage * 5;
			int[] randX = new int[stage];
			for(int i = 0; i < stage; i++){
				randX[i] = random.nextInt(stage/5);
				for(int j = i; j > 0; j--){
					if(randX[j] < randX[j-1]){
						int tmp = randX[j];
						randX[j] = randX[j-1];
						randX[j-1] = tmp;
					}
				}
			}
			for(int i = 0; i < stage; i++){
				monsters.add(new Monster(hO + hG * randX[i], vO + vG * random.nextInt(15), kind));
			}
			break;
		case 1://cross line
			for(int i = 0; i < stage; i++){
				for(int j = 0; j < (i % 2 != 0 ? 8 :7); j++){
					if(i % 2 != 0){
						monsters.add(new Monster(hO + hG * i, vO + 2 * vG * j, kind));
					}
					else{
						monsters.add(new Monster(hO + hG * i, vO + 2 * vG * j + 40, kind));
					}
				}
			}
			break;
		case 2://lines
			for(int i = 0; i < stage; i++){
				for(int j = 0; j < 8; j++){
					monsters.add(new Monster(hO + hG * i, vO + 2 * vG * j, kind));
				}
			}
			break;
		}
	}
}
