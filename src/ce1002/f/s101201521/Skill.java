package ce1002.f.s101201521;
import java.awt.*;

import javax.swing.*;
public class Skill {
	public static int[] ptMax = {10000, 35, 20, 20, 40, 24};
	public static int[] pts = {1, 1, 0, 0, 0, 1};
	//max 99
	public static int strength = 1;
	//max 45
	public static int agility = 1;
	//
	public static int repel = 1;
	public static int poison = 1;
	public static int fatal = 1;
	public static int amount = 1;
	public static int strengthPercent = 100;
	//max 99
	
	public static String[] introduction = {"Increase your weapon's base damage.",
											"Increase your weapon's firing rate.",
											"Increase the distance to repel monsters.",
											"",
											"Your weapon may cause double damage",
											"You can throw multiple weapons"};
	public static int[] cost = {1000,1000,1000,1000,1000,1000};
	public static MyButton agilityBt = new MyButton("Agility", 0);
	public static MyButton strengthBt = new MyButton("Strength", 0);
	public static MyButton repelBt = new MyButton("Repel", 0);
	public static MyButton poisonBt = new MyButton("Poison", 0);
	public static MyButton fatalBt = new MyButton("Fatal", 0);
	public static MyButton amountBt = new MyButton("Amount", 0);
	public Skill(){
		update();
	}
	public static void update(){
		strengthPercent = (25 + 25 * ((pts[5] + Weapon.effect[2][Player.weaponEquiped]) == 1 
							? 3 : ((pts[5] + Weapon.effect[2][Player.weaponEquiped]) + 1)%3 + 1));
		strength = (int)(4 * (pts[0] + Weapon.effect[0][Player.weaponEquiped]) * (strengthPercent / 100.0) );
		agility = pts[1] + Weapon.effect[1][Player.weaponEquiped];
		repel = 5 * pts[2];
		poison = pts[3];
		fatal = (fatal < 75 ? 5 * pts[4] : 75 + (pts[4] - 15)) ;
		amount = ((pts[5] + Weapon.effect[2][Player.weaponEquiped]) + 1) / 3 + 1;
		
		cost[0] = 1000 + (pts[0] - 1) * 500;
		cost[1] = 2000 + (pts[1] - 1) * 500;
		cost[2] = 10000 + (pts[2] - 1) * 5000;
		cost[3] = 0;
		cost[4] = 10000 + (pts[4] - 1) * 5000;
		cost[5] = 50000 + (pts[5] - 1) * 10000;
	}
	
}
