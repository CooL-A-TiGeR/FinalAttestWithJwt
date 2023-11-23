package my.app.finalAtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Задание:
 * Написать API для создания и продления залоговых билетов.
 *
 * Требования:
 * 1) Сервер запускается и отвечает на запросы
 * 2) API позволяет с помощью REST запросов создать залоговый билет
 * 3) API позволяет выполнить пролонгацию залогового билета
 * 4) Сервер предусматривает авторизацию пользователя
 * 5) Все REST методы требуют токен для выполнения и выбрасывают понятные ошибки
 */

@SpringBootApplication
public class CommonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonsApplication.class, args);
	}

}
