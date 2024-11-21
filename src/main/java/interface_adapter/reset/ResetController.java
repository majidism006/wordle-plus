package interface_adapter.reset;

import use_case.reset.ResetInputBoundary;
import use_case.reset.ResetInputData;

public class ResetController {

    private final ResetInputBoundary resetusecaseInteractor;

    public ResetController(ResetInputBoundary resetusecaseInteractor) {
        this.resetusecaseInteractor = resetusecaseInteractor;
    }

    public void execute(String username) {
        final ResetInputData resetInputData = new ResetInputData(username);

        resetusecaseInteractor.execute(resetInputData);
    }

    public void switchToGridView() {
        resetusecaseInteractor.switchToGridView();
    }
}
