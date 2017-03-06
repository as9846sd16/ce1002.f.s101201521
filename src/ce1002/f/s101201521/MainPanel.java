package ce1002.f.s101201521;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainPanel extends JPanel {
	public MyButton BackBt = new MyButton("Back", 1);
	public MyButton goBt = new MyButton("Go", 2);
	public MyButton HardBt = new MyButton("Too Hard", 6);
	private BarPanel barPanel = new BarPanel();
	private SkillPanel skillPanel = new SkillPanel();
	private MagicPanel magicPanel = new MagicPanel();
	private WeaponPanel weaponPanel = new WeaponPanel();
	private JPanel curPanel = skillPanel;
	private MyButton skillBt = new MyButton("Skill", 3);
	private MyButton magicBt = new MyButton("Magic", 3);
	private MyButton weaponBt = new MyButton("Weapon", 3);
	private Image pageBg = new ImageIcon("src/image/pageBg.png").getImage();

	public MainPanel() {
		setLayout(new BorderLayout());
		setBounds(0, 0, (int) (MainFrame.screemSize.getWidth()),
				(int) (MainFrame.screemSize.getHeight()));

		curPanel.add(skillBt);
		curPanel.add(magicBt);
		curPanel.add(weaponBt);
		skillBt.setBounds(0, 50, 250, 100);
		magicBt.setBounds(0, 200, 250, 100);
		weaponBt.setBounds(0, 350, 250, 100);
		skillBt.addActionListener(new BtListener());
		magicBt.addActionListener(new BtListener());
		weaponBt.addActionListener(new BtListener());

		add(barPanel, BorderLayout.SOUTH);
		add(curPanel, BorderLayout.CENTER);

	}

	// listener for buttons controlling things in mainPanel
	class BtListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == skillBt) {
				remove(curPanel);
				add(skillPanel, BorderLayout.CENTER);
				curPanel = skillPanel;
			} else if (e.getSource() == magicBt) {
				remove(curPanel);
				add(magicPanel, BorderLayout.CENTER);
				curPanel = magicPanel;
			} else if (e.getSource() == weaponBt) {
				remove(curPanel);
				add(weaponPanel, BorderLayout.CENTER);
				curPanel = weaponPanel;
			}
			curPanel.add(skillBt);
			curPanel.add(magicBt);
			curPanel.add(weaponBt);
			validate();
			repaint();
		}

	}

	class BarPanel extends JPanel {
		public Player player = new Player();

		public BarPanel() {

			setLayout(null);
			setPreferredSize(new Dimension(100, 200));
			// button to start game
			add(goBt);
			goBt.setBounds((int)(MainFrame.screemSize.getWidth() - 200), 30,
					200, 100);
			// button to go back
			add(BackBt);
			BackBt.setBounds(0, 30, 200, 100);
			add(HardBt);
			HardBt.setBounds((int)(MainFrame.screemSize.getWidth()/2.0 - 100), 30, 200, 100);
			add(player);
			player.setBounds(
					(int) (MainFrame.screemSize.getWidth() / 2.0) - 600, 130,
					1200, 50);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pageBg, 0,
					200 - (int) (MainFrame.screemSize.getHeight()),
					(int) (MainFrame.screemSize.getWidth()),
					(int) (MainFrame.screemSize.getHeight()), this);
		}
	}

	// panel for skill
	class SkillPanel extends JPanel {
		private ImageIcon image = new ImageIcon("src/image/move1.png");
		private JLabel lb = new JLabel(image);
		private DialogPanel dialogPanel = new DialogPanel(0);
		public SkillBtListener skillBtListener = new SkillBtListener();

		public SkillPanel() {
			setLayout(null);

			add(Skill.strengthBt);
			add(Skill.agilityBt);
			add(Skill.repelBt);
			add(Skill.fatalBt);
			add(Skill.poisonBt);
			add(Skill.amountBt);
			add(dialogPanel);

			dialogPanel.setBounds(300, 370, 900, 400);
			Skill.strengthBt.setBounds(300, 100, 170, 50);
			Skill.agilityBt.setBounds(300, 200, 170, 50);
			Skill.repelBt.setBounds(650, 50, 170, 50);
			Skill.fatalBt.setBounds(650, 250, 170, 50);
			Skill.poisonBt.setBounds(650, 150, 170, 50);
			Skill.amountBt.setBounds(1000, 150, 170, 50);
			Skill.strengthBt.addActionListener(skillBtListener);
			Skill.agilityBt.addActionListener(skillBtListener);
			Skill.repelBt.addActionListener(skillBtListener);
			Skill.fatalBt.addActionListener(skillBtListener);
			Skill.poisonBt.addActionListener(skillBtListener);
			Skill.amountBt.addActionListener(skillBtListener);
			dialogPanel.upgradeBt.addActionListener(skillBtListener);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pageBg, 0, 0, (int) (MainFrame.screemSize.getWidth()),
					(int) (MainFrame.screemSize.getHeight()), this);
		}

		class SkillBtListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Skill.strengthBt) {
					dialogPanel.curBt = Skill.strengthBt;
					dialogPanel.curEffect = Skill.strength;
					dialogPanel.curCost = Skill.cost[0];
					dialogPanel.curIntroduction = Skill.introduction[0];
					if (Skill.pts[0] == Skill.ptMax[0]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == Skill.agilityBt) {
					dialogPanel.curBt = Skill.agilityBt;
					dialogPanel.curEffect = Skill.agility;
					dialogPanel.curCost = Skill.cost[1];
					dialogPanel.curIntroduction = Skill.introduction[1];
					if (Skill.pts[1] == Skill.ptMax[1]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == Skill.repelBt) {
					dialogPanel.curBt = Skill.repelBt;
					dialogPanel.curEffect = Skill.repel;
					dialogPanel.curCost = Skill.cost[2];
					dialogPanel.curIntroduction = Skill.introduction[2];
					if (Skill.pts[2] == Skill.ptMax[2]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == Skill.poisonBt) {
					dialogPanel.curBt = Skill.poisonBt;
					dialogPanel.curEffect = Skill.poison;
					dialogPanel.curCost = Skill.cost[3];
					dialogPanel.curIntroduction = Skill.introduction[3];
					if (Skill.pts[3] == Skill.ptMax[3]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == Skill.fatalBt) {
					dialogPanel.curBt = Skill.fatalBt;
					dialogPanel.curEffect = Skill.fatal;
					dialogPanel.curCost = Skill.cost[4];
					dialogPanel.curIntroduction = Skill.introduction[4];
					if (Skill.pts[4] == Skill.ptMax[4]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == Skill.amountBt) {
					dialogPanel.curBt = Skill.amountBt;
					dialogPanel.curEffect = Skill.amount;
					dialogPanel.curPercent = Skill.strengthPercent;
					dialogPanel.curCost = Skill.cost[5];
					dialogPanel.curIntroduction = Skill.introduction[5];
					if (Skill.pts[5] == Skill.ptMax[5]) {
						dialogPanel.upgradeBt.setText("Max");
					} else {
						dialogPanel.upgradeBt.setText("Upgrade");
					}
				} else if (e.getSource() == dialogPanel.upgradeBt) {
					switch (dialogPanel.curBt.text) {
					case "Strength":
						if (Skill.pts[0] < Skill.ptMax[0]
								&& Player.money >= Skill.cost[0]) {
							Skill.pts[0]++;
							Player.money -= Skill.cost[0];
							Skill.update();
							dialogPanel.curEffect = Skill.strength;
							if (Skill.pts[0] == Skill.ptMax[0]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[0];
						break;
					case "Agility":
						if (Skill.pts[1] < Skill.ptMax[1]
								&& Player.money >= Skill.cost[1]) {
							Skill.pts[1]++;
							Player.money -= Skill.cost[1];
							Skill.update();
							dialogPanel.curEffect = Skill.agility;
							if (Skill.pts[1] == Skill.ptMax[1]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[1];
						break;
					case "Repel":
						if (Skill.pts[2] < Skill.ptMax[2]
								&& Player.money >= Skill.cost[2]) {
							Skill.pts[2]++;
							Player.money -= Skill.cost[2];
							Skill.update();
							dialogPanel.curEffect = Skill.repel;
							if (Skill.pts[2] == Skill.ptMax[2]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[2];
						break;
					case "Poison":
						if (Skill.pts[3] < Skill.ptMax[3]
								&& Player.money >= Skill.cost[3]) {
							Skill.pts[3]++;
							Player.money -= Skill.cost[3];
							Skill.update();
							dialogPanel.curEffect = Skill.poison;
							if (Skill.pts[3] == Skill.ptMax[3]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[3];
						break;
					case "Fatal":
						if (Skill.pts[4] < Skill.ptMax[4]
								&& Player.money >= Skill.cost[4]) {
							Skill.pts[4]++;
							Player.money -= Skill.cost[4];
							Skill.update();
							dialogPanel.curEffect = Skill.fatal;
							if (Skill.pts[4] == Skill.ptMax[4]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[4];
						break;
					case "Amount":
						if (Skill.pts[5] < Skill.ptMax[5]
								&& Player.money >= Skill.cost[5]) {
							Skill.pts[5]++;
							Player.money -= Skill.cost[5];
							Skill.update();
							dialogPanel.curEffect = Skill.amount;
							if (Skill.pts[5] == Skill.ptMax[5]) {
								dialogPanel.upgradeBt.setText("Max");
							}
						}
						dialogPanel.curCost = Skill.cost[5];
						break;
					}
				}
				Skill.update();
				validate();
				repaint();
				barPanel.player.validate();
				barPanel.player.repaint();
			}
		}
	}

	// panel for magic
	class MagicPanel extends JPanel {
		private Font font = new Font("Blackadder ITC", Font.BOLD, 100);
		public MagicPanel() {
			setLayout(null);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pageBg, 0, 0, (int) (MainFrame.screemSize.getWidth()),
					(int) (MainFrame.screemSize.getHeight()), this);
			
			g.setColor(Color.RED);
			g.setFont(font);
			g.drawString("Coming soon", (int) (MainFrame.screemSize.getWidth()/3.0), (int)(MainFrame.screemSize.getHeight()/2.0));
		}
	}

	// panel for weapon
	class WeaponPanel extends JPanel {
		private DialogPanel dialogPanel = new DialogPanel(1);
		private WeaponBtListener weaponBtListener = new WeaponBtListener();

		public WeaponPanel() {
			setLayout(null);
			add(dialogPanel);
			dialogPanel.setBounds(300, 370, 900, 400);
			for (int i = 0; i < Weapon.weaponBt.length; i++) {
				add(Weapon.weaponBt[i]);
				Weapon.weaponBt[i].setBounds(400 + 150 * i, 150, 50, 50);
				Weapon.weaponBt[i].addActionListener(weaponBtListener);
			}
			dialogPanel.upgradeBt.addActionListener(weaponBtListener);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(pageBg, 0, 0, (int) (MainFrame.screemSize.getWidth()),
					(int) (MainFrame.screemSize.getHeight()), this);
		}

		class WeaponBtListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < Weapon.weaponBt.length; i++) {
					if (e.getSource() == Weapon.weaponBt[i]) {
						dialogPanel.curBt = Weapon.weaponBt[i];
						dialogPanel.curIsOwn = Weapon.isOwn[i];
						dialogPanel.curCost = Weapon.cost[i];
						dialogPanel.curIntroduction = Weapon.introduction[i];
						if (!Weapon.isOwn[i]) {
							dialogPanel.upgradeBt.setText("Buy");
						} else if (Weapon.isOwn[i]) {
							if (i == Player.weaponEquiped) {
								dialogPanel.upgradeBt.setText("Equiped");
							} else {
								dialogPanel.upgradeBt.setText("Equip");
							}
						}

					}
				}
				if (e.getSource() == dialogPanel.upgradeBt) {
					int i = 0;
					switch (dialogPanel.curBt.text){
					case "Star":
						i = 0;
						break;
					case "Snow":
						i = 1;
						break;
					case "Thunder":
						i = 2;
						break;
					case "Sun":
						i = 3;
						break;
					case "Magic":
						i = 4;
						break;
					}
					if (!Weapon.isOwn[i] && Player.money >= Weapon.cost[i]) {
						Player.money -= Weapon.cost[i];
						Weapon.isOwn[i] = true;
						dialogPanel.curIsOwn = true;
						dialogPanel.upgradeBt.setText("Equip");
					} 
					else if (Weapon.isOwn[i]) {
						if (Player.weaponEquiped == i) {
							dialogPanel.upgradeBt.setText("Equiped");
						} else if (Player.weaponEquiped != i) {
							Player.weaponEquiped = i;
							dialogPanel.upgradeBt.setText("Equiped");
						}
					}
				}
				Skill.update();
				validate();
				repaint();
				barPanel.player.validate();
				barPanel.player.repaint();
			}
		}
	}

	class DialogPanel extends JPanel {
		private Image dialogBg = new ImageIcon("src/image/dialogBg.png")
				.getImage();
		private Image moneyImg = new ImageIcon("src/image/money.png")
				.getImage();
		public MyButton curBt;
		public int curEffect;
		private int curPercent;
		private int curCost;
		private boolean curIsOwn;
		public String curIntroduction;
		private Font font = new Font("Elephant", Font.PLAIN, 18);
		private MyButton upgradeBt;
		private int mode;

		public DialogPanel(int mode) {
			setOpaque(false);
			this.mode = mode;
			setLayout(null);
			switch (mode) {
			case 0:
				curBt = Skill.strengthBt;
				curEffect = Skill.strength;
				curCost = Skill.cost[0];
				curIntroduction = Skill.introduction[0];
				upgradeBt = new MyButton("Upgrade", 4);
				break;
			case 1:
				curIsOwn = Weapon.isOwn[0];
				curBt = Weapon.weaponBt[0];
				curCost = Weapon.cost[0];
				curIntroduction = Weapon.introduction[0];
				if (Player.weaponEquiped == 0) {
					upgradeBt = new MyButton("Equiped", 4);
				} else {
					upgradeBt = new MyButton("Equipe", 4);
				}
				break;
			}
			add(upgradeBt);
			upgradeBt.setBounds(720, 100, 150, 40);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(dialogBg, 0, 0, 900, 180, this);
			g.drawImage(moneyImg, 710, 50, 42, 40, this);
			g.setFont(font);
			switch (mode) {
			case 0:
				g.setColor(Color.RED);
				g.drawString(curBt.text, 50, 50);
				g.setColor(Color.WHITE);
				g.drawString(curIntroduction, 60, 90);
				if (curBt.text.equals("Amount")) {
					g.drawString(
							"Current: " + String.valueOf(curEffect)
									+ " weapons, "
									+ String.valueOf(Skill.strengthPercent)
									+ " % per weapon", 60, 130);
				} else {
					g.drawString("Current: " + String.valueOf(curEffect), 60,
							130);
				}
				g.drawString(String.valueOf(curCost), 760, 75);
				break;
			case 1:
				g.setColor(Color.RED);
				g.drawString(curBt.text, 50, 50);
				g.setColor(Color.WHITE);
				g.drawString(curIntroduction, 60, 90);
				g.drawString(curIsOwn ? "Owned" : (String.valueOf(curCost)),
						760, 75);
				break;
			}
		}
	}
}
