package use_case.grid;
import entity.GameState;

public interface GameRepository {
    GameState getGameState();
    void saveGameState(GameState gameState);
}
