package ce1002.f.s101201521;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class MainFrame extends JFrame{

	public static final Dimension screemSize = Toolkit.getDefaultToolkit().getScreenSize();
	//panel for the battle
	private BattlePanel battlePanel = new BattlePanel();
	//panel for welcome
	private WelcomePanel welcomePanel = new WelcomePanel();
	//panel for the home
	private MainPanel mainPanel = new MainPanel();
	//panel for pause
	private PausePanel pausePanel = new PausePanel();
	//panel for dead
	private DeadPanel  deadPanel = new DeadPanel();
	//panel for win
	private WinPanel winPanel = new WinPanel();
	//listener for buttons
	private BtListener btListener = new BtListener();
	//file for save
	private File file = new File("src/saves/save.txt");
	public MainFrame(){
		setLayout(null);
		battlePanel.setFrame(this);
		//add(welcomePanel);
		add(welcomePanel);
		welcomePanel.leaveBt.addActionListener(btListener);
		welcomePanel.startBt.addActionListener(btListener);
		mainPanel.goBt.addActionListener(btListener);
		mainPanel.BackBt.addActionListener(btListener);
		mainPanel.HardBt.addActionListener(btListener);
		battlePanel.pauseBt.addActionListener(btListener);
		pausePanel.contiBt.addActionListener(btListener);
		pausePanel.homeBt.addActionListener(btListener);
		pausePanel.restartBt.addActionListener(btListener);
		deadPanel.yesBt.addActionListener(btListener);
		winPanel.yesBt.addActionListener(btListener);
		setLocation(0,0);
		setSize((int)(screemSize.getWidth()), (int)(screemSize.getHeight()));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);
		if(file.exists()){
		try {
			load();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}
	//dead
	public void dead(){
		add(deadPanel, 0);
		battlePanel.shooting.stop();
		battlePanel.pauseBt.setEnabled(false);
		validate();
		repaint();
	}
	//win
	public void win(){
		add(winPanel, 0);
		battlePanel.shooting.stop();
		battlePanel.pauseBt.setEnabled(false);
		WinPanel.moneyGain = 3000 + (BattlePanel.stage - 1) * 100;
		Player.money = Player.money + WinPanel.moneyGain;
		validate();
		repaint();
	}
	//load the save
	private void load() throws FileNotFoundException{
		Scanner input = new Scanner(file);
		BattlePanel.stage = input.nextInt();
		Player.money = input.nextInt();
		Player.weaponEquiped = input.nextInt();
		for(int i = 0; i < Skill.pts.length; i++){
			Skill.pts[i] = input.nextInt();
		}
		for(int i = 0; i < Weapon.isOwn.length; i++){
			Weapon.isOwn[i] = input.nextBoolean();
		}
		Skill.update();
		input.close();
	}
	//save
	private void save() throws FileNotFoundException{
		PrintWriter output = new PrintWriter(file);
		output.println(BattlePanel.stage);
		output.print(Player.money + " ");
		output.println(Player.weaponEquiped);
		for(int i = 0; i < Skill.pts.length; i++){
			output.print(Skill.pts[i] + " ");
		}
		output.println();
		for(int i = 0; i < Weapon.isOwn.length; i++){
			output.print(Weapon.isOwn[i] + " ");
		}
		output.close();
	}
	public static void main(String[] args){
		MainFrame frame = new MainFrame();
		Skill skill = new Skill();
		Player player = new Player();
	}
	//listener for button controlling things in mainFrame 
	public class BtListener implements ActionListener{
		BtListener(){};
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == welcomePanel.leaveBt){
				try {
					save();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
			else if(e.getSource() == welcomePanel.startBt){
				remove(welcomePanel);
				add(mainPanel);
			}
			else if(e.getSource() == mainPanel.goBt){
				remove(mainPanel);
				add(battlePanel);
				battlePanel.monsterKilled = 0;
				battlePanel.shooting.start();
			}
			else if(e.getSource() == mainPanel.BackBt){
				remove(mainPanel);
				add(welcomePanel);
			}
			else if(e.getSource() == mainPanel.HardBt){
				if(BattlePanel.stage > 0){
					BattlePanel.stage--;
					mainPanel.HardBt.setEnabled(false);
				}
			}
			else if(e.getSource() == battlePanel.pauseBt){
				add(pausePanel, 0);
				battlePanel.shooting.stop();
			}
			else if(e.getSource() == pausePanel.contiBt){
				remove(pausePanel);
				battlePanel.shooting.start();
			}
			else if(e.getSource() == pausePanel.homeBt){
				battlePanel.shooting.stop();
				battlePanel.monsters.clear();
				battlePanel.weapons.clear();
				remove(pausePanel);
				remove(battlePanel);
				Player.hp = (int)(Player.maxHp);
				add(mainPanel);
				System.gc();
			}
			else if(e.getSource() == pausePanel.restartBt){
				remove(pausePanel);
				battlePanel.shooting.stop();
				battlePanel.monsters.clear();
				battlePanel.weapons.clear();
				Player.hp = (int)(Player.maxHp);
				System.gc();
				battlePanel.shooting.start();
			}
			else if(e.getSource() == deadPanel.yesBt){
				Player.hp = (int)(Player.maxHp);
				battlePanel.pauseBt.setEnabled(true);
				battlePanel.wave = 0;
				battlePanel.isSend = false;
				remove(deadPanel);
				remove(battlePanel);
				battlePanel.monsters.clear();
				battlePanel.weapons.clear();
				System.gc();
				add(mainPanel);
			}
			else if(e.getSource() == winPanel.yesBt){
				BattlePanel.stage++;
				Player.hp = (int)(Player.maxHp);
				battlePanel.pauseBt.setEnabled(true);
				battlePanel.wave = 0;
				battlePanel.isSend = false;
				remove(winPanel);
				remove(battlePanel);
				battlePanel.monsters.clear();
				battlePanel.weapons.clear();
				System.gc();
				add(mainPanel);
				mainPanel.HardBt.setEnabled(true);
			}
			revalidate();
			repaint();
		}
	}
}
