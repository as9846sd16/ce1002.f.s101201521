package ce1002.f.s101201521;
import javax.swing.*;

import java.awt.*;
public class WinPanel extends JPanel{
	private Image winBg = new ImageIcon("src/image/winBg.png").getImage();
	private Font font = new Font("Elephant", Font.PLAIN, 18);
	public JButton yesBt = new JButton(new ImageIcon("src/image/yesBt.png"));
	public static int moneyGain;
	public WinPanel(){
		
		setBounds((int)(MainFrame.screemSize.getWidth()/2) - 275,(int)(MainFrame.screemSize.getHeight()/3) - 145,
				550, 290);
		setLayout(null);
		add(yesBt);
		yesBt.setFocusPainted(false);
		yesBt.setBorderPainted(false);
		yesBt.setBounds(250,260,50,20);
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(winBg, 0,0,550,290, this);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString(String.valueOf(BattlePanel.monsterKilled), 400, 100);
		g.drawString(String.valueOf(moneyGain), 400, 250);
		
	}
}
