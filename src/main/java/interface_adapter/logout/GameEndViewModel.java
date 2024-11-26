package interface_adapter.logout;

import interface_adapter.ViewModel;

import java.util.HashMap;
import java.util.Map;


public class GameEndViewModel extends ViewModel<GameEndState> {
    public GameEndViewModel() {
            super("game end");
            setState(new GameEndState());
        }

    public Map<String, Integer> getUserHistory(int win, int loss) {
        Map<String, Integer> history = new HashMap<>();
        history.put("Win", win);
        history.put("Loss", loss);
        return history;
    }
}
