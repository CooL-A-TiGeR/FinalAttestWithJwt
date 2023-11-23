package my.app.finalAtt.service;

import my.app.finalAtt.model.Category;
import my.app.finalAtt.model.Commons;

import java.util.List;

/**
 * Делаем интерфейс, в котором описываем необходимые операции над категориями:
 * вывод всех категорий, добавление/обновление/удаление информации о них.
 */
public interface CategoryService {
    /**
     * Создаем новую категорию
     *
     * @param category - категория, которую будем добавлять
     */
    void create(Category category);

    /**
     * Смотрим на список всех категорий
     *
     * @return все категории
     */
    List<Category> readAll();

    /**
     * Получаем информацию о конкретной категории
     *
     * @param id
     * @return категория с указанным id
     */
    Category read(long id);

    /**
     * Обновляем категорию с заданным id
     *
     * @param category
     * @param id
     * @return
     */
    int update(Category category, long id);

    /**
     * Удаляем категорию по id
     *
     * @param id
     * @return
     */
    boolean delete(long id);

}
