package my.app.finalAtt.service;

import lombok.NonNull;
import my.app.finalAtt.model.Role;
import my.app.finalAtt.model.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("user1", "user1", "Пользователь", "Первый", Collections.singleton(Role.USER)),
                new User("admin1", "admin1", "Администартор", "Первый", Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }
}
