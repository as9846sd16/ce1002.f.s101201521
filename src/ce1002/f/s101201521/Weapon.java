package ce1002.f.s101201521;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.LinkedList;
public class Weapon {
	private static int[] basicStrengths = {5, 10, 15, 20, 25};
	public static String[] name = {"star", "snow", "thunder", "sun", "magic"};
	//strength, agility, amount// weapons
	public static int effect[][] = {{0, 10, 15, 25, 50},
									{0, 0, 5, 0, 10},
									{0, 0, 0, 0, 4}};
	private static Image[][] image = new Image[name.length][3];
	public static MyButton[] weaponBt = {new MyButton("Star", 5),
										new MyButton("Snow", 5),
										new MyButton("Thunder", 5),
										new MyButton("Sun", 5),
										new MyButton("Magic", 5)}; 
	public static int[] cost = {0, 100, 1000, 10000, 100000, 1000000};
	public static String[] introduction = {"The weapon for the beginners.",
											"Snow ball can increase the damage a little.",
											"Thunder can increase the throwing rate and damage.",
											"Sun can much more increase the damage.",
											"Magically increase the throwing rate and damage a lot."};
	public static boolean[] isOwn = {true, false, false, false, false};
	private static boolean isFirst = true;
	private static Image curImage;
	public static int weaponMode = 2;
	private double ox = 100 - 25;
	private double oy = MainFrame.screemSize.getHeight()/2.0 - 12;
	public  double x=ox;
	public  double y=oy;
	public int distance=0;
	public boolean isAlive = true;
	private int speed = 10;
	private double direct;
	public Weapon(int mx, int my, double angle){
		weaponMode =  Player.weaponEquiped;
		if(isFirst){
			for(int i = 0; i < name.length; i++){
				for(int j = 0; j < 3; j++){
					image[i][j] = new ImageIcon("src/image/weapon/" + name[i] + "weapon" + j + ".png").getImage();
				}
			}
			isFirst = false;
		}
		curImage = image[weaponMode][0];
		direct = Math.atan((my - oy)/(mx - ox));
		if(mx <= 100 && my <= oy){
			direct = Math.PI*3/2;
		}
		else if(mx <= 100 && my >= oy){
			direct = Math.PI/2;
		}
		direct = direct - angle;
	}
	public void body(Graphics g, JPanel p){
		g.drawImage(curImage, (int)(x)-15,(int)(y)-15
				,(weaponMode == 2 ? 50 : (weaponMode == name.length - 1 ? 30 : 25))
				, (weaponMode == name.length - 1 ? 30 :25),p);
		//check whether the weapon is alive
		if(x > MainFrame.screemSize.getWidth() || y < 0 || y > MainFrame.screemSize.getHeight()){
			isAlive = false;
		}
	}
	public void shoot(int s){
		distance = distance + speed;
		x = ox + (double)(distance)*Math.cos(direct);
		y = oy + (double)(distance)*Math.sin(direct);
		curImage = image[weaponMode][s%3];
	}
	public static void generateWeapon(LinkedList<Weapon> weapons, int mx, int my, int amount){
		double angle = Math.PI/72.0;
		if(amount%2 == 0){
			for(int i = amount/2; i>= -1 * amount/2; i--){
				if(i != 0){
					weapons.add(new Weapon(mx, my, i * ((i == 1 | i == -1) ? angle/2 : angle)
													- (i/(Math.abs(i))) * ((i == 1 | i == -1) ? 0 : angle/2)));
				}
			}
		}
		else{
			for(int i = amount/2; i >= -1 * amount/2; i--){
				weapons.add(new Weapon(mx, my, i * angle));
			}
		}
		
	}
}
