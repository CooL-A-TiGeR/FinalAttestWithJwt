package my.app.finalAtt.service;

import my.app.finalAtt.model.Commons;

import java.util.HashMap;
import java.util.List;

/**
 * Делаем интерфейс, в котором описываем необходимые операции над Товарами:
 * вывод всех товаров, вывод товаров по категориям, добавление/обновление/удаление информации о них.
 */
public interface CommonsService {
    /**
     * Создаем новый товар
     *
     * @param commons - товар, который будем добавлять
     */
    void create(Commons commons);

    /**
     * Смотрим на список всех товаров
     *
     * @return все товары
     */
    List<Commons> readAll();

    /**
     * Смотрим на список товаров по заданной категории
     *
     * @return товары по заданной категории
     */
    List<Commons> readByCategory(String category);

    /**
     * Получаем информацию о конкретном товаре
     *
     * @param id
     * @return товар с указанным id
     */
    Commons read(long id);

    /**
     * Обновляем товар с заданным id
     *
     * @param commons
     * @param id
     * @return
     */
    int shipmentFromWarehouse(Commons commons, long id);

    /**
     * Удаляем товар по id
     *
     * @param id
     * @return
     */
    boolean delete(long id);

    /**
     * Производим отгрузку товара по выбранным id
     *
     * @param warehouse - мапа со связко id товара и количество
     * @param
     * @return
     */
    void shipmentFromWarehouse(HashMap<Long, Double> warehouse);

}
