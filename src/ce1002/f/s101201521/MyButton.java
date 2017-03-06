package ce1002.f.s101201521;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class MyButton extends JButton{
	//mode for button
	private int mode = 0;
	//font
	private Font font = new Font("Blackadder ITC", Font.BOLD, 30);
	//current image
	private Image image;
	//arrow to right image
	private Image rightImg = new ImageIcon("src/image/button/rightBt.png").getImage();
	//wood image
	private Image middleImg = new ImageIcon("src/image/button/middleBt.png").getImage();
	//arrow to left image
	private Image leftImg = new ImageIcon("src/image/button/leftBt.png").getImage();	
	//bookmarker image
	private Image changeImg = new ImageIcon("src/image/button/changeBt.jpg").getImage();
	//upgrade image
	private Image upgradeImg = new ImageIcon("src/image/button/upgradeBt.png").getImage();
	//text for the button
	public String text;
	public MyButton(String text, int mode){
		this.text = text;
		this.mode = mode;
		switch(mode){
		case 0:
			image = new ImageIcon("src/image/button/" + text + ".png").getImage();	
			break;
		case 1:
			image = leftImg;
			break;
		case 2:
			image = rightImg;
			break;
		case 3:
			image = changeImg;
			break;
		case 4:
			image = upgradeImg;
			break;
		case 5:
			image = new ImageIcon("src/image/weapon/" + text + ".png").getImage();
			break;
		case 6:
			image = middleImg;
			break;
		}
		setContentAreaFilled(false);
		setBorderPainted(false);
	}
	@Override
	public void setText(String text){
		this.text = text;
	}
	@Override
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		switch(mode){
		case 0:
			g.drawImage(image, 0,0,170,50,this);
			int point = 0;
			switch(text){
			case "Strength":
				point = Skill.pts[0];
				break;
			case "Agility":
				point = Skill.pts[1];
				break;
			case "Repel":
				point = Skill.pts[2];
				break;
			case "Poison":
				point = Skill.pts[3];
				break;
			case "Fatal":
				point = Skill.pts[4];
				break;
			case "Amount":
				point = Skill.pts[5];
				break;
			}
			g.drawString(String.valueOf(point), 60, 40);
			if(getModel().isRollover()){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.BLACK);
			}
			g.drawString(text, 60, 20);
			break;
		case 1:
			g.drawImage(image, 0,0,200,100,this);
			g.setFont(font);
			if(getModel().isRollover()){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.drawString(text, 80, 55);
			break;
		case 2:
			g.drawImage(image, 0,0,200,100,this);
			g.setFont(font);
			if(getModel().isRollover()){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.drawString(text, 40, 55);
			break;
		case 3:
			g.drawImage(image, 0, 0, 250, 100, this);
			g.setFont(font);
			if(getModel().isRollover()){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.WHITE);
			}
			g.drawString(text, 50, 50);
			break;
		case 4:
			g.drawImage(image, 0, 0, 140, 40, this);
			g.setFont(new Font("Elephant", Font.PLAIN, 18));
			if(getModel().isRollover()){
				g.setColor(Color.RED);
			}
			else{
				g.setColor(Color.WHITE);
			}
			FontMetrics fm = g.getFontMetrics();
			g.drawString(text, 70 - fm.stringWidth(text)/2, 27);
			break;
		case 5:
			g.drawImage(image, 0, 0, 50, 50, this);
			break;
		case 6:
			g.drawImage(image, 0,0,200,100,this);
			g.setFont(font);
			if(getModel().isRollover()){
				g.setColor(Color.YELLOW);
			}
			else{
				g.setColor(Color.WHITE);
			}
			FontMetrics fm2 = g.getFontMetrics();
			g.drawString(text, 100 - fm2.stringWidth(text)/2, 55);
			break;
		}
	}
}