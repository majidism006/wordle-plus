package use_case.discussion;

import entity.DiscussionPost;

import java.util.List;

public interface DiscussionPostOutputBoundary {
    void presentPostAdded();
    void presentAllPosts(List<DiscussionPost> posts);

    void switchToInstructionView();
}
