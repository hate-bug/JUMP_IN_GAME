import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Jump_IN_View extends JFrame {
	private JMenuBar menubar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game"); 
	private JMenuItem startMenu = new JMenuItem ("Start Game");
	private JMenu levelMenu = new JMenu("Level");
	private JMenuItem chooseLevel1Menu = new JMenuItem("Choose Level");
	private JButton undoButton = new JButton ("Undo");
	private JButton redoButton = new JButton("Redo");
	private JButton hintButton = new JButton("Hint");
	private JLabel gameInfo = new JLabel("Please choose level", SwingConstants.CENTER);
	private JLabel hintInfo = new JLabel("Hint section", SwingConstants.CENTER);
	private GridLayout BoardLayout = new GridLayout(5, 5);
	private JButton grid[][];
	private JPanel upPanel, panel;
	private static final String dot = "\u2022";
	
	public Jump_IN_View () {
		
		this.menubar.add(this.gameMenu);
		this.gameMenu.add(startMenu);
		this.menubar.add(levelMenu);
		this.levelMenu.add(chooseLevel1Menu);
		super.setJMenuBar(menubar);
		
		upPanel = new JPanel(); 
		upPanel.add(gameInfo);
		upPanel.add(undoButton);
		upPanel.add(redoButton);
		upPanel.add(hintButton);
		upPanel.add(hintInfo);
		hintInfo.setSize(100, 200);
		super.add(upPanel, BorderLayout.NORTH);
		
		panel = new JPanel();
		panel.setLayout(BoardLayout);
		
		//Initialize all buttons to dot first
		this.grid = new JButton [5][5];
		
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				JButton button = new JButton(dot);
				this.panel.add(button);
				this.grid[x][y] = button;
			}
		}

		super.add(panel, BorderLayout.CENTER);
		
		super.setSize(900, 900);
		super.setName("Jump In Game");
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setupButtons (String[][] buttons ) {
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				(this.grid[x][y]).setText(buttons[x][y]);
			}
		}
	}
	
	public void setSelected (String name) {
		for (int x=0; x<5; x++) {
			for (int y=0; y<5; y++) {
				if (this.grid[x][y].getText().equals(name)) {
					this.grid[x][y].setForeground(Color.RED);
				} else {
					this.grid[x][y].setForeground(Color.BLACK);
				}
			}
		}
			
	}	
			
	public void showDialog (String info) {
		JOptionPane.showMessageDialog(this, info);
	}
	
	public void setLevelLabel (String content, Color color) {
		
		this.gameInfo.setText(content);
		this.gameInfo.setForeground(color);
		
	}
	
	public void setHintText (String content, Color color) {
		
		this.hintInfo.setText(content);
		this.hintInfo.setForeground(color);
		
	}
	
	public void cleaeHintText() {
		this.hintInfo.setText("");
	}

		
	public int levelInputDialog () {
		try {
			int level = Integer.valueOf(JOptionPane.showInputDialog("Please enter a level. 0-2"));
			if (level<3 && level>0) {
				return level;
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a integer smaller than 3");
			}
		}catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, "Please enter a valid integer");
		}
		return -1;
	}
	
	public void addNewGameButtonListener (ActionListener newGameButtonListener) {
		this.startMenu.addActionListener(newGameButtonListener);
	}
	
	
	public void addChooseLevelButtonListener (ActionListener chooseLevelButtonListener) {
		this.chooseLevel1Menu.addActionListener(chooseLevelButtonListener);
	}
	
	public void addGridButtonListener (int x, int y, ActionListener GridButtonListener) {
		grid[x][y].addActionListener(GridButtonListener);		
	}
	
	public void addUndoButtonListener (ActionListener UndoButtonListener) {
		this.undoButton.addActionListener(UndoButtonListener);
	}
	
	public void addRedoButtonListener (ActionListener RedoButtonListener) {
		this.redoButton.addActionListener(RedoButtonListener);
	}
	
	public void addHintButtonListener (ActionListener HintButtonListener) {
		this.hintButton.addActionListener(HintButtonListener);
	}
}