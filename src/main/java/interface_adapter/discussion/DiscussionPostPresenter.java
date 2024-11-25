package interface_adapter.discussion;

import entity.DiscussionPost;
import interface_adapter.ViewManagerModel;
import use_case.discussion.DiscussionPostOutputBoundary;

import java.util.List;

public class DiscussionPostPresenter implements DiscussionPostOutputBoundary {
    private final DiscussionPostViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public DiscussionPostPresenter(DiscussionPostViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentPostAdded() {
        // Handle post added (if needed)
    }

    @Override
    public void presentAllPosts(List<DiscussionPost> posts) {
        viewModel.setPosts(posts);
    }

    /**
     *
     */
    @Override
    public void switchToInstructionView() {
        viewManagerModel.setState("instructions");
        viewManagerModel.firePropertyChanged();
    }
}
