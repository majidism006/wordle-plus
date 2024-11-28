package interface_adapter.discussion;

public class DiscussionPostState {
    private String userId = "";
    private String content = "";
    private String username = "";

    public DiscussionPostState() {
//        this.userId = userId;
//        this.content = content;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
