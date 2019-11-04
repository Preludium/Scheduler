package pl.jansmi.scheduler;

public class Session {
    private String userId;

    public Session() {}

    public Session(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
