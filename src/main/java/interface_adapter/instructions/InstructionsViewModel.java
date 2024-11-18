package interface_adapter.instructions;

import interface_adapter.ViewModel;

/**
 * The View Model for the Grid View.
 */
public class InstructionsViewModel extends ViewModel<InstructionsState> {

    public InstructionsViewModel() {
        super("instructions");
        setState(new InstructionsState());
    }

}