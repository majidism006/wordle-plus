package interface_adapter.difficulty;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class DifficultyViewModel extends ViewModel<DifficultyState> {

    public DifficultyViewModel() {
        super("difficulty");
        setState(new DifficultyState());
    }

}
