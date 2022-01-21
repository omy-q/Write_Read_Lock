public class User implements Comparable {
    private int userId;

    public User(int userId) {
        this.userId = userId;
    }

    public void setUserId(int id) {
        userId = id;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public int compareTo(Object user) {
        return this.userId - ((User) user).getUserId();
    }
}
