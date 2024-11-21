package use_case.reset;

public interface ResetInputBoundary {
    void execute(ResetInputData resetInputData);

    void switchToGridView();
}
