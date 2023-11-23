package my.app.finalAtt.service;

import my.app.finalAtt.model.Client;

import java.util.List;

/**
 * Делаем интерфейс, в котором описываем необходимые операции над клиентами ломбарда:
 * считывание клиентов, добавление/обновление/удаление информации о них.
 */
public interface ClientService {
	/**
	 * Создаем нового клиента
	 *
	 * @param client - клиент, которого будем добавлять
	 */
	void create(Client client);

	/**
	 * Смотрим на список клиентов
	 *
	 * @return все клиенты
	 */
	List<Client> readAll();

	/**
	 * Получаем информацию о конкретном клиенте
	 *
	 * @param id
	 * @return клиент с указанным id
	 */
	Client read(long id);

	/**
	 * Обновляем клиента с заданным id
	 *
	 * @param client
	 * @param id
	 * @return
	 */
	boolean update(Client client, long id);

	/**
	 * Проверка существования клиента
	 *
	 * @param login
	 * @return
	 */
	Client getByLoginAndPassword(String login, String password);

	/**
	 * Удаляем клиента по id
	 *
	 * @param id
	 * @return
	 */
	boolean delete(long id);
	
}
