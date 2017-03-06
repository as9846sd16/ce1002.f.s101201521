package ce1002.f.s101201521;
import javax.swing.*;
import java.awt.*;
//dead panel
public class DeadPanel extends JPanel{
	//image for the background
	private Image deadBg = new ImageIcon("src/image/deadBg.png").getImage();
	//button to confirm
	public JButton yesBt = new JButton(new ImageIcon("src/image/yesBt.png"));
	public DeadPanel(){
		setBounds((int)(MainFrame.screemSize.getWidth()/2) - 210,(int)(MainFrame.screemSize.getHeight()/3) - 105,
				420, 210);
		setLayout(null);
		add(yesBt);
		yesBt.setFocusPainted(false);
		yesBt.setBorderPainted(false);
		yesBt.setBounds(185,175,50,20);
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(deadBg, 0,0,420,210, this);
		
	}
}
