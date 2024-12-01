package interface_adapter.profile;

public class ProfileState {
    private String username = "";
    private String loginError;
    private String password = "";
    private int win;
    private int loss;
    private String status = "";

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String state) {
        this.status = state;
    }
}
