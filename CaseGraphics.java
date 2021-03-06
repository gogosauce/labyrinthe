import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


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
	public CaseGraphics ( Case idCase, Grid maze)
	{
		super();
		this.setSize(width, width);
		this.idCase = idCase;
	}

	protected void paintComponent (Graphics g)	{
		super.paintComponent(g);	


		if(idCase.getWallSouth() != Case.Wall.Open && idCase.getWallEast() != Case.Wall.Open){					
			g.drawImage(new ImageIcon("corner_bot_right.png").getImage(), 0, 0, null);
		}
		if(idCase.getWallSouth() != Case.Wall.Open && idCase.getWallEast() == Case.Wall.Open){
			g.drawImage(new ImageIcon("south.png").getImage(), 0, 0, null);
		}
		if(idCase.getWallSouth() == Case.Wall.Open && idCase.getWallEast() != Case.Wall.Open){
			g.drawImage(new ImageIcon("East.png").getImage(), 0, 0, null);
		}
//		if(idCase.isDiscovered() == false){
//			g.drawImage(new ImageIcon("fogg.png").getImage(), 0, 0, null);
//		}
		if(idCase.isKey() == true){
			g.drawImage(new ImageIcon("key.png").getImage(), 0, 0, null);
		}
		if(idCase.getGate() == Case.Gate.Close){
			g.drawImage(new ImageIcon("gateClose.png").getImage(), 0, 0, null);
		}
		if(idCase.getGate() == Case.Gate.Open){
			g.drawImage(new ImageIcon("gateOpen.png").getImage(), 0, 0, null);
		}
		if(idCase.isPlayer() == true){
			g.drawImage(new ImageIcon("player.png").getImage(), 0, 0, null);
		}
		
	}

	public int getWidth() {
		return this.width;
	}

	public Case getIdCase() {
		return idCase;
	}



}
