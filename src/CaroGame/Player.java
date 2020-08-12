package CaroGame;

public class Player {
	String sym, playerName;
	int playerId;
	private static int playerIdCount = 1;
	Player(String sym,String playerName) {
		this.sym = sym;
		this.playerName = playerName;
		this.playerId = playerIdCount++ ;
	}
	
	String getSym() {
		return this.sym;
	}
	
	
	String getPlayerName() {
		return this.playerName + " (" + this.sym + ") ";
	}
	
	int getId() {
		return this.playerId;
	}
	
}
