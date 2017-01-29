import java.awt.Button;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import javax.swing.JOptionPane;

/**
 * Frame intègre toutes les informations liées à la fenêtre d'affichage.
 *Que ce soit les menus, l'affichage des cases ou les fenêtres de dialogue.
 */
public class Frame extends JFrame {
	private CaseGraphics[][] gridGraphics;
	private Panel panel;	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenu helpMenu = new JMenu("?");
	private JMenu updateMenu = new JMenu("F5");
	private JMenuItem newGame = new JMenuItem("new game"),
			stats = new JMenuItem("stats"),
			options = new JMenuItem("options"),
			quit = new JMenuItem("quit"),
			help = new JMenuItem("help"),
			about = new JMenuItem("about"),
			update = new JMenuItem("update");

	/**
	 * @param maze on donne en parametre la maze du main pour 
	 * ensuite creer une version graphique de celle-ci.
	 */	
	public Frame(Grid maze){

		int height = maze.getHeight();
		int width = maze.getWidth();		
		gridGraphics = new CaseGraphics[height][width];

		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				gridGraphics[i][j]=new CaseGraphics(maze.getCase(i, j));				
			}		
		}

		this.panel = new Panel(gridGraphics);	
		this.setTitle("Curse of the Maze");
		this.setVisible(true);
		this.setSize(width*20 + 16, height*20 + 69);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
		//this.setResizable(false); // permet d'empecher le redimensionnement de la fenetre


		//implementation menu

		this.gameMenu.add(newGame);
		this.gameMenu.addSeparator();
		this.gameMenu.add(stats);
		this.gameMenu.add(options);

		this.gameMenu.addSeparator();
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		this.gameMenu.add(quit);
		this.helpMenu.add(help);
		this.helpMenu.addSeparator();
		this.helpMenu.add(about);
		this.updateMenu.add(update);
		
		this.menuBar.add(gameMenu);
		this.menuBar.add(helpMenu);
		update.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
		});

		newGame.setAccelerator(KeyStroke.getKeyStroke("F2"));
		update.setAccelerator(KeyStroke.getKeyStroke("SPACE"));

		this.menuBar.add(updateMenu);
		//	actualiser.setMnemonic(KeyEvent.VK_O);
		this.setJMenuBar(menuBar);
		this.setVisible(true);	
	}
}
