package interface_adapter.logout;

import interface_adapter.ViewModel;

import java.util.HashMap;
import java.util.Map;


public class LogoutViewModel extends ViewModel<LogoutState> {
    public LogoutViewModel() {
            super("game end");
            setState(new LogoutState());
        }

    public Map<String, Integer> getUserHistory(int win, int loss) {
        Map<String, Integer> history = new HashMap<>();
        history.put("Win", win);
        history.put("Loss", loss);
        return history;
    }
}
