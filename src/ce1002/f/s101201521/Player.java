package ce1002.f.s101201521;
import java.awt.*;

import javax.swing.*;
public class Player extends JPanel{
	//player's money
	public static int money = 10000;
	//max hp
	public static double maxHp = 100;
	//max mp
	public static double maxMp = 100;
	//current hp
	public static int hp = 100;
	//current mp
	public int mp = 100;
	//the weapon equiped
	public static int weaponEquiped = 0;
	//status background image
	private static Image statusBg = new ImageIcon("src/image/status/status.png").getImage();
	//hp image
	private static Image hpImg = new ImageIcon("src/image/status/hp.png").getImage();
	//mp image
	private static Image mpImg = new ImageIcon("src/image/status/mp.png").getImage();
	//money image
	private static Image moneyImg = new ImageIcon("src/image/money.png").getImage();
	private Font font = new Font("Eras Bold ITC", Font.PLAIN, 12);
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawImage(statusBg, 0,0, 1200, 50, this);
		g.drawImage(hpImg, 715, 6, (int)(482 * (hp / maxHp)), 18, this);
		g.drawImage(moneyImg, 205, 8, 42, 40, this);
		g.drawString("STAGE", 20, 30);
		g.drawString(String.valueOf(BattlePanel.stage), 120, 30);
		g.drawString(String.valueOf(money), 280, 30);
		g.drawString("[ " + hp + " / " + (int)(maxHp) + " ]", 1000, 20);
		g.drawImage(mpImg, 715, 28, (int)(482 * (mp / maxMp)), 18, this);
		g.drawString("[ " + mp + " / " + (int)(maxMp) + " ]", 1000, 42);
		g.drawImage(new ImageIcon("src/image/weapon/" + Weapon.name[weaponEquiped] + ".png").getImage(),
							400, 5, 40, 40, this);
	}
}
