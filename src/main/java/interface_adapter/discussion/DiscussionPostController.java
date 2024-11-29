package interface_adapter.discussion;

import use_case.discussion.DiscussionPostInputBoundary;
import use_case.discussion.DiscussionPostInputData;

public class DiscussionPostController {
    private final DiscussionPostInputBoundary interactor;

    public DiscussionPostController(DiscussionPostInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addPost(String userId, String content) {
        DiscussionPostInputData inputData = new DiscussionPostInputData(userId, content);
        interactor.addPost(inputData);
    }

    public void getAllPosts() {
        interactor.getAllPosts();
    }
    public void fetchRandomQuote(String userId) {
        interactor.fetchRandomQuote(userId);
    }
    public void switchToInstructionView() {
        interactor.switchToInstructionView();
    }


}
