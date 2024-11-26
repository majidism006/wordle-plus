package use_case.discussion;

public class DiscussionPostInputData {
    private final String userId;
    private final String content;

    public DiscussionPostInputData(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
