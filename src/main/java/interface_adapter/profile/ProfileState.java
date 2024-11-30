package interface_adapter.profile;

public class ProfileState {
    private int win;
    private String username = "";
    private String loginError;
    private String password = "";

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

}
