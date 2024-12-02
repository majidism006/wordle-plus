package data_access.repository;

import entity.GameState;
import use_case.grid.GameRepository;

/**
 * Contains operations for the game state.
 */
public class GameRepositoryImpl implements GameRepository {
    // This could be a database, file system, or in-memory storage
    private GameState gameState;

    @Override
    public GameState getGameState() {
        // Logic to retrieve the game state
        return gameState;
    }

    @Override
    public void saveGameState(GameState gameState) {
        // Logic to save the game state
        this.gameState = gameState;
    }
}
