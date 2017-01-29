
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel{

	private CaseGraphics[][] grid;
	public Panel(CaseGraphics[][] grid) {
		super();
		this.grid = grid;
		this.setLayout(null);
		//repaint();
	}
	
	public void paintComponent(Graphics g){		
		g.setColor(Color.white);		
		g.fillRect(0,  0,  getSize().width, getSize().height);
		for (int x = 0; x < grid.length; x++){
			for ( int y = 0; y < grid[x].length; y++){
				CaseGraphics caseGraphics = grid[x][y];
				int width = caseGraphics.getWidth();
				caseGraphics.setLocation(y * width, x * width);
				this.add(caseGraphics);
			}
		}
	}

	
}
