package use_case.reset;


public class ResetInteractor implements ResetInputBoundary{
    private final ResetOutBoundary userpresenter;

    public ResetInteractor(ResetOutBoundary resetOutBoundary) {
        this.userpresenter = resetOutBoundary;
    }

    @Override
    public void execute(ResetInputData resetInputData) {
        if(resetInputData.getUsername() != null) {
            final ResetOutputData resetOutdata= new ResetOutputData(false);
            userpresenter.prepareSuccessView(resetOutdata);}

    }

    @Override
    public void switchToInstructionView() {
        userpresenter.switchToInstructionView();
    }

}
