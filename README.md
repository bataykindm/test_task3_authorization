<h1>
  <p align="center">Authorication app</p>
</h1>


<b>Описание</b> <em>
Тестовое RESTful приложение, позволяющее добавлять сообщения в БД и получать последние 10 (согласно заданию). Работать с БД сообщений может только авторизованный пользователь. Аутентификация проходит путем сравнения хэш-кода пароля в БД и хэш-кода, который генерируется сервисом при передаче пароля. 
Стек: Java8, SpringBoot, SpringSecurity, Maven, PostgreSQL, Docker, Git.
</em>

<b>Перед началом</b> <em>
application.properties
Для опробования работы приложения до старта, согласно используемой БД, необходимо изменить в файле application.properties параметры БД, такие как:
spring.datasource.url
spring.datasource.username
spring.datasource.password

Для использования отличной от PostgreSQL БД необходимо также пересмотреть на соответствующие БД параметры: 
spring.datasource.driver-class-name, 
spring.jpa.database, 
spring.jpa.database-platform 

По умолчанию используемый порта 8175:
server.port=8175

Также данный файл содержит параметры для работы с JWT:
jwt.token.secret=inside – для подписи
jwt.token.expired=120000 – время «существования» токена
</em>

<b>Старт</b> <em>
При использовании исходного кода*
Приложение можно завернуть в docker image, для этого в проекте содержится файл Dockerfile описывающий этот процесс. 
Необходимые команды для создания образа (вызываем через командную строку в корне проекта):
mvn clean install – для сборки проекта
docker build -t inside – создание docker image
docker run -p 8175:8175 --name inside -d inside – запуск в работу контейнера приложения с назначенным при старте именем “inside_container” на порту 8175
docker –kill inside_container – для остановки работы приложения
*Также возможно использование ранее созданного docker образа по ссылке https://hub.docker.com/r/bataykindm/inside
</em>

<h2>
  <p align="center">Таблица запросов</p>
</h2>

<table align="center">
	<thead>
		<tr>
			<th>Method</th>
			<th>Path</th>
			<th>Option</th>
      <th>Curl-Request</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><strong>POST</strong></td>
			<td>/auth/login/</td>
			<td>Аутентификация предоставленного аккаунта используя пароль</td>
      <td>curl -X POST "http://localhost:8175/auth/login" -H "Accept:application/json" -H "Content-Type:application/json" -d "{\"username\":\"-----\",\"password\":\"-----\"}"</td>
		</tr>
		<tr>
			<td><strong>POST</strong></td>
      <td>/messages</td>
        <tr>
			    <td>Сохранение предоставленной сущности-сообщения. Возвращает сохраненную сущность-сообщение</td>
          <td>curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" –H "Authorization:Bearer_token" -d "{\"username\":\"-----\",\"text\":\"-----\"}"</td>
        </tr>
        <tr>
          <td>При передаче в поле “text” значение “history 10” метод возвращает последние 10 сообщений из БД</td>
          <td>curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:Bearer_token " -d "{\"username\":\"-----\",\"text\":\"history 10\"}"</td>
        </tr>
      <td>
		</tr>
	</tbody>
</table>

<b></b> <em>
Import.sql
Для проверки работы приложения в проекте содержится файл import.sql для Hibernate, который заполнит начальными для проверки приложения данными им же созданную таблицу:

INSERT INTO accounts (id, username, password) VALUES (321, 'inside', '$2a$04$C5dxv2YjVhh7udwRZ4Pre.GA9Jsqu2wt2pgxH0bDxBkSYtqK.56cW');

Поле “password” хранится в БД в виде хэш-кода, который генерируется при регистрации аккаунта. Для генерации данного хэш-кода использовался https://www.devglan.com/online-tools/bcrypt-hash-generator для пароля “inside”.

Опробование
Согласно таблицы запросов воспользуемся командной строкой и передадим приложению curl-запросы. Для начала произведем аутентификацию аккаунта с полями username=inside и password=inside.

curl -X POST "http://localhost:8175/auth/login" -H "Accept:application/json" -H "Content-Type:application/json" -d "{\"username\":\"inside\",\"password\":\"inside\"}"
Ответом на данный запрос будет с полем “token”. Данное поле необходимо скопировать и вставить в следующий curl-запрос в token.
curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" –H "Authorization:Bearer_token" -d "{\"username\":\"inside\",\"text\":\"message1\"}"
Данный запрос сохранит сущность Message в БД и вернет сохраненную сущность с полями, содержащими инфекцию о том, кем отправлено сообщение: username и содержимое сообщения: text.
Для того, чтобы вернуть последние 10 добавленных в БД сообщений, необходимо направить аналогичный запрос, однако переданное поле “text” должно содержать “history 10”.
curl -X POST "http://localhost:8175/messages" -H "Accept:application/json" -H "Content-Type:application/json" -H "Authorization:Bearer_token " -d "{\"username\":\"inside\",\"text\":\"history 10\"}"

</em>

