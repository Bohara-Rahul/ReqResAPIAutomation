package in.reqres.tests.models;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private String name;
    private String job;

    public User() {}

    public User(int id) {
        setId(id);
    }

    public User(String name, String job) {
        setName(name);
        setJob(job);
    }

    public User(int id, String first_name, String last_name, String email, String avatar) {
        setId(id);
        setFirstName(first_name);
        setLastName(last_name);
        setEmail(email);
        setAvatar(avatar);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
