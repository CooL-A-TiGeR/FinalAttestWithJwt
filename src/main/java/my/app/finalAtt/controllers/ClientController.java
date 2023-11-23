package my.app.finalAtt.controllers;

import my.app.finalAtt.model.Client;
import my.app.finalAtt.service.AuthService;
import my.app.finalAtt.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Контроллер - это связь клиента с нашими методами при помощи HTTP-запросов,
 * он дает возможность обработки бизнес-логики: вызывает методы ClientService.
 * Делаем методы, обрабатывающие наши CRUD-запросы.
 */

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private AuthService authService;
	@Autowired
    private ClientService clientService;

    /**
     * Делаем возможность вставки данных в мапу - это запрос методом POST
     * 
     * ResponseEntity - КЛАСС ДЛЯ ВОЗВРАТА КОДА ОТВЕТА (= статус сервиса) !!
     * 
     * @RequestBody Client client - то, что приходит от клиента сервера(тело запроса в виде JSON-строки,
     * 							    в к-й будут id, name, login, pass, упаковывается REST в объект Java)
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<?> create(
            @RequestBody Client client
    ) {
    	clientService.create(client);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Получаем всех клиентов - это запрос методом GET
     *
     * @return клиенты, статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping({"", "/"})
    public ResponseEntity<List<Client>> readAll() {
        final List<Client> clients = clientService.readAll();
        return clients != null && !clients.isEmpty() ?
                new ResponseEntity<>(clients, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получаем клиента по id
     * 
     * @PathVariable ДЛЯ УКАЗАНИЯ, КАКОЙ ЖДЕМ ПАРАМЕТР
     * 
     * @param id id указанный в URL !
     * 
     * @return клиент, статус
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> read(
            @PathVariable(name="id") int id
    ) {
        final Client client = clientService.read(id);
        return client != null ?
                new ResponseEntity<>(client, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Обновляем клиента с заданным id - это запрос методом PUT
     *
     * @param id
     * @param client
     * @return статус
     */
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(
            @PathVariable(name="id") int id,
            @RequestBody Client client
    ) {
        return clientService.update(client,id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Проверка существования клиента по логину и паролю
     *
     * @param login
     * @param password
     * @return клиент, статус
     */
    @GetMapping(value = "/{login}/{pass}")
    public ResponseEntity<Client> exists(
            @PathVariable(name="login") String login,
            @PathVariable(name="password") String password
    ) {
        final Client client = clientService.getByLoginAndPassword(login, password);
        return client != null ?
                new ResponseEntity<>(client, HttpStatus.FOUND) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
	 * Удаляем клиента по id - это запрос методом DELETE
     *
	 * @param id
	 * @return статус
	 */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(name="id") int id
    ) {
        boolean deleted = clientService.delete(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
