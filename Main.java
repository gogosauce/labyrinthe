public class Main {
	public static void main(String[] args) {
		Grid maze= new Grid();
		maze.delimiteEdge();
		maze.openedWall();
		int val = 0;		
		for (int i=0; i<maze.getHeight(); i++){
			for (int j=0; j<maze.getWidth(); j++){
				val++;
				maze.getCase(i, j).setVal(val);
			}
		}
		
		Frame fram = new Frame(maze);
		
	}
}
