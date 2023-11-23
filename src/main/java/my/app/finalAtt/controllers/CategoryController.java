package my.app.finalAtt.controllers;

import my.app.finalAtt.model.Category;
import my.app.finalAtt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * Создаём новую категорию - это запрос методом PUT
     *
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping({"/new"})
    public ResponseEntity<?> create(@RequestBody Category category) {
        categoryService.create(category);
        return new ResponseEntity<>(HttpStatus.CREATED); // CREATED - данные добавились
    }

    /**
     * Получаем все категории - это запрос методом GET
     *
     * @return категории, статус
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<Category>> readAll() {
        final List<Category> category = categoryService.readAll();    //  return new ArrayList<>(category.values());
        return ((category != null) && !category.isEmpty()) ?
                // в ответе отправим коллекцию полученных из сервиса товаров
                new ResponseEntity<List<Category>>(category, HttpStatus.OK) :
                // иначе - не ОК
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   /**
     * Получаем категорию по id
     *
     * @param id указанный в URL !
     * @return категория, статус
     * @PathVariable ДЛЯ УКАЗАНИЯ, КАКОЙ ЖДЕМ ПАРАМЕТР
     */
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/category/{id}")        // {id} - любое число
    public ResponseEntity<Category> read(@PathVariable(name = "id") long id) {
        final Category category = categoryService.read(id);
        return category != null ?
                new ResponseEntity<Category>(category, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Обновляем данные по категории - это запрос методом PUT
     *
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping({"/update"})
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable(name = "id") long id) {
        final int updated = categoryService.update(category, id);
        return updated > 0 ?
                new ResponseEntity<>(updated, HttpStatus.OK) :       // обновилось
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);      // без изменений
    }

    /**
     * Удаляем категорию по id - это запрос методом DELETE
     *
     * @param id
     * @return статус
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(
            @PathVariable(name = "id") long id
    ) {
        final boolean deleted = categoryService.delete(id);
        return deleted ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
