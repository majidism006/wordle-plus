package use_case.reset;

public interface ResetOutBoundary {

    void prepareSuccessView(ResetOutputData resetOutputData);

    void switchToGridView();
}
