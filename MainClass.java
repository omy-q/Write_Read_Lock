

public class MainClass {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        for (int i = 0; i < 2; i++) {
            int threadNumber = i;
            new Thread(() -> {
                System.out.println("Поток " + Thread.currentThread().getName() + " готовится");
                userRepository.addUser(new User(threadNumber));
                userRepository.getUser(threadNumber);
                userRepository.addUser(new User(threadNumber + 15));
                userRepository.getOrderedUsers();
                System.out.println("Поток " + Thread.currentThread().getName() + " завершился");
            }).start();
        }
    }
}
