import java.util.*;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public List<User> getOrderedUsers(){
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.compareTo(user2);
            }
        });
        return users;
    }
}
