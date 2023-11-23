package my.app.finalAtt.controllers;

import my.app.finalAtt.model.Commons;
import my.app.finalAtt.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/commons")
public class CommonsController {
    @Autowired
    private CommonsService commonsService;

    /**
     * Создаём новый товар - это запрос методом PUT
     *
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping({"/new"})
    public ResponseEntity<?> create(@RequestBody Commons commons) {
        commonsService.create(commons);
        return new ResponseEntity<>(HttpStatus.CREATED); // CREATED - данные добавились
    }

    /**
     * Получаем все товары - это запрос методом GET
     *
     * @return товары, статус
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Commons>> readAll() {
        final List<Commons> commons = commonsService.readAll();    //  return new ArrayList<>(commons.values());
        return ((commons != null) && !commons.isEmpty()) ?
                // в ответе отправим коллекцию полученных из сервиса товаров
                new ResponseEntity<List<Commons>>(commons, HttpStatus.OK) :
                // иначе - не ОК
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получаем товары по выбранной категории - это запрос методом POST
     *
     * @return товары, статус
     */
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/byCategory")
    public ResponseEntity<List<Commons>> readByCategory(String category) {
        final List<Commons> commons = commonsService.readByCategory(category);    //  return new ArrayList<>(commons.values());
        return ((commons != null) && !commons.isEmpty()) ?
                // в ответе отправим коллекцию полученных из сервиса товаров
                new ResponseEntity<List<Commons>>(commons, HttpStatus.OK) :
                // иначе - не ОК
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Получаем товар по id
     *
     * @param id указанный в URL !
     * @return товар, статус
     * @PathVariable ДЛЯ УКАЗАНИЯ, КАКОЙ ЖДЕМ ПАРАМЕТР
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/commons/{id}")        // {id} - любое число
    public ResponseEntity<Commons> read(@PathVariable(name = "id") long id) {
        final Commons commons = commonsService.read(id);
        return commons != null ?
                new ResponseEntity<>(commons, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Обновляем данные по товару - это запрос методом PUT
     *
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping({"/update"})
    public ResponseEntity<?> update(@RequestBody Commons commons, @PathVariable(name = "id") long id) {
        final int updated = commonsService.shipmentFromWarehouse(commons, id);
        return updated > 0 ?
                new ResponseEntity<>(updated, HttpStatus.OK) :       // обновилось
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);      // без изменений
    }

    /**
     * Удаляем товар по id - это запрос методом DELETE
     *
     * @param id
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(name = "id") long id
    ) {
        final boolean deleted = commonsService.delete(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /**
     * Отгружаем товар со склада методом POST
     *
     * @param warehouse
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/shipment")
    public ResponseEntity<?> shipmentFromWarehouse(@RequestBody HashMap<Long, Double> warehouse) {
        commonsService.shipmentFromWarehouse(warehouse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
