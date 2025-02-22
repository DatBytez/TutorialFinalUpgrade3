package main;

public enum GameStates {

	BUILD_STATE, MENU_STATE, COMBAT_STATE, EDIT_STATE, GAME_OVER_STATE;

	public static GameStates gameState = MENU_STATE;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}
