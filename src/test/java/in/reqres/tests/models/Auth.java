package in.reqres.tests.models;

public class Auth {
    private String email;
    private String password;

    public Auth() {}

    public Auth(String email) {
        setEmail(email);
    }

    public Auth(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
