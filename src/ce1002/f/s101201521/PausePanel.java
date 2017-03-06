package ce1002.f.s101201521;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
//panel for pause
public class PausePanel extends JPanel{
	//button for restart
	public MyButton restartBt = new MyButton("Restart", 1);
	//button for continue
	public MyButton contiBt = new MyButton("Continue", 6);
	//button to go to home page
	public MyButton homeBt = new MyButton("Home", 2);
	public PausePanel(){
		setBounds((int)(MainFrame.screemSize.getWidth()/4),(int)(MainFrame.screemSize.getHeight()/4),
				(int)(MainFrame.screemSize.getWidth()/2), (int)(MainFrame.screemSize.getHeight()/6));
		setOpaque(false);
		setLayout(null);
		add(restartBt);
		restartBt.setBounds(0,(int)(MainFrame.screemSize.getHeight()/12) - 50,200,100);
		add(contiBt);
		contiBt.setBounds((int)(MainFrame.screemSize.getWidth()/4) - 100,(int)(MainFrame.screemSize.getHeight()/12) - 50,200,100);
		add(homeBt);
		homeBt.setBounds((int)(MainFrame.screemSize.getWidth()/2) - 200,(int)(MainFrame.screemSize.getHeight()/12) - 50,200,100);
	}
}
