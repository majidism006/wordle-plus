package use_case.discussion;

public class DiscussionPostInputData {
    private final int userId;
    private final String content;

    public DiscussionPostInputData(int userId, String content) {
        this.userId = userId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }
}
