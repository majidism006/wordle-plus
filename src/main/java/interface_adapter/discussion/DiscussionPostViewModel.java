package interface_adapter.discussion;

import entity.DiscussionPost;
import interface_adapter.ViewModel;
import interface_adapter.logout.GameEndState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class DiscussionPostViewModel extends ViewModel<DiscussionPostState> {
    private final PropertyChangeSupport support;
    private List<DiscussionPost> posts;

    public DiscussionPostViewModel() {
        super("discussion");
        this.support = new PropertyChangeSupport(this);
        setState(new DiscussionPostState());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public List<DiscussionPost> getPosts() {
        return posts;
    }

    public void setPosts(List<DiscussionPost> posts) {
        List<DiscussionPost> oldPosts = this.posts;
        this.posts = posts;
        support.firePropertyChange("posts", oldPosts, posts);
    }
    public String getViewName() {
        return "discussion";
    }
}

