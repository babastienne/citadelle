package applicationTests;

import game.Game;

public class Application {

	public static void main(String[] args) {
		Game test = new Game("FirstPlayer", "SecondPlayer");
		test.launchGame();
	}

}
