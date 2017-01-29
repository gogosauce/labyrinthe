import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Ref;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;


/**
 * Les CaseGraphics sont des objets graphiques pour dessiner les cases dans la fenêtre
 * C'est au niveau de ces cases que le mouseListener est implémenté. On interroge les cases graphiques pour interroger 
 * les cases de la grid. 
 */
public class CaseGraphics extends JComponent {
	private Case idCase;
	private int width = 40;		//taille des segments d'une case (g)

	/**
	 * @param idCase correspond à une case de la grid
	 * @param grid	 
	 */
	public CaseGraphics ( Case idCase)
	{
		super();
		this.setSize(width, width);
		this.idCase = idCase;
	}

	protected void paintComponent (Graphics g)	{
		super.paintComponent(g);

		g.drawImage(new ImageIcon("open.png").getImage(), 0, 0, null);
		
		if(idCase.getWallSouth() != Case.Wall.Open && idCase.getWallEast() != Case.Wall.Open){					
			g.drawImage(new ImageIcon("corner_bot_right.png").getImage(), 0, 0, null);
		}
		if(idCase.getWallSouth() != Case.Wall.Open && idCase.getWallEast() == Case.Wall.Open){
			g.drawImage(new ImageIcon("south.png").getImage(), 0, 0, null);
		}
		if(idCase.getWallSouth() == Case.Wall.Open && idCase.getWallEast() != Case.Wall.Open){
			g.drawImage(new ImageIcon("East.png").getImage(), 0, 0, null);
		}
		
	}

	public int getWidth() {
		return this.width;
	}


}
