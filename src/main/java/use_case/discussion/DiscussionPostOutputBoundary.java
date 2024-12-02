package use_case.discussion;

import java.util.List;

import entity.DiscussionPost;

/**
 * Interface defining the output boundary for discussion post use cases.
 */
public interface DiscussionPostOutputBoundary {

    /**
     * Presents the result of adding a new discussion post.
     */
    void presentPostAdded();

    /**
     * Presents all discussion posts.
     *
     * @param posts the list of discussion posts
     */
    void presentAllPosts(List<DiscussionPost> posts);

    /**
     * Switches the view to the instruction view.
     */
    void switchToInstructionView();
}
