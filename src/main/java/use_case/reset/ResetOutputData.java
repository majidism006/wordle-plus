package use_case.reset;

public class ResetOutputData {

    private boolean useCaseFailed;

    public ResetOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
