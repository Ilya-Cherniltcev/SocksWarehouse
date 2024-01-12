### Тестовое задание - автоматизация учета склада носков )) ###

Выполнено выпускником Skypro JD 5.0 Чернильцевым Ильей
***
### Функционал приложения ###
Реализовано приложение для автоматизации учёта носков на складе магазина. Кладовщик имеет возможность:

- учесть приход и отпуск носков;
- узнать общее количество носков определенного цвета и состава в данный момент времени.
Интерфейс реализован через Сваггер.

**Список URL HTTP-методов** 
1. POST /api/socks/income 
  Регистрирует приход носков на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:

- color — цвет носков, строка (например, black, red, yellow);
- cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
- quantity — количество пар носков, целое число больше 0.

Результаты:

- HTTP 200 — удалось добавить приход;
- HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

2. POST /api/socks/outcome
   Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.
3. GET /api/socks
   Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.

Параметры запроса передаются в URL:

- color — цвет носков, строка;
- operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
- cottonPart — значение процента хлопка в составе носков из сравнения.

Результаты:

- HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
- HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
- HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

Примеры запросов:

- /api/socks?color=red&operation=moreThan&cottonPart=90 — должен вернуть общее количество красных носков с долей хлопка более 90%;
- /api/socks?color=black&operation=lessThan&cottonPart=10 — должен вернуть общее количество черных носков с долей хлопка менее 10%.

***
### Стек, используемых технологий ###

Язык программирования **Java**

База данных **PostgreSQL**


Фреймворки/Библиотеки **Spring Framework**, **Spring Boot**, **Hibernate**, **Lombok**, **Liquibase**, **Mapstruct**, **SpringDoc OpenAPI UI**, **Swagger**

***

### Запуск приложения через Swagger: ###

http://localhost:8080/swagger-ui/index.html
