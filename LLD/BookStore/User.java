package LLD.BookStore;

public abstract class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
