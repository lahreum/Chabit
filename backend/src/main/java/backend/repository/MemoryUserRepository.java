package backend.repository;


import backend.domain.User;

import javax.swing.text.html.Option;
import java.util.*;

public class MemoryUserRepository implements UserRepository{
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public User save(User user) {
        user.setUserId(sequence++);
        store.put(user.getUserId(), user);
        return user;
    }
    @Override
    public Optional<User> findByUserId(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }
    @Override
    public Optional<User> findByUserEmailAndUserUserPassword(String userEmail, String userPassword) {
        return store.values().stream()
                .filter(user -> user.getUserEmail().equals(userEmail) && user.getUserPassword().equals(userPassword))
                .findAny();
    }
    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
