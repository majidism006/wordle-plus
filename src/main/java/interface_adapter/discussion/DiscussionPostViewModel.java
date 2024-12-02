package interface_adapter.discussion;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import entity.DiscussionPost;
import interface_adapter.ViewModel;

/**
 * ViewModel class for managing the state and behavior of discussion posts.
 */
public class DiscussionPostViewModel extends ViewModel<DiscussionPostState> {
    private final PropertyChangeSupport support;
    private List<DiscussionPost> posts;

    /**
     * Constructs a new DiscussionPostViewModel.
     * Initializes the PropertyChangeSupport and sets the initial state.
     */
    public DiscussionPostViewModel() {
        super("discussion");
        this.support = new PropertyChangeSupport(this);
        setState(new DiscussionPostState());
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     *
     * @param listener the PropertyChangeListener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Gets the list of discussion posts.
     *
     * @return the list of discussion posts
     */
    public List<DiscussionPost> getPosts() {
        return posts;
    }

    /**
     * Sets the list of discussion posts and notifies listeners of the change.
     *
     * @param posts the new list of discussion posts
     */
    public void setPosts(List<DiscussionPost> posts) {
        List<DiscussionPost> oldPosts = this.posts;
        this.posts = posts;
        support.firePropertyChange("posts", oldPosts, posts);
    }

    /**
     * Gets the name of the view associated with this ViewModel.
     *
     * @return the name of the view
     */
    public String getViewName() {
        return "discussion";
    }
}
