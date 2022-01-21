import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class UserRepository {
    private List<User> users = new ArrayList<>();
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addUser(User user) {
        lock.writeLock().lock();
        System.out.println("Поток " + Thread.currentThread().getName() + " начал запись");
        try {
            Thread.sleep(5000);
            users.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил запись");
            lock.writeLock().unlock();
        }
    }

    public User getUser(int userId) {
        lock.readLock().lock();
        System.out.println("Поток " + Thread.currentThread().getName() + " начал чтение");
        try {
            return users.get(userId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил чтение");
            lock.readLock().unlock();
        }
        return null;
    }

    public List<User> getOrderedUsers() {
        lock.readLock().lock();
        System.out.println("Поток " + Thread.currentThread().getName() + " начал чтение2");
        try {
            List<User> copyUsers = new ArrayList<>(users);
            copyUsers.sort(new Comparator<User>() {
                @Override
                public int compare(User user1, User user2) {
                    return user1.compareTo(user2);
                }
            });
            return copyUsers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Поток " + Thread.currentThread().getName() + " закончил чтение2");
            lock.readLock().unlock();
        }
        return null;
    }
}
