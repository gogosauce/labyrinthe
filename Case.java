public class Case {	
	private Wall wallNorth;
	private Wall wallSouth;
	private Wall wallEast;
	private Wall wallWest;
	int x, y, val, porte;
	boolean player, discovered;	
	

	public Case(int x, int y, int val){
		this.x = x;
		this.y = y;
		this.val = val;
		this.wallEast = Wall.Close;
		this.wallSouth = Wall.Close;
		this.player = false;
		this.discovered = false;

	}
	
	public enum Wall{
		Open, 
		Close,		
		Edge;	 
	}

	public Wall getWallNorth() {
		return wallNorth;
	}
	public void setWallNorth(Wall wallNorth) {
		this.wallNorth = wallNorth;
	}
	public Wall getWallSouth() {
		return wallSouth;
	}
	public void setWallSouth(Wall wallSouth) {
		this.wallSouth = wallSouth;
	}
	public Wall getWallEast() {
		return wallEast;
	}
	public void setWallEast(Wall wallEast) {
		this.wallEast = wallEast;
	}
	public Wall getWallWest() {
		return wallWest;
	}
	public void setWallWest(Wall wallWest) {
		this.wallWest = wallWest;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public int getVal() {
		return val;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getPorte() {
		return porte;
	}
	public void setPorte(int porte) {
		this.porte = porte;
	}
	public boolean isPlayer() {
		return player;
	}
	public void setPlayer(boolean player) {
		this.player = player;
	}
	
	public boolean isDiscovered() {
		return discovered;
	}
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}
	
	
}
