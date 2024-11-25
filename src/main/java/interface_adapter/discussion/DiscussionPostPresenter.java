package interface_adapter.discussion;

import entity.DiscussionPost;
import use_case.discussion.DiscussionPostOutputBoundary;

import java.util.List;

public class DiscussionPostPresenter implements DiscussionPostOutputBoundary {
    private final DiscussionPostViewModel viewModel;

    public DiscussionPostPresenter(DiscussionPostViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentPostAdded() {
        // Handle post added (if needed)
    }

    @Override
    public void presentAllPosts(List<DiscussionPost> posts) {
        viewModel.setPosts(posts);
    }
}
