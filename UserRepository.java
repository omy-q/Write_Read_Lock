import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserRepository {
    private List<User> users = new ArrayList<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addUser(User user) {
        lock.writeLock().lock();
        try {
            users.add(user);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public User getUser(int userId) {
        lock.readLock().lock();
        try {
            return users.get(userId);
        } finally {
            lock.readLock().unlock();
        }
    }

    public List<User> getOrderedUsers() {
        lock.readLock().lock();
        try {
            List<User> copyUsers = new ArrayList<>(users);
            copyUsers.sort(new Comparator<User>() {
                @Override
                public int compare(User user1, User user2) {
                    return user1.compareTo(user2);
                }
            });
            return users;
        } finally {
            lock.readLock().unlock();
        }
    }
}
