package interface_adapter.gameend;

/**
 * Class representing the state at the end of a game.
 */
public class GameEndState {
    private String username = "";
    private String loginError;
    private String password = "";

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the login error message.
     *
     * @return the login error message
     */
    public String getLoginError() {
        return loginError;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the login error message.
     *
     * @param loginError the new login error message
     */
    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}