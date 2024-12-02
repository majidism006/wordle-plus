package use_case.grid;

import entity.GameState;

/**
 * Interface defining the repository for game state operations.
 */
public interface GameRepository {

    /**
     * Retrieves the current game state.
     *
     * @return the current game state
     */
    GameState getGameState();

    /**
     * Saves the given game state.
     *
     * @param gameState the game state to save
     */
    void saveGameState(GameState gameState);
}
