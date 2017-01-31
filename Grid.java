import java.util.ArrayList;
import java.util.Random;


public class Grid {
	private Case[][] maze;
	private int height, width;
	public int val = 0;

	public Grid(){
		this(20, 20);
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


	public void openedWall(){
		//		Cases avec des murs fermés
		ArrayList<Case> leftWalls = new ArrayList<Case>();
		for ( int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getWallEast() == Case.Wall.Close && maze[x][y].getWallSouth() == Case.Wall.Close){
					leftWalls.add(new Case(x, y, maze[x][y].getVal()));
					leftWalls.get(leftWalls.size()-1).setWallSouth(null);
					
					leftWalls.add(new Case(x, y, maze[x][y].getVal()));
					leftWalls.get(leftWalls.size()-1).setWallEast(null);
				}
				else{
					if(maze[x][y].getWallEast() == Case.Wall.Close){
						leftWalls.add(new Case(x, y, maze[x][y].getVal()));
						leftWalls.get(leftWalls.size()-1).setWallSouth(Case.Wall.Edge);

						}
					if(maze[x][y].getWallSouth() == Case.Wall.Close){
						leftWalls.add(new Case(x, y, maze[x][y].getVal()));
						leftWalls.get(leftWalls.size()-1).setWallEast(Case.Wall.Edge);
					}
				}
			}			
		}
//		for ( int x = 0; x < leftWalls.size(); x++) {
//			System.out.println("X: "+leftWalls.get(x).getX());
//			System.out.println("Y: "+leftWalls.get(x).getY());
//			System.out.println("East wall: "+leftWalls.get(x).getWallEast());
//			System.out.println("South wall: "+leftWalls.get(x).getWallSouth()+"\n");
//		}
		while (isMerged(maze) == false && leftWalls.size() > 1){
			System.out.println("How many walls left? "+leftWalls.size());
			//pick up random case
			int caseId = (int)Math.round( Math.random()*( leftWalls.size() - 1));
			Case randomArrayCase =leftWalls.get(caseId);			
			Case mazeCase = this.getCase( randomArrayCase.getX(), randomArrayCase.getY());

			System.out.println("caseId: "+caseId);
			System.out.println("East: "+randomArrayCase.getWallEast()+" South: "+randomArrayCase.getWallSouth());
			System.out.println("mazeCase x: "+mazeCase.getX()+" y: "+mazeCase.getY());

			//pick up random wall
			if(randomArrayCase.getWallSouth() == Case.Wall.Close){
				Case southCase = this.getCase(mazeCase.getX()+1, mazeCase.getY());					
				if(mazeCase.getVal() != southCase.getVal()) {						
					if ( mazeCase.getVal()> southCase.getVal()) {
						this.setValPath(southCase.getVal(), mazeCase.getVal());
					}
					else {
						this.setValPath(southCase.getVal(), mazeCase.getVal());
					}
					mazeCase.setWallSouth(Case.Wall.Open);
				}
				leftWalls.remove(caseId);
				System.out.println("CASE REMOVED"+"\n");
			}
			else{
				Case eastCase = this.getCase(mazeCase.getX(), mazeCase.getY()+1);
				if (mazeCase.getVal() != eastCase.getVal()) {							
					if (mazeCase.getVal() > eastCase.getVal()) {					
						this.setValPath(mazeCase.getVal(), eastCase.getVal());
					}
					else {
						this.setValPath(eastCase.getVal(), mazeCase.getVal());
					}
					mazeCase.setWallEast(Case.Wall.Open);
				}
				leftWalls.remove(caseId);
				System.out.println("CASE REMOVED"+"\n");
			}
		}
		displayMaze(maze);
	}


	private void displayMaze(Case[][] maze){
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getWallSouth() == Case.Wall.Open && maze[x][y].getWallEast() == Case.Wall.Open){				
					//					System.out.println("   ");
					System.out.print("   ");
				}
				if((maze[x][y].getWallSouth() == Case.Wall.Close || maze[x][y].getWallSouth() == Case.Wall.Edge) && maze[x][y].getWallEast() == Case.Wall.Open){
					//					System.out.print("   ");
					System.out.print("___");
				}
				if(maze[x][y].getWallSouth() == Case.Wall.Open && (maze[x][y].getWallEast() == Case.Wall.Close || maze[x][y].getWallEast() == Case.Wall.Edge)){
					System.out.print("  |");
					//					System.out.print("   ");
				}
				if((maze[x][y].getWallSouth() == Case.Wall.Close || maze[x][y].getWallSouth() == Case.Wall.Edge) && (maze[x][y].getWallEast() == Case.Wall.Close || maze[x][y].getWallEast() == Case.Wall.Edge)){
					//					System.out.print("  ");
					System.out.print("__|");
				}			
			}
			System.out.println("");
		}
	}

	private boolean isCaseOpen(Case mazeCase) {
		if(mazeCase.getWallSouth() != Case.Wall.Close && mazeCase.getWallEast() != Case.Wall.Close){
			return true;
		}
		return false;
	}

	public void setValPath(int lastVal, int newVal ){
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getVal() == lastVal){
					maze[x][y].setVal(newVal);
				}
			}
		}
	}


	public boolean isMerged(Case[][] maze){
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getVal() > 1){
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


}
