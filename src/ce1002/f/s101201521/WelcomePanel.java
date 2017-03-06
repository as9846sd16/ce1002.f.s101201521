package ce1002.f.s101201521;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class WelcomePanel extends JPanel{
	public MyButton startBt = new MyButton("Start",2);
	public MyButton leaveBt= new MyButton("Leave",1);
	public WelcomePanel(){
		setLayout(null);
		setBounds(0, 0, (int)(MainFrame.screemSize.getWidth()), (int)(MainFrame.screemSize.getHeight()));
		//button to start the game
		add(startBt);
		startBt.setBounds((int)(MainFrame.screemSize.getWidth()*9/12.0 - 100) ,
						(int)(MainFrame.screemSize.getHeight()*6.1/7.0),
						200,100);
		//button for exit
		add(leaveBt);
		leaveBt.setBounds((int)(MainFrame.screemSize.getWidth()*3/12.0 -100) ,
						(int)(MainFrame.screemSize.getHeight()*6.1/7.0),
						200,100);
	}
	
	@Override
	protected void paintComponent(Graphics g){
		Image image = new ImageIcon("src/image/welcomeBg.png").getImage();
		g.drawImage(image, 0 ,0 , (int)(MainFrame.screemSize.getWidth()), 
				(int)(MainFrame.screemSize.getHeight()), this);
	}
}
