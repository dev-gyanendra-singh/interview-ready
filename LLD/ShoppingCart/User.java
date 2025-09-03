package LLD.ShoppingCart;

public class User {

    String userId;
    String name;
    User(String userId, String userName) {
        this.userId = userId;
        this.name = userName;
    }

    String getUserId() {
        return userId;
    }


    public String getName() {
        return name;
    }
}
