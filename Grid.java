import java.util.ArrayList;

public class Grid {
	private Case[][] maze;
	private int height, width;
	public int val = 0;
	private Case player;


	public Grid(){
		this(4, 4);
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
		initGate();
	}

	public void openedWall(){
		ArrayList<Case> wallsClosed = new ArrayList<Case>();
		for ( int x = 0; x < height; x++) {
			for (int y = 0; y < width; y++) {
				if(maze[x][y].getWallEast() == Case.Wall.Close && maze[x][y].getWallSouth() == Case.Wall.Close){
					wallsClosed.add(new Case(x, y, maze[x][y].getVal()));
					wallsClosed.get(wallsClosed.size()-1).setWallSouth(null);
					wallsClosed.add(new Case(x, y, maze[x][y].getVal()));
					wallsClosed.get(wallsClosed.size()-1).setWallEast(null);
				}
				else{
					if(maze[x][y].getWallEast() == Case.Wall.Close){
						wallsClosed.add(new Case(x, y, maze[x][y].getVal()));
						wallsClosed.get(wallsClosed.size()-1).setWallSouth(Case.Wall.Edge);
					}
					if(maze[x][y].getWallSouth() == Case.Wall.Close){
						wallsClosed.add(new Case(x, y, maze[x][y].getVal()));
						wallsClosed.get(wallsClosed.size()-1).setWallEast(Case.Wall.Edge);
					}
				}
			}			
		}

		while (isMerged(maze) == false && wallsClosed.size() > 1){

			//pick up random case
			int caseId = (int)Math.round( Math.random()*( wallsClosed.size() - 1));
			Case randomArrayCase =wallsClosed.get(caseId);			
			Case mazeCase = this.getCase( randomArrayCase.getX(), randomArrayCase.getY());

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
				wallsClosed.remove(caseId);
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
				wallsClosed.remove(caseId);
			}
		}
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

	public void initGate(){
		player = maze[height-1][width/2];
		maze[height-1][width/2].setPlayer(true);
		maze[height-1][width/2].setDiscovered(true);
		maze[0][width/2].setDiscovered(true); 
		maze[0][width/2].setGate(Case.Gate.Close);
		boolean keyChoose = false;
		while(keyChoose == false){
			int x= (int)Math.round(Math.random()*(this.height-1));
			int y= (int)Math.round(Math.random()*(this.width-1));
			if(x != 0 && y != width/2 || x != height-1 && y != width/2 ){
					maze[x][y].setKey(true);
					keyChoose = true;
			}
		}
	}

	public boolean isPathOpen(int direction){
		switch (direction){
		//south
		case 1:
			if (this.getPlayer().getX() < this.height-1){
				if(this.getCase(getPlayer().getX(), getPlayer().getY()).getWallSouth() == Case.Wall.Open){
					return true;
				}
			}
			break;
			//west
		case 2:
			if (this.getPlayer().getY() > 0){
				if(this.getCase(getPlayer().getX(), getPlayer().getY()-1).getWallEast() == Case.Wall.Open){
					return true;					
				}
			}
			break;	
			//north
		case 3:
			if (this.getPlayer().getX() > 0){
				if(this.getCase(getPlayer().getX()-1, getPlayer().getY()).getWallSouth() == Case.Wall.Open ){
					return true;
				}
			}
			break;
			//east
		case 4:
			if (this.getPlayer().getY() < this.width-1){
				if(this.getCase(getPlayer().getX(), getPlayer().getY()).getWallEast() == Case.Wall.Open){
					return true;
				}
			}
			break;
		}
		return false;
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

	public void setPlayer(int x, int y){
		player.setX(x);
		player.setY(y);
	}
	public Case getPlayer() {
		return player;
	}
}
