package my.app.finalAtt.repository;

import my.app.finalAtt.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client getByLoginAndPassword(String login, String password);
    boolean deleteById(long id);
}
