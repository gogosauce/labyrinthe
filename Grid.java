package laby;
import java.util.ArrayList;
import java.util.Random;


public class Grid {
	private Case[][] maze;
	private int height, width;
	public int val = 1;


	public Grid(){
		this(5, 5);
	}

	public Grid(int height, int width){
		this.height = height;
		this.width = width;
		maze=new Case[height][width];		
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				val++;
				maze[i][j]=new Case(i, j, val);
			}
		}
	}
	public void displayGrid(){
		for (int i=0; i<height; i++){
			for (int j=0; j<width; j++){
				if(maze[i][j].getWallSouth() == Case.Wall.Close)
					System.out.print(" _ |");
				else
					System.out.print(" X ");
			}
			System.out.println();
		}
	}
	
	
	public void openedWall(){
		ArrayList<Case> leftWalls = new ArrayList<Case>();
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				leftWalls.add(maze[x][y]);
			}
		}
		while (isMerged(maze) == false){
			//pick up random case
			int caseId =(int) Math.round(Math.random()*(leftWalls.size() - 1));  
			//si array vide -> return
			if (leftWalls.size() <= 0) {
				return;
			}
			//if one wall is open then open the other and remove the case from array
			if(leftWalls.get(caseId).getWallEast() == Case.Wall.Open || leftWalls.get(caseId).getWallSouth() == Case.Wall.Open){
				if(leftWalls.get(caseId).getWallEast() != Case.Wall.Edge){
					leftWalls.get(caseId).setWallEast(Case.Wall.Open);
				}
				if (leftWalls.get(caseId).getWallSouth() != Case.Wall.Edge){
					leftWalls.get(caseId).setWallSouth(Case.Wall.Open);

				}
				leftWalls.remove(caseId);
			}
			else{
				//pick up random wall			
				int chooseWall=(int) Math.round(Math.random());
				if (chooseWall > 0){						
					leftWalls.get(caseId).setWallEast(Case.Wall.Open);
				}
				else
					leftWalls.get(caseId).setWallSouth(Case.Wall.Open);				
			}
		}
		//TODO mettre en relation 2 cases et attribuer valeur 
	}	

	public boolean isMerged(Case[][] maze){
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getVal() != 1){
					return false;
				}					
			}
		}
		return true;
	}

	public void delimiteEdge(){
		for (int i=0; i<width; i++){
			maze[0][i].setWallNorth(Case.Wall.Edge);
		}
		for (int i=0; i<width; i++){
			maze[height-1][i].setWallSouth(Case.Wall.Edge);
		}
		for (int i=0; i<height; i++){
			maze[i][0].setWallWest(Case.Wall.Edge);
		}
		for (int i=0; i<height; i++){
			maze[i][width-1].setWallEast(Case.Wall.Edge);
		}
	}

	public Case getCase(int x, int y){
		return maze[x][y];
	}
}
